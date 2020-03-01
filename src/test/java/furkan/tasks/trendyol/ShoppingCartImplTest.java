package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShoppingCartImplTest {

    @Test
    public void getTotalAmountTest() {
        Category foodCategory = new Category("food");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        ShoppingCart cart = new ShoppingCartImpl(null,null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Assertions.assertEquals(450.0, cart.getTotalAmountAfterDiscounts());
    }

    @Test
    public void getTotalAmountWithCampaignsTest() {
        Category foodCategory = new Category("food");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        ShoppingCart cart = new ShoppingCartImpl(null, null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Campaign moreThanThreeCampaign = new Campaign(foodCategory, 20.0, 3, DiscountType.RATE);
        Campaign moreThanFiveCampaign = new Campaign(foodCategory, 50.0, 5, DiscountType.RATE);
        Campaign fiveLiraCampaign = new Campaign(foodCategory, 5.0, 5, DiscountType.AMOUNT);
        cart.applyDiscounts(moreThanThreeCampaign, moreThanFiveCampaign, fiveLiraCampaign);

        Assertions.assertEquals(360.0, cart.getTotalAmountAfterDiscounts());
    }

    @Test
    public void getTotalAmountWithCouponTest() {
        Category foodCategory = new Category("food");
        Category cosmeticsCategory = new Category("cosmetics");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        ShoppingCartImpl cart = new ShoppingCartImpl(null, null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
        cart.applyCoupon(coupon);

        Assertions.assertEquals(405.0, cart.getTotalAmountAfterDiscounts());
    }

    @Test
    public void getTotalAmountWithCampaignsWithCouponTest() {
        Category foodCategory = new Category("food");
        Category cosmeticsCategory = new Category("cosmetics");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        ShoppingCartImpl cart = new ShoppingCartImpl(null, null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Campaign moreThanThreeCampaign = new Campaign(foodCategory, 20.0, 3, DiscountType.RATE);
        Campaign moreThanFiveCampaign = new Campaign(foodCategory, 50.0, 5, DiscountType.RATE);
        Campaign fiveLiraCampaign = new Campaign(foodCategory, 5.0, 5, DiscountType.AMOUNT);
        cart.applyDiscounts(moreThanThreeCampaign, moreThanFiveCampaign, fiveLiraCampaign);

        Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
        cart.applyCoupon(coupon);

        Assertions.assertEquals(324.0, cart.getTotalAmountAfterDiscounts());
    }
}
