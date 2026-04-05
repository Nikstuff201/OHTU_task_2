package org;

import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.text.MessageFormat;
import java.util.Locale;


public class Controller {

    private Locale locale;
    private double lastFuel;
    private double lastCost;
    private boolean hasResult = false;
    private boolean isInputRight = true;
    private LocalizationService localizationService = LocalizationService.getInstance();

    @FXML
    VBox root;

    @FXML
    Label lblDistance;

    @FXML
    Label lblConsumption;

    @FXML
    Label lblPrice;

    @FXML
    Label lblResult;

    @FXML
    TextField txtDistance;

    @FXML
    TextField txtConsumption;

    @FXML
    TextField txtPrice;

    @FXML
    Button btnCalculate;

    @FXML
    ChoiceBox<String> languageSelection;

    @FXML
    public void initialize() {
        languageSelection.getItems().addAll("en", "fr", "fa", "ja");
        languageSelection.setValue("en");
        languageSelection.setOnAction(event -> {
            locale = new Locale(languageSelection.getValue());
            LocalizationService localizationService = LocalizationService.getInstance();
            localizationService.loadStrings(languageSelection.getValue());
            updateTexts();
            updateResult();
        });
        locale = new Locale("en");

        root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        updateTexts();
    }


    @FXML
    private void calculateFuel() {
        try {

            String distanceText = txtDistance.getText();
            String consumptionText = txtConsumption.getText();
            String priceText = txtPrice.getText();

            if (distanceText.isEmpty() || consumptionText.isEmpty() || priceText.isEmpty()) {
                lblResult.setText(localizationService.getString("invalid.input"));
                isInputRight = false;
                lblResult.setVisible(true);
                return;
            }

            double distance = Double.parseDouble(distanceText);
            double consumption = Double.parseDouble(consumptionText);
            double price = Double.parseDouble(priceText);

            if (distance <= 0 || consumption <= 0 || price <= 0) {
                lblResult.setText("invalid.input");
                isInputRight = false;
                lblResult.setVisible(true);
                return;
            }

            double fuelNeeded = distance * consumption / 100.0;
            double totalCost = fuelNeeded * price;
            lastFuel = fuelNeeded;
            lastCost = totalCost;
            hasResult = true;

            String resultPattern = localizationService.getString("result.label");

            String result = MessageFormat.format(
                    resultPattern,
                    String.format("%.2f", fuelNeeded),
                    String.format("%.2f", totalCost)
            );

            lblResult.setText(result);
            СalculationService.saveCalculation(new CalculationRecord(distance, consumption, price, fuelNeeded, totalCost, "en"));
            isInputRight = true;
            lblResult.setVisible(true);
        } catch (NumberFormatException e) {
            lblResult.setText("invalid.input");
            isInputRight = false;
            lblResult.setVisible(true);
        }

    }

    private void updateTexts() {
        lblDistance.setText(localizationService.getString("distance.label"));
        lblConsumption.setText(localizationService.getString("consumption.label"));
        lblPrice.setText(localizationService.getString("price.label"));

        btnCalculate.setText(localizationService.getString("calculate.button"));

        if (locale.getLanguage().equals("fa")){
            root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
        else {
            root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }

    private void updateResult(){
        if (!isInputRight){
            lblResult.setText(localizationService.getString("invalid.input"));
            return;
        }
        if (!hasResult){
            return;
        }
        String resultPattern = localizationService.getString("result.label");
        String result = MessageFormat.format(
                resultPattern,
                String.format("%.2f", lastFuel),
                String.format("%.2f", lastCost)
        );
        lblResult.setText(result);



    }


    public void setLocale(String language) {
         this.locale = new Locale(language);
    }
}

