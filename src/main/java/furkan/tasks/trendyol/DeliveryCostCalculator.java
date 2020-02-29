package furkan.tasks.trendyol;

public class DeliveryCostCalculator {
    private final double costPerDelivery;
    private final double costPerProduct;
    private final double fixedCost;

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }


    public double calculateFor(ShoppingCart cart) {
        throw new RuntimeException("Not yet implemented.");
    }
}
