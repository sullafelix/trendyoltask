package furkan.tasks.trendyol;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

public class ConsoleShoppingCartPrinterTest {
    StringBuilder stringBuilder;
    static ShoppingCart shoppingCart;
    static PrintStream defaultOutputStream;

    @BeforeAll
    public static void saveDefaultPrintStream() {
        defaultOutputStream = System.out;
    }

    @BeforeAll
    public static void mockShoppingCart() {
        Category foodCategory = new Category("food");
        Category cosmeticsCategory = new Category("cosmetics");
        Category drinksCategory = new Category("drinks");

        Product apple = new Product("Apple", 100.0, foodCategory);
        Product almond = new Product("Almonds", 150.0, foodCategory);
        Product perfume = new Product("Perfume", 200.0, cosmeticsCategory);
        Product toothPaste = new Product("Toothpaste", 120.0, cosmeticsCategory);
        Product lemonade = new Product("Lemonade", 125.50, drinksCategory);

        Map<Product, Integer> foodProducts = Map.of(apple, 3, almond, 1);
        Map<Product, Integer> cosmeticProducts = Map.of(perfume, 2, toothPaste, 1);
        Map<Product, Integer> drinkProducts = Map.of(lemonade, 4);

        shoppingCart = Mockito.mock(ShoppingCart.class);
        when(shoppingCart.getCategories()).thenReturn(Set.of(foodCategory, cosmeticsCategory, drinksCategory));
        when(shoppingCart.getProductQuantityMapForCategory(foodCategory)).thenReturn(Optional.of(foodProducts));
        when(shoppingCart.getProductQuantityMapForCategory(cosmeticsCategory)).thenReturn(Optional.of(cosmeticProducts));
        when(shoppingCart.getProductQuantityMapForCategory(drinksCategory)).thenReturn(Optional.of(drinkProducts));
        when(shoppingCart.getTotalAmountAfterDiscounts()).thenReturn(500.0);
        when(shoppingCart.getCampaignDiscount()).thenReturn(20.0);
        when(shoppingCart.getCouponDiscount()).thenReturn(50.0);
        when(shoppingCart.getDeliveryCost()).thenReturn(35.0);
    }

    @BeforeEach
    public void setOutputStream() {
        stringBuilder = new StringBuilder();
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int i) {
                stringBuilder.append((char)i);
            }
        };
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testPrint() {
        ShoppingCartPrinter shoppingCartPrinter = new ConsoleShoppingCartPrinter();
        shoppingCartPrinter.printShoppingCart(shoppingCart);

        String printedString = stringBuilder.toString()
                                            .toLowerCase();

        // write output to default output
        defaultOutputStream.println(stringBuilder.toString());

        Assertions.assertTrue(printedString.contains("apple"));
        Assertions.assertTrue(printedString.contains("almond"));
        Assertions.assertTrue(printedString.contains("perfume"));
        Assertions.assertTrue(printedString.contains("toothpaste"));
        Assertions.assertTrue(printedString.contains("lemonade"));
        Assertions.assertTrue(printedString.contains("food"));
        Assertions.assertTrue(printedString.contains("drinks"));
        Assertions.assertTrue(printedString.contains("cosmetics"));
        Assertions.assertTrue(printedString.contains("570"));
        Assertions.assertTrue(printedString.contains("70"));
        Assertions.assertTrue(printedString.contains("535"));
        Assertions.assertTrue(printedString.contains("35"));
    }

    @AfterAll
    public static void restoreDefaultPrintStream() {
        System.setOut(defaultOutputStream);
    }
}
