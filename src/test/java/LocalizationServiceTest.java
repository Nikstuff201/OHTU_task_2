

import org.LocalizationService;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    @Test
    void testSingleton() {
        LocalizationService s1 = LocalizationService.getInstance();
        LocalizationService s2 = LocalizationService.getInstance();

        assertSame(s1, s2);
    }

    @Test
    void testGetStringDefault() {
        LocalizationService service = LocalizationService.getInstance();

        String result = service.getString("unknown.key");

        assertEquals("unknown.key", result);
    }

    @Test
    void testGetAllKeysEmpty() {
        LocalizationService service = LocalizationService.getInstance();

        Set<String> keys = service.getAllKeys();

        assertNotNull(keys);
    }


}