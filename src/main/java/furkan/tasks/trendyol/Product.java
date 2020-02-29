package furkan.tasks.trendyol;

import java.util.Objects;

public class Product {
    private String title;
    private double price;
    private Category category;

    public Product(String title, double price, Category category) {
        checkFields(title, price, category);
        this.setTitle(title);
        this.setPrice(price);
        this.setCategory(category);
    }

    private void checkFields(String title, double price, Category category) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        if(title == null || title.equals("")) {
            errorMessageBuilder.append("Title can not be null or empty. ");
        }
        if(price <= 0) {
            errorMessageBuilder.append("Price must be greater than 0. ");
        }
        if(category == null) {
            errorMessageBuilder.append("Category can not be null.");
        }

        String errorMessage = errorMessageBuilder.toString();
        if(!errorMessage.equals("")) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                Objects.equals(getTitle(), product.getTitle()) &&
                Objects.equals(getCategory(), product.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getPrice(), getCategory());
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
