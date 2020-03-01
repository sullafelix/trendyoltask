package furkan.tasks.trendyol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CampaignTest {
    @Test
    public void testConstructorIllegalArguments() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Campaign campaign = new Campaign(null, 0, 0, null);
        });
    }
}
