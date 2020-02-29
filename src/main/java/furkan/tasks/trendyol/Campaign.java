package furkan.tasks.trendyol;

public class Campaign {
    private final Category category;
    private final double number;
    private final int minUnit;
    private final DiscountType discountType;



    public Campaign(Category category, double number, int minUnit, DiscountType discountType) {
        this.category = category;
        this.number = number;
        this.minUnit = minUnit;
        this.discountType = discountType;
    }
}
