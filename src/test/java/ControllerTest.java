

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.Controller;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeAll
    static void initJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(() -> {
            latch.countDown();
        });

        latch.await();
    }

    private Controller controller;

    @BeforeEach
    void setup() {
        controller = new Controller();

        // эмуляция UI
        controller.root = new VBox();
        controller.lblDistance = new Label();
        controller.lblConsumption = new Label();
        controller.lblPrice = new Label();
        controller.lblResult = new Label();

        controller.txtDistance = new TextField();
        controller.txtConsumption = new TextField();
        controller.txtPrice = new TextField();

        controller.btnCalculate = new Button();
        controller.languageSelection = new ChoiceBox<>();

        controller.initialize();
    }

    @Test
    void testInitialize() {
        assertEquals("en", controller.languageSelection.getValue());
        assertTrue(controller.languageSelection.getItems().contains("fr"));
    }

    @Test
    void testCalculateFuelSuccess() {
        controller.txtDistance.setText("100");
        controller.txtConsumption.setText("5");
        controller.txtPrice.setText("2");

        controller.calculateFuel();

        assertTrue(controller.lblResult.isVisible());
    }

    @Test
    void testEmptyInput() {
        controller.txtDistance.setText("");
        controller.txtConsumption.setText("5");
        controller.txtPrice.setText("2");

        controller.calculateFuel();

        assertTrue(controller.lblResult.isVisible());
    }

    @Test
    void testInvalidNumber() {
        controller.txtDistance.setText("abc");
        controller.txtConsumption.setText("5");
        controller.txtPrice.setText("2");

        controller.calculateFuel();

        assertTrue(controller.lblResult.isVisible());
    }

    @Test
    void testNegativeValues() {
        controller.txtDistance.setText("-10");
        controller.txtConsumption.setText("5");
        controller.txtPrice.setText("2");

        controller.calculateFuel();

        assertTrue(controller.lblResult.isVisible());
    }

    @Test
    void testSetLocale() {
        controller.setLocale("fa");

        assertNotNull(controller);
    }
}