package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void isParentOrSelfTest() {
        Category foodCategory = new Category("food");
        Category chocolateCategory = new Category("chocolate", foodCategory);

        Assertions.assertTrue(chocolateCategory.isParentOrSelf(chocolateCategory));
        Assertions.assertTrue(chocolateCategory.isParentOrSelf(foodCategory));
    }
}
