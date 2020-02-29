package furkan.tasks.trendyol;

public class Coupon {
    private final double minAmount;
    private final double number;
    private final DiscountType discountType;

    public Coupon(double minAmount, double number, DiscountType discountType) {
        this.minAmount = minAmount;
        this.number = number;
        this.discountType = discountType;
    }
}
