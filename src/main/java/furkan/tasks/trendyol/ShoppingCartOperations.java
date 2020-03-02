package furkan.tasks.trendyol;

public interface ShoppingCartOperations {
    public void addItem(Product product, int numberOfProduct);
    public void applyDiscounts(Campaign... campaigns);
    public void applyCoupon(Coupon coupon);
    public double getTotalAmountAfterDiscounts();
    public double getCouponDiscount();
    public double getCampaignDiscount();
    public double getDeliveryCost();
}
