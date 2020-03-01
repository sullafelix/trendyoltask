package furkan.tasks.trendyol;

import java.util.Map;

public class Campaign {
    private final Category category;
    private final double number;
    private final int minUnit;
    private final DiscountType discountType;



    public Campaign(Category category, double number, int minUnit, DiscountType discountType) {
        checkArguments(category, number, minUnit, discountType);
        this.category = category;
        this.number = number;
        this.minUnit = minUnit;
        this.discountType = discountType;
    }

    private void checkArguments(Category category, double number, int minUnit, DiscountType discountType) {
        StringBuilder errorMessage = new StringBuilder();
        if(category == null) {
            errorMessage.append("Category must not be null. ");
        }
        if(number <= 0) {
            errorMessage.append("Number must be greater than 0. ");
        }
        if(minUnit <= 0) {
            errorMessage.append("Minimum number of units must be greater than 0. ");
        }
        if(discountType == null) {
            errorMessage.append("Discount type must not be null.");
        }
        if(!errorMessage.toString().equals("")) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    public boolean isApplicable(Map<Category, Map<Product, Integer>> categoryItemMap) {
        int totalUnits = categoryItemMap.entrySet().stream()
                            .filter(categoryItemEntry -> categoryItemEntry.getKey().isParentOrSelf(this.category))
                            .mapToInt(categoryMapEntry ->
                                        categoryMapEntry.getValue().values().stream()
                                            .reduce(0, Integer::sum))
                            .sum();
        return totalUnits >= minUnit;
    }

    private double getCampaignTotal(Map<Category, Map<Product, Integer>> categoryItemMap) {
        return categoryItemMap.entrySet().stream()
                .filter(categoryItemEntry -> categoryItemEntry.getKey().isParentOrSelf(this.category))
                .mapToDouble(categoryMapEntry ->
                        categoryMapEntry.getValue().entrySet().stream()
                                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                                .sum())
                .sum();
    }

    public double getDiscountAmount(Map<Category, Map<Product, Integer>> categoryItemMap) {
        if(!isApplicable(categoryItemMap)) {
            return 0;
        }

        double totalAmountForCampaign = getCampaignTotal(categoryItemMap);

        return discountType.getDiscount(number, totalAmountForCampaign);
    }
}
