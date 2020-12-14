package kursach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kursach.*;

public class newVehicleAppController {

    @FXML
    private ImageView logo;

    @FXML
    private Button enterButton;

    @FXML
    private Label labelNameCompany;

    @FXML
    private Button cancelButton;

    @FXML
    private Label labelError;

    @FXML
    private CheckBox checkElectric;

    @FXML
    private Label batteryLabel;

    @FXML
    private RadioButton feeRadio1;

    @FXML
    private RadioButton feeRadio2;

    @FXML
    private RadioButton nalRadio;

    @FXML
    private RadioButton beznalRadio2;

    @FXML
    private CheckBox checkDoljn;

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
    private TextField batteryField;

    @FXML
    private TextField termField;

    @FXML
    private TextField expField;

    @FXML
    void initialize() {
        logo.setImage(new Image(Const.urlLogo));
        labelNameCompany.setText(Const.COMPANY_NAME);
        batteryLabel.setDisable(true);
        batteryField.setDisable(true);
        ToggleGroup group1 = new ToggleGroup();
        feeRadio1.setToggleGroup(group1);
        feeRadio2.setToggleGroup(group1);
        feeRadio1.setSelected(true);
        ToggleGroup group2 = new ToggleGroup();
        nalRadio.setToggleGroup(group2);
        beznalRadio2.setToggleGroup(group2);
        nalRadio.setSelected(true);

        cancelButton.setOnAction(actionEvent -> {
            ControlWindows.ChangeWindows(cancelButton, getClass(), "/kursach/fxmls/mainMenu.fxml");
        });

        checkElectric.setOnAction(actionEvent -> {
            if (checkElectric.isSelected()) {
                batteryField.setDisable(false);
                batteryLabel.setDisable(false);
            } else {
                batteryLabel.setDisable(true);
                batteryField.setDisable(true);
            }
        });

        enterButton.setOnAction(actionEvent -> {
            String model = modelField.getText();
            String regNumber = regNumbField.getText();
            String year = yeadField.getText();
            String bodyNumb = bodyNumbField.getText();
            String vEngine = vEngineField.getText();
            String lifting = liftingField.getText();
            String seats = numbSeatsField.getText();
            String powerEngine = powerEngineField.getText();
            String accidents = numbAccidentField.getText();
            String term = termField.getText();
            String experience = expField.getText();
            String battery = "not";
            if (checkElectric.isSelected()) battery = batteryField.getText();
            String fee;
            if (feeRadio1.isSelected()) fee = "Единовременно";
            else fee = "Поэтапно";
            String typeMoney;
            if(nalRadio.isSelected()) typeMoney = "Наличными";
            else typeMoney = "Банковской картой";
            String doljn;
            if (checkDoljn.isSelected()) doljn = "Должностное лицо";
            else doljn = "Не является должностным лицом";
            try {
                TestInput.vehicleAppFieldsAreOk(model, regNumber, year, bodyNumb, vEngine, lifting, seats, powerEngine, accidents, battery, term, experience);

                String iDuser = Integer.toString(User.getUser().getId());

                String message = CommunicationWithServer.createMessageForServer(iDuser, term, "", fee, typeMoney, doljn, experience, model, regNumber, bodyNumb, vEngine, lifting, seats, powerEngine, accidents, year, battery);
                System.out.println(message);

                ///Сохранение в базу данных
                String answer = CommunicationWithServer.request(Code.NEW_APP_VEHICLE, message);

                if (!"true".equals(answer)) labelError.setText("!!! " + answer);
                else ControlWindows.ChangeWindows(enterButton, getClass(), "/kursach/fxmls/mainMenu.fxml");

            } catch(NullPointerException e) {
                labelError.setText("!!! Все поля должны быть заполнены");
            } catch (Exception e) {
                labelError.setText("!!! " + e.toString());
            }
        });
    }
}
