package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void shouldThrowExceptionWithIllegalConstructorParams() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Product product = new Product("", 0, null);
        });
    }
}
