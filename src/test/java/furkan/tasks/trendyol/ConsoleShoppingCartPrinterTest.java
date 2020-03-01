package furkan.tasks.trendyol;

import org.junit.jupiter.api.Test;

public class ConsoleShoppingCartPrinterTest {

    @Test
    public void testPrint() {
        Category foodCategory = new Category("food");
        Category cosmeticsCategory = new Category("cosmetics");
        Category drinksCategory = new Category("drinks");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        Product perfume = new Product("Perfume", 200.0, cosmeticsCategory);
        Product toothPaste = new Product("Toothpaste", 120.0, cosmeticsCategory);

        Product lemonade = new Product("Lemonade", 125.50, drinksCategory);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorImpl(7.5, 2.5, 2.99);
        ShoppingCartPrinter shoppingCartPrinter = new ConsoleShoppingCartPrinter();
        ShoppingCartImpl cart = new ShoppingCartImpl(deliveryCostCalculator, shoppingCartPrinter);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
        cart.addItem(perfume, 2);
        cart.addItem(toothPaste, 1);
        cart.addItem(lemonade, 4);


        Campaign moreThanThreeFoodCampaign = new Campaign(foodCategory, 20.0, 3, DiscountType.RATE);
        Campaign moreThanFiveFoodCampaign = new Campaign(foodCategory, 50.0, 5, DiscountType.RATE);
        Campaign fiveLiraDrinkCampaign = new Campaign(drinksCategory, 5.0, 4, DiscountType.AMOUNT);
        cart.applyDiscounts(moreThanThreeFoodCampaign, moreThanFiveFoodCampaign, fiveLiraDrinkCampaign);

        Coupon coupon = new Coupon(500, 15, DiscountType.RATE);
        cart.applyCoupon(coupon);

        cart.print();
    }
}
