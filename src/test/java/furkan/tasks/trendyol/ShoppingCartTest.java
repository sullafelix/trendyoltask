package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static furkan.tasks.trendyol.TestUtils.DOUBLE_DELTA;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartTest {
    DeliveryCostCalculator deliveryCostCalculator;
    ShoppingCartPrinter shoppingCartPrinter;

    @BeforeEach
    public void mockDeliveryCostCalculator() {
        deliveryCostCalculator = mock(DeliveryCostCalculator.class);
        when(deliveryCostCalculator.calculateFor(any(ShoppingCart.class))).thenReturn(25.5);
    }

    @BeforeEach
    public void mockShippingPrinter() {
        shoppingCartPrinter = mock(ShoppingCartPrinter.class);
    }

    @Test
    public void testGetTotalAmountAfterDiscounts() {
        Category foodCategory = new Category("food");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        ShoppingCart cart = new ShoppingCartImpl(null,null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Assertions.assertEquals(450.0, cart.getTotalAmountAfterDiscounts(), DOUBLE_DELTA);
    }

    @Test
    public void testGetTotalAmountAfterDiscounts_WithCampaigns() {
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

        Assertions.assertEquals(360.0, cart.getTotalAmountAfterDiscounts(), DOUBLE_DELTA);
    }

    @Test
    public void testGetTotalAmountAfterDiscounts_WithCoupon() {
        Category foodCategory = new Category("food");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        ShoppingCartImpl cart = new ShoppingCartImpl(null, null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
        cart.applyCoupon(coupon);

        Assertions.assertEquals(405.0, cart.getTotalAmountAfterDiscounts(), DOUBLE_DELTA);
    }

    @Test
    public void testGetTotalAmount_WithCampaignsWithCoupon() {
        Category foodCategory = new Category("food");

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

        Assertions.assertEquals(324.0, cart.getTotalAmountAfterDiscounts(), DOUBLE_DELTA);
    }

    @Test
    public void testGetCouponDiscount() {
        ShoppingCartImpl cart = new ShoppingCartImpl(null, null);

        Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
        cart.applyCoupon(coupon);

        Assertions.assertEquals(0, cart.getCouponDiscount());
    }

    @Test
    public void testGetCampaignDiscount() {
        Category foodCategory = new Category("food");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);

        ShoppingCartImpl cart = new ShoppingCartImpl(null, null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Campaign moreThanThreeCampaign = new Campaign(foodCategory, 20.0, 3, DiscountType.RATE);
        Campaign moreThanFiveCampaign = new Campaign(foodCategory, 50.0, 5, DiscountType.RATE);
        Campaign fiveLiraCampaign = new Campaign(foodCategory, 5.0, 5, DiscountType.AMOUNT);
        cart.applyDiscounts(moreThanThreeCampaign, moreThanFiveCampaign, fiveLiraCampaign);

        Assertions.assertEquals(90.0, cart.getCampaignDiscount(), DOUBLE_DELTA);
    }

    @Test
    public void testGetDeliveryCost() {
        ShoppingCartImpl cart = new ShoppingCartImpl(deliveryCostCalculator, null);
        Assertions.assertEquals(25.5, cart.getDeliveryCost(), DOUBLE_DELTA);
    }

    @Test
    public void testGetProductQuantityMapForCategory_WhenCategoryNotFound() {
        ShoppingCart shoppingCart = new ShoppingCartImpl(null, null);
        Optional<Map<Product, Integer>> productQuantityMap =
                shoppingCart.getProductQuantityMapForCategory(new Category("food"));
        Assertions.assertEquals(Optional.empty(), productQuantityMap);
    }

    @Test
    public void testAddItem_ItemExists() {
        Category foodCategory = new Category("food");
        Product apple = new Product("Apple", 100.0, foodCategory);

        ShoppingCart shoppingCart = new ShoppingCartImpl(null, null);
        shoppingCart.addItem(apple, 2);
        shoppingCart.addItem(apple, 4);

        Assertions.assertEquals(6, shoppingCart.getNumberOfProductsByCategory(foodCategory));
    }

    @Test
    public void testPrint() {
        ShoppingCart shoppingCart = new ShoppingCartImpl( null, shoppingCartPrinter);
        shoppingCart.print();
        Mockito.verify(shoppingCartPrinter, Mockito.times(1)).printShoppingCart(shoppingCart);
    }

    @Test
    public void testGetNumberOfCategories() {
        Category foodCategory = new Category("food");
        Category cosmeticsCategory = new Category("cosmetics");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);
        Product toothBrush = new Product("Toothbrush", 200.0, cosmeticsCategory);

        ShoppingCartImpl cart = new ShoppingCartImpl(null, null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
        cart.addItem(toothBrush, 2);

        Assertions.assertEquals(2, cart.getNumberOfCategories());
    }

    @Test
    public void testGetNumberOfDistinctProducts() {
        Category foodCategory = new Category("food");
        Category cosmeticsCategory = new Category("cosmetics");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);
        Product toothBrush = new Product("Toothbrush", 200.0, cosmeticsCategory);

        ShoppingCartImpl cart = new ShoppingCartImpl(null, null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
        cart.addItem(toothBrush, 2);

        Assertions.assertEquals(3, cart.getNumberOfDistinctProducts());
    }

    @Test
    public void testGetCategories() {
        Category foodCategory = new Category("food");
        Category cosmeticsCategory = new Category("cosmetics");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);
        Product toothBrush = new Product("Toothbrush", 200.0, cosmeticsCategory);

        ShoppingCartImpl cart = new ShoppingCartImpl(null, null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
        cart.addItem(toothBrush, 2);

        Assertions.assertEquals(Set.of(foodCategory, cosmeticsCategory), cart.getCategories());
    }

    @Test
    public void testGetTotalPriceByCategory() {
        Category foodCategory = new Category("food");
        Category cosmeticsCategory = new Category("cosmetics");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);
        Product toothBrush = new Product("Toothbrush", 200.0, cosmeticsCategory);

        ShoppingCartImpl cart = new ShoppingCartImpl(null, null);
        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
        cart.addItem(toothBrush, 2);

        Assertions.assertEquals(450.0, cart.getTotalPriceByCategory(foodCategory), DOUBLE_DELTA);
    }
}
