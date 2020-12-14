package kursach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import kursach.*;
import kursach.exceptions.NameFieldException;
import kursach.exceptions.TermAppException;

public class CalculatorController {

    @FXML
    private ImageView logo;

    @FXML
    private Label labelNameCompany;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField termField;

    @FXML
    private TextField sumMoneyField;

    @FXML
    private TextField perMonthField;

    @FXML
    private Button moneyButton;

    @FXML
    private Button carButton;

    @FXML
    private Button healthButton;

    @FXML
    private Button propertyButton;

    @FXML
    private AnchorPane carPane;

    @FXML
    private TextField modelField;

    @FXML
    private TextField regNumbField;

    @FXML
    private TextField yeadField;

    @FXML
    private TextField bodyNumbField;

    @FXML
    private TextField vEngineField;

    @FXML
    private TextField liftingField;

    @FXML
    private TextField numbSeatsField;

    @FXML
    private TextField powerEngineField;

    @FXML
    private TextField numbAccidentField;

    @FXML
    private TextField userExpDrivingField;

    @FXML
    private AnchorPane propertyPane;

    @FXML
    private TextField adresField;

    @FXML
    private TextField areaField;

    @FXML
    private TextField roomsField;

    @FXML
    private TextField yearPropertyField;

    @FXML
    private TextField floorField;

    @FXML
    private TextField doorsField;

    @FXML
    private CheckBox steelDoorCheck;

    @FXML
    private CheckBox codeLockCheck;

    @FXML
    private CheckBox unfireSystemCheck;

    @FXML
    private CheckBox securityCheck;

    @FXML
    private CheckBox materialCheck;

    @FXML
    private CheckBox riskCheck;

    @FXML
    private AnchorPane healthPane;

    @FXML
    private CheckBox sportCheck;

    @FXML
    private CheckBox relaxCheck;

    @FXML
    private CheckBox harmHapCheck;

    @FXML
    private CheckBox healthHapCheck;

    @FXML
    private CheckBox invalidHapCheck;

    @FXML
    private CheckBox deathCheck;

    @FXML
    private CheckBox covidCheck;

    @FXML
    private CheckBox invalid12Check;

    @FXML
    private CheckBox checkDolj;

