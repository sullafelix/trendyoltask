package furkan.tasks.trendyol;

public class Coupon {
    private final double minPurchase;
    private final double number;
    private final DiscountType discountType;

    public Coupon(double minPurchase, double number, DiscountType discountType) {
        checkArguments(minPurchase, number, discountType);
        this.minPurchase = minPurchase;
        this.number = number;
        this.discountType = discountType;
    }

    private void checkArguments(double number, double minPurchase, DiscountType discountType) {
        StringBuilder errorMessage = new StringBuilder();
        if(number <= 0) {
            errorMessage.append("Number must be greater than 0. ");
        }
        if(minPurchase <= 0) {
            errorMessage.append("Minimum purchase amount must be greater than 0. ");
        }
        if(discountType == null) {
            errorMessage.append("Discount type must not be null.");
        }
        if(!errorMessage.toString().equals("")) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    public double getMinPurchase() {
        return minPurchase;
    }

    public double getDiscount(double total) {
        return discountType.getDiscount(number, total);
    }
}
