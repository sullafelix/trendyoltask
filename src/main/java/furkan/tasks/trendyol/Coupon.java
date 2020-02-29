package furkan.tasks.trendyol;

public class Coupon {
    private final double minPurchase;
    private final double number;
    private final DiscountType discountType;

    public Coupon(double minPurchase, double number, DiscountType discountType) {
        this.minPurchase = minPurchase;
        this.number = number;
        this.discountType = discountType;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public double getMinPurchase() {
        return minPurchase;
    }

    public double getNumber() {
        return number;
    }

    public double getDiscount(double total) {
        return discountType.getDiscount(number, total);
    }
}
