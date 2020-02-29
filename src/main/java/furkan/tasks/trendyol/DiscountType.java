package furkan.tasks.trendyol;

public enum DiscountType {
    RATE, AMOUNT;

    public Double getDiscount(Double number, Double total) {
        switch (this) {
            case RATE:
                return (number * total) / 100;
            case AMOUNT:
                return number;
            default:
                throw new RuntimeException("Discount for this type not found");
        }
    }
}
