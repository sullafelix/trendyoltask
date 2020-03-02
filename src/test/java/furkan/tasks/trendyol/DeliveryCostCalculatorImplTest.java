package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static furkan.tasks.trendyol.TestUtils.DOUBLE_DELTA;
import static org.mockito.Mockito.when;

public class DeliveryCostCalculatorImplTest {
    static ShoppingCart shoppingCart;

    @BeforeAll
    public static void mockShoppingCart() {
        shoppingCart = Mockito.mock(ShoppingCart.class);
        when(shoppingCart.getNumberOfCategories()).thenReturn(3);
        when(shoppingCart.getNumberOfDistinctProducts()).thenReturn(5);
    }

    @Test
    public void testCalculateFor() {
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorImpl(7.5, 2.5, 2.99);
        Assertions.assertEquals(37.99, deliveryCostCalculator.calculateFor(shoppingCart), DOUBLE_DELTA);
    }
}
