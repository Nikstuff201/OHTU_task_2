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
import java.util.ResourceBundle;


public class Controller {

    ResourceBundle messageBundle;
    Locale locale;
    private double lastFuel;
    private double lastCost;
    private boolean hasResult = false;
    private boolean isInputRight = true;

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
            switch (languageSelection.getValue()) {
                case "en":
                    locale = new Locale("en", "US");
                    break;
                case "fr":
                    locale = new Locale("fr", "FR");
                    break;
                case "fa":
                    locale = new Locale("fa", "IR");
                    break;
                case "ja":
                    locale = new Locale("ja", "JP");
                    break;
            }

            messageBundle = ResourceBundle.getBundle("messages", locale);
            updateTexts();
            updateResult();
        });
        locale = new Locale("en", "US");
        messageBundle = ResourceBundle.getBundle("messages", locale);

        root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        updateTexts();
    }


    @FXML
    private void calculateFuel() {
        String wrongInput = messageBundle.getString("invalid.input");
        try {

            String distanceText = txtDistance.getText();
            String consumptionText = txtConsumption.getText();
            String priceText = txtPrice.getText();

            if (distanceText.isEmpty() || consumptionText.isEmpty() || priceText.isEmpty()) {
                lblResult.setText(wrongInput);
                isInputRight = false;
                lblResult.setVisible(true);
                return;
            }

            double distance = Double.parseDouble(distanceText);
            double consumption = Double.parseDouble(consumptionText);
            double price = Double.parseDouble(priceText);

            if (distance <= 0 || consumption <= 0 || price <= 0) {
                lblResult.setText(wrongInput);
                isInputRight = false;
                lblResult.setVisible(true);
                return;
            }

            double fuelNeeded = distance * consumption / 100.0;
            double totalCost = fuelNeeded * price;
            lastFuel = fuelNeeded;
            lastCost = totalCost;
            hasResult = true;

            String resultPattern = messageBundle.getString("result.label");

            String result = MessageFormat.format(
                    resultPattern,
                    String.format("%.2f", fuelNeeded),
                    String.format("%.2f", totalCost)
            );

            lblResult.setText(result);
            isInputRight = true;
            lblResult.setVisible(true);
        } catch (NumberFormatException e) {
            lblResult.setText(wrongInput);
            isInputRight = false;
            lblResult.setVisible(true);
        }

    }

    private void updateTexts() {
        lblDistance.setText(messageBundle.getString("distance.label"));
        lblConsumption.setText(messageBundle.getString("consumption.label"));
        lblPrice.setText(messageBundle.getString("price.label"));

        btnCalculate.setText(messageBundle.getString("calculate.button"));

        if (locale.getLanguage().equals("fa")){
            root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
        else {
            root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }

    private void updateResult(){
        if (!isInputRight){
            lblResult.setText(messageBundle.getString("invalid.input"));
            return;
        }
        if (!hasResult){
            return;
        }
        String resultPattern = messageBundle.getString("result.label");
        String result = MessageFormat.format(
                resultPattern,
                String.format("%.2f", lastFuel),
                String.format("%.2f", lastCost)
        );
        lblResult.setText(result);



    }



}

