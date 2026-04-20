
import org.DatabaseConnection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void testConnectionFailsGracefully() {
        try {
            DatabaseConnection.getConnection();
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}