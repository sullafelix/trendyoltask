package furkan.tasks.trendyol;

import java.util.*;

public class ShoppingCartImpl implements ShoppingCart {
    private final Map<Category, Map<Product, Integer>> categoryItemMap = new HashMap<>();
    private List<Campaign> campaigns = new LinkedList<>();
    private ShoppingCartPrinter printer;
    private DeliveryCostCalculator deliveryCostCalculator;
    private Coupon coupon;

    public ShoppingCartImpl(DeliveryCostCalculator deliveryCostCalculator, ShoppingCartPrinter printer) {
        setDeliveryCostCalculator(deliveryCostCalculator);
        setPrinter(printer);
    }

    public void setPrinter(ShoppingCartPrinter printer) {
        this.printer = printer;
    }

    public void setDeliveryCostCalculator(DeliveryCostCalculator deliveryCostCalculator) {
        this.deliveryCostCalculator = deliveryCostCalculator;
    }

    @Override
    public double getTotalAmountAfterDiscounts() {
        return getTotalAmount() - getCampaignDiscount() - getCouponDiscount();
    }

    @Override
    public double getCouponDiscount() {
        if(coupon == null) {
            return 0;
        }

        return coupon.getDiscount(getTotalAmount() - getCampaignDiscount());
    }

    private double getTotalAmount() {
        return categoryItemMap.values().stream()
                .flatMap(map -> map.entrySet().stream())
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .reduce(0.0, Double::sum);
    }

    @Override
    public double getCampaignDiscount() {
        return campaigns.stream()
                .filter(campaign -> campaign.isApplicable(this))
                .mapToDouble(campaign -> campaign.getDiscountAmount(this))
                .max()
                .orElse(0);
    }

    @Override
    public double getDeliveryCost() {
        return deliveryCostCalculator.calculateFor(this);
    }

    @Override
    public void print() {
        printer.printShoppingCart(this);
    }

    @Override
    public void addItem(Product product, int number) {
        Map<Product, Integer> itemMap;
        if(categoryItemMap.containsKey(product.getCategory())) {
            itemMap = categoryItemMap.get(product.getCategory());
            if(itemMap.containsKey(product)) {
                number = itemMap.get(product) + number;
            }
            itemMap.put(product, number);
        } else {
            itemMap = new HashMap<>();
            itemMap.put(product, number);
            categoryItemMap.put(product.getCategory(), itemMap);
        }
    }

    @Override
    public void applyDiscounts(Campaign... campaigns) {
        this.campaigns.clear();
        this.campaigns.addAll(Arrays.asList(campaigns));
    }

    @Override
    public void applyCoupon(Coupon coupon) {
        if(coupon.getMinPurchase() <= getTotalAmount() - getCampaignDiscount()) {
            this.coupon = coupon;
        }
    }

    @Override
    public int getNumberOfCategories() {
        return categoryItemMap.keySet().size();
    }

    @Override
    public int getNumberOfDistinctProducts() {
        return categoryItemMap.values().stream()
                .mapToInt(productIntegerMap -> productIntegerMap.keySet().size())
                .sum();
    }

    @Override
    public Optional<Map<Product, Integer>> getProductQuantityMapForCategory(Category category) {
        Map<Product, Integer> productQuantityMap = this.categoryItemMap.entrySet().stream()
                .filter(categoryItemEntry -> categoryItemEntry.getKey().equals(category))
                .map(Map.Entry::getValue)
                .findFirst().orElse(null);
        if(productQuantityMap == null) {
            return Optional.empty();
        }

        return Optional.of(Collections.unmodifiableMap(productQuantityMap));
    }

    @Override
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(this.categoryItemMap.keySet());
    }

    @Override
    public int getNumberOfProductsByCategory(Category category) {
        Optional<Map<Product, Integer>> productQuantityOfCategory = getProductQuantityMapForCategory(category);

        return productQuantityOfCategory.map(
                productIntegerMap -> productIntegerMap.values().stream()
                        .mapToInt(i -> i)
                        .sum()).orElse(0);
    }

    @Override
    public double getTotalPriceByCategory(Category category) {
        Optional<Map<Product, Integer>> productQuantityOfCategory = getProductQuantityMapForCategory(category);

        return productQuantityOfCategory.map(
                productIntegerMap -> productIntegerMap.entrySet().stream()
                        .mapToDouble(productQuantityEntry ->
                                productQuantityEntry.getKey().getPrice() * productQuantityEntry.getValue())
                        .sum()).orElse(0.0);
    }
}
