package furkan.tasks.trendyol;

import java.util.*;

public class ShoppingCartImpl implements ShoppingCart{
    private final Map<Category, Map<Product, Integer>> categoryItemMap = new HashMap<>();
    private List<Campaign> campaigns = new LinkedList<>();
    private ShoppingCartPrinter printer;
    private Coupon coupon;

    public ShoppingCartImpl(ShoppingCartPrinter printer) {
        setPrinter(printer);
    }

    public void setPrinter(ShoppingCartPrinter printer) {
        this.printer = printer;
    }

    public double getTotalAmountAfterDiscounts() {
        return getTotalAmount() - getCampaignDiscount() - getCouponDiscount();
    }

    public double getCouponDiscount() {
        if(coupon == null) {
            return 0;
        }

        double totalAmountAfterCampaigns = getTotalAmount() - getCampaignDiscount();
        if(totalAmountAfterCampaigns > coupon.getMinPurchase()) {
            return coupon.getDiscount(totalAmountAfterCampaigns);
        }

        return 0;
    }

    private double getTotalAmount() {
        return categoryItemMap.values().stream()
                .flatMap(map -> map.entrySet().stream())
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .reduce(0.0, Double::sum);
    }

    public double getCampaignDiscount() {
        return campaigns.stream()
                .filter(campaign -> campaign.isApplicable(this.categoryItemMap))
                .mapToDouble(campaign -> campaign.getDiscountAmount(this.categoryItemMap))
                .max()
                .orElse(0);
    }

    public double getDeliveryCost() {
        throw new RuntimeException("Not yet implemented!");
    }

    public void print() {
        printer.printShoppingCart(this);
    }

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

    public void applyDiscounts(Campaign... campaigns) {
        this.campaigns.clear();
        this.campaigns.addAll(Arrays.asList(campaigns));
    }

    public void applyCoupon(Coupon coupon) {
        if(coupon.getMinPurchase() <= getTotalAmount() - getCampaignDiscount()) {
            this.coupon = coupon;
        }
    }
}
