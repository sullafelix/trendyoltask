package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void shouldThrowExceptionWithIllegalConstructorParams() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Product("", 0, null);
        });
    }

    @Test
    public void testEquals() {
        Category clothesCategory = new Category("clothes");

        Product product1 = new Product("t-shirt", 20.0, clothesCategory);
        Product product2 = new Product("t-shirt", 20.0, clothesCategory);

        Assertions.assertEquals(product1, product2);
    }
}
