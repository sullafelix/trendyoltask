package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CouponTest {
    @Test
    public void testConstructorIllegalArguments() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Coupon coupon = new Coupon(0, 0, null);
        });
    }
}
