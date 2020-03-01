package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void testIsParentOrSelf() {
        Category foodCategory = new Category("food");
        Category chocolateCategory = new Category("chocolate", foodCategory);

        Assertions.assertTrue(chocolateCategory.isParentOrSelf(chocolateCategory));
        Assertions.assertTrue(chocolateCategory.isParentOrSelf(foodCategory));
    }

    @Test
    public void testIsParentOrSelfNegative() {
        Category foodCategory = new Category("food");
        Category chocolateCategory = new Category("chocolate", foodCategory);

        Assertions.assertFalse(foodCategory.isParentOrSelf(chocolateCategory));
    }

    @Test
    public void testEquals() {
        Category foodCategory1 = new Category("food");
        Category foodCategory2 = new Category("food");

        Assertions.assertEquals(foodCategory1, foodCategory2);
    }

    @Test
    public void testNotEquals() {
        Category foodCategory = new Category("food");
        Category chocolateCategory = new Category("chocolate", foodCategory);

        Assertions.assertNotEquals(foodCategory, chocolateCategory);
    }
}
