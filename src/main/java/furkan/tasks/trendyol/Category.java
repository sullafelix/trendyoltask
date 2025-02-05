package furkan.tasks.trendyol;

import java.util.Objects;

public class Category {
    private Category parent;
    private String title;

    public Category(String title) {
        this.title = title;
    }

    public Category(String title, Category parent) {
        this(title);
        this.parent = parent;
    }

    public boolean isParentOrSelf(Category other) {
        Category current = this;
        while(current != null) {
            if(other == current) {
                return true;
            }
            current = current.parent;
        }

        return false;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(parent, category.parent) &&
                Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, title);
    }
}
