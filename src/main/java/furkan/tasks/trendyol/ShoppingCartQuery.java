package furkan.tasks.trendyol;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ShoppingCartQuery {
    public int getNumberOfCategories();
    public int getNumberOfDistinctProducts();
    public int getNumberOfProductsByCategory(Category category);
    public double getTotalPriceByCategory(Category category);
    public Set<Category> getCategories();
    public Optional<Map<Product, Integer>> getProductQuantityMapForCategory(Category category);
}
