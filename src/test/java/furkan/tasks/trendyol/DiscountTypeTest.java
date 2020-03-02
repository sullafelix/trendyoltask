package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static furkan.tasks.trendyol.TestUtils.DOUBLE_DELTA;

public class DiscountTypeTest {
    @Test
    public void testAmount() {
        DiscountType discountType = DiscountType.AMOUNT;

        Assertions.assertEquals(100, discountType.getDiscount(100.0, 2000.0), DOUBLE_DELTA);
    }

    @Test
    public void testRate() {
        DiscountType discountType = DiscountType.RATE;

        Assertions.assertEquals(400, discountType.getDiscount(20.0, 2000.0), DOUBLE_DELTA);
    }
}
