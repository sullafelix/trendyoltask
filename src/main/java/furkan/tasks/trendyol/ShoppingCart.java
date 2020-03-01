package furkan.tasks.trendyol;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ShoppingCart {
    public void addItem(Product product, int numberOfProduct);
    public void applyDiscounts(Campaign... campaigns);
    public void applyCoupon(Coupon coupon);
    public double getTotalAmountAfterDiscounts();
    public double getCouponDiscount();
    public double getCampaignDiscount();
    public double getDeliveryCost();
    public int getNumberOfCategories();
    public int getNumberOfDistinctProducts();
    public Set<Category> getCategories();
    public Optional<Map<Product, Integer>> getProductQuantityForCategory(Category category);
    public void print();
}
