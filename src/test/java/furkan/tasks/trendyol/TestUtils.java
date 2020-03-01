package furkan.tasks.trendyol;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestUtils {
    public static double convertToTwoDecimalPlaces(double number) {
        BigDecimal decimal = new BigDecimal(number).setScale(2, RoundingMode.HALF_UP);

        return decimal.doubleValue();
    }
}
