package furkan.tasks.trendyol;

import org.junit.jupiter.api.Test;

public class CartTest {

    @Test
    public void testConstructor() {
        Category foodCategory = new Category("food");
        Category cosmeticsCategory = new Category("cosmetics");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Campaign moreThanThreeCampaign = new Campaign(foodCategory, 20.0, 3, DiscountType.RATE);
        Campaign moreThanFiveCampaign = new Campaign(foodCategory, 50.0, 5, DiscountType.RATE);
        Campaign fiveLiraCampaign = new Campaign(cosmeticsCategory, 5.0, 5, DiscountType.AMOUNT);

        cart.applyDiscounts(moreThanThreeCampaign, moreThanFiveCampaign, fiveLiraCampaign);

        Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
        cart.applyCoupon(coupon);
        double costPerDelivery = 1.0;
        double costPerProduct = 2.0;
        double fixedCost = 2.99;

        DeliveryCostCalculator deliveryCostCalculator =
                new DeliveryCostCalculator(costPerDelivery, costPerProduct, fixedCost);
        deliveryCostCalculator.calculateFor(cart);
    }
}
