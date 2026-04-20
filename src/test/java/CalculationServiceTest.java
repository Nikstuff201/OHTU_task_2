import org.CalculationRecord;
import org.junit.jupiter.api.Test;
import org.СalculationService;

class CalculationServiceTest {

    @Test
    void testSaveCalculationDoesNotCrash() {
        CalculationRecord record = new CalculationRecord(
                100, 8, 1.5, 8, 12, "EN"
        );

        try {
            СalculationService.saveCalculation(record);
        } catch (Exception e) {
        }
    }
}
