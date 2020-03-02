package furkan.tasks.trendyol;

import java.text.DecimalFormat;

public class ConsoleShoppingCartPrinter implements ShoppingCartPrinter {
    private static final int CATEGORY_END_LINEBREAK_NUMBER = 3;
    private static final String CATEGORY_HEADER_LINE =
            "=============================================================";
    private DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");


    @Override
    public void printShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder outputBuilder = new StringBuilder();
        shoppingCart.getCategories().forEach(
            category ->
            {
                appendCategoryHeader(outputBuilder, category);
                shoppingCart.getProductQuantityMapForCategory(category).get()
                        .forEach((product, quantity) -> appendProductLine(outputBuilder, product, quantity));
                this.appendCategoryLineBreaks(outputBuilder);
            });
        appendSummaryInfo(shoppingCart, outputBuilder);
        System.out.println(outputBuilder.toString());
    }

    private void appendSummaryInfo(ShoppingCart shoppingCart, StringBuilder builder) {
        builder.append(String.format("%-16s %10s  %-16s %10s%s%-16s %10s  %-16s %10s",
                "Total Price: ",
                decimalFormat.format(shoppingCart.getTotalAmountAfterDiscounts() +
                        shoppingCart.getCampaignDiscount() + shoppingCart.getCouponDiscount()),
                "Total Discount: ",
                decimalFormat.format(shoppingCart.getCampaignDiscount() + shoppingCart.getCouponDiscount()),
                System.lineSeparator(),
                "Total Amount: ",
                decimalFormat.format(shoppingCart.getTotalAmountAfterDiscounts() + shoppingCart.getDeliveryCost()),
                "Delivery Cost: ",
                decimalFormat.format(shoppingCart.getDeliveryCost())));

    }

    private void appendCategoryHeader(StringBuilder builder, Category category) {
        builder.append(CATEGORY_HEADER_LINE).append(System.lineSeparator());
        builder.append(category.getTitle().toUpperCase()).append(System.lineSeparator());
        builder.append(CATEGORY_HEADER_LINE).append(System.lineSeparator());
        builder.append(String.format("%-20s %-20s %-20s", "Product Name", "Quantity", "Unit Price"))
                .append(System.lineSeparator());
    }

    private void appendProductLine(StringBuilder builder, Product product, int quantity) {
        builder.append(
                String.format("%-20s %-20s %-20s", product.getTitle(), quantity,
                        decimalFormat.format(product.getPrice())))
                .append(System.lineSeparator());
    }

    private void appendCategoryLineBreaks(StringBuilder builder) {
        addLineSeparators(builder, CATEGORY_END_LINEBREAK_NUMBER);
    }

    private void addLineSeparators(StringBuilder builder, int numberOfSeparators) {
        builder.append(String.valueOf(System.lineSeparator()).repeat(Math.max(0, numberOfSeparators)));
    }
}
