package furkan.tasks.trendyol;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> itemMap = new HashMap<>();

    public double getTotalAmountAfterDiscounts() {
        throw new RuntimeException("Not yet implemented!");
    }

    public double getCouponDiscount() {
        throw new RuntimeException("Not yet implemented!");
    }

    public double getCampaignDiscount() {
        throw new RuntimeException("Not yet implemented!");
    }

    public double getDeliveryCost() {
        throw new RuntimeException("Not yet implemented.");
    }

    public void print() {
        throw new RuntimeException("Not yet implemented!");
    }

    public void addItem(Product product, int number) {
        if(itemMap.containsKey(product)) {
            Integer updatedNumber = itemMap.get(product) + number;
            itemMap.put(product, updatedNumber);
        } else {
            itemMap.put(product, number);
        }
    }

    public void applyDiscounts(Campaign... campaigns) {
        throw new RuntimeException("Not yet implemented!");
    }

    public void applyCoupon(Coupon coupon) {
        throw new RuntimeException("Not yet implemented!");
    }
}
