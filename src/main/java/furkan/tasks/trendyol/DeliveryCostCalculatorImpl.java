package furkan.tasks.trendyol;

public class DeliveryCostCalculatorImpl implements DeliveryCostCalculator {
    private final double costPerDelivery;
    private final double costPerProduct;
    private final double fixedCost;

    public DeliveryCostCalculatorImpl(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }


    public double calculateFor(ShoppingCart cart) {
        return costPerDelivery * cart.getNumberOfCategories() +
                costPerProduct * cart.getNumberOfDistinctProducts() +
                  fixedCost;
    }
}