    @FXML
    void initialize() {
        labelNameCompany.setText(Const.COMPANY_NAME);
        logo.setImage(new Image(Const.urlLogo));

        cancelButton.setOnAction(actionEvent -> {
            ControlWindows.ChangeWindows(cancelButton, getClass(), "/kursach/fxmls/mainMenu.fxml");
        });

        carButton.setOnAction(actionEvent -> {
            setVisibleAll(false, propertyPane, healthPane);
            if (!carPane.isVisible()) {
                carPane.setVisible(true);
                SetEmpty(yeadField, vEngineField, liftingField, numbSeatsField, powerEngineField, numbAccidentField, userExpDrivingField, termField);
            }
        });

        propertyButton.setOnAction(actionEvent -> {
            setVisibleAll(false, carPane, healthPane);
            if (!propertyPane.isVisible()) {
                propertyPane.setVisible(true);
                SetEmpty(areaField, roomsField, yearPropertyField, floorField, doorsField, termField);
            }
        });

        healthButton.setOnAction(actionEvent -> {
            setVisibleAll(false, propertyPane, carPane);
            if (!healthPane.isVisible()) {
                healthPane.setVisible(true);
                SetEmpty(termField);
            }
        });

        moneyButton.setOnAction(actionEvent -> {
            Pair<Double, Double> result;
            try {
                if (carPane.isVisible()) {

                    TestEmpty(yeadField, vEngineField, liftingField, numbSeatsField, powerEngineField, numbAccidentField, userExpDrivingField, termField);
                    TestInput.vehicleAppFieldsAreOk(modelField.getText(), regNumbField.getText(), yeadField.getText(), bodyNumbField.getText(),
                            vEngineField.getText(), liftingField.getText(), numbSeatsField.getText(), powerEngineField.getText(),
                            numbAccidentField.getText(), "not", termField.getText(), userExpDrivingField.getText());

                    String answer = CommunicationWithServer.request(Code.CALCULATE, CommunicationWithServer.createMessageForServer("1",
                            yeadField.getText(),
                            vEngineField.getText(),
                            liftingField.getText(),
                            numbSeatsField.getText(),
                            powerEngineField.getText(),
                            numbAccidentField.getText(),
                            userExpDrivingField.getText(),
                            Boolean.toString(checkDolj.isSelected()),
                            termField.getText()));

                    String[] ans = answer.split(Const.DELIMITER);
                    result = new Pair<>(Double.parseDouble(ans[0]), Double.parseDouble(ans[1]));
                } else if (propertyPane.isVisible()) {

                    TestEmpty(areaField, roomsField, yearPropertyField, floorField, doorsField, termField);
                    TestInput.propertyAppFieldsAreOk(adresField.getText(), areaField.getText(), roomsField.getText(), yearPropertyField.getText(), floorField.getText(), doorsField.getText(), termField.getText());

                    String answer = CommunicationWithServer.request(Code.CALCULATE, CommunicationWithServer.createMessageForServer("2",
                            areaField.getText(),
                            roomsField.getText(),
                            yearPropertyField.getText(),
                            floorField.getText(),
                            doorsField.getText(),
                            Boolean.toString(steelDoorCheck.isSelected()),
                            Boolean.toString(codeLockCheck.isSelected()),
                            Boolean.toString(unfireSystemCheck.isSelected()),
                            Boolean.toString(securityCheck.isSelected()),
                            Boolean.toString(materialCheck.isSelected()),
                            Boolean.toString(riskCheck.isSelected()),
                            Boolean.toString(checkDolj.isSelected()),
                            termField.getText()));

                    String[] ans = answer.split(Const.DELIMITER);
                    result = new Pair<>(Double.parseDouble(ans[0]), Double.parseDouble(ans[1]));
                } else if (healthPane.isVisible()) {

                    TestEmpty(termField);
                    TestInput.testIntValue(termField.getText(), 1, Const.MAX_TERM_YEARS, new TermAppException());

                    String answer = CommunicationWithServer.request(Code.CALCULATE, CommunicationWithServer.createMessageForServer("3",
                            Boolean.toString(harmHapCheck.isSelected()),
                            Boolean.toString(healthHapCheck.isSelected()),
                            Boolean.toString(invalidHapCheck.isSelected()),
                            Boolean.toString(deathCheck.isSelected()),
                            Boolean.toString(covidCheck.isSelected()),
                            Boolean.toString(sportCheck.isSelected()),
                            Boolean.toString(relaxCheck.isSelected()),
                            Boolean.toString(invalidHapCheck.isSelected()),
                            Boolean.toString(checkDolj.isSelected()),
                            termField.getText()));

                    String[] ans = answer.split(Const.DELIMITER);
                    result = new Pair<>(Double.parseDouble(ans[0]), Double.parseDouble(ans[1]));

                } else {
                    result = new Pair(.0, .0);
                }
                sumMoneyField.setText(Integer.toString(result.getKey().intValue()));
                perMonthField.setText(Integer.toString(result.getValue().intValue()));
            } catch (NameFieldException e) {
                sumMoneyField.setText("Все данные должны быть заполнены!!!");
                perMonthField.setText("Все данные должны быть заполнены!!!");
            } catch (Exception e) {
                sumMoneyField.setText(e.toString());
                perMonthField.setText(e.toString());
            }
        });
    }
    private void TestEmpty(TextField...fields) throws NameFieldException {
        for(TextField field : fields) {
            if ("".equals(field.getText())) throw new NameFieldException();
        }
    }

    private void SetEmpty(TextField...fields) {
        for (TextField field : fields) {
            field.setText("");
        }
    }

    private void setVisibleAll(boolean visible, Object...objects) {
        for (Object obj : objects) {
            if (obj instanceof Button) ((Button)obj).setVisible(visible);
            if (obj instanceof Label) ((Label)obj).setVisible(visible);
            if (obj instanceof AnchorPane) ((AnchorPane)obj).setVisible(visible);
        }
    }
}
