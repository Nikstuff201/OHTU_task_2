

import org.CalculationRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculationRecordTest {

    @Test
    void testGetters() {
        CalculationRecord record = new CalculationRecord(
                100.0, 8.0, 1.5, 8.0, 12.0, "EN"
        );

        assertEquals(100.0, record.getDistance());
        assertEquals(8.0, record.getConsumption());
        assertEquals(1.5, record.getPrice());
        assertEquals(8.0, record.getTotalFuel());
        assertEquals(12.0, record.getTotalCost());
        assertEquals("EN", record.getLanguage());
    }

    @Test
    void testIsValidTrue() {
        CalculationRecord rec = new CalculationRecord(
                100, 5, 2, 5, 10, "en"
        );

        assertTrue(rec.isValid());
    }
}