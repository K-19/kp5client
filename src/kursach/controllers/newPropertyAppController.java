package kursach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kursach.*;

public class newPropertyAppController {

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
    private TextField adressField;

    @FXML
    private TextField areaField;

    @FXML
    private TextField roomsField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField floorField;

    @FXML
    private TextField doorsField;

    @FXML
    private TextField termField;

    @FXML
    private CheckBox steelDoorCheck;

    @FXML
    private CheckBox codelockCheck;

    @FXML
    private CheckBox unfireSystemCheck;

    @FXML
    private CheckBox securityCheck;

    @FXML
    private CheckBox unfireMaterialCheck;

    @FXML
    private CheckBox highRiskCheck;

    @FXML
    void initialize() {
        logo.setImage(new Image(Const.urlLogo));
        labelNameCompany.setText(Const.COMPANY_NAME);
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

        enterButton.setOnAction(actionEvent -> {
            String adress = adressField.getText();
            String area = areaField.getText();
            String rooms = roomsField.getText();
            String year = yearField.getText();
            String floor = floorField.getText();
            String doors = doorsField.getText();
            String term = termField.getText();
            Boolean steelDoor = steelDoorCheck.isSelected();
            Boolean codeLock = codelockCheck.isSelected();
            Boolean unfireSystem = unfireSystemCheck.isSelected();
            Boolean security = securityCheck.isSelected();
            Boolean unfireMaterial = unfireMaterialCheck.isSelected();
            Boolean highRisk = highRiskCheck.isSelected();
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
                TestInput.propertyAppFieldsAreOk(adress, area, rooms, year, floor, doors, term);

                String idUser = Integer.toString(User.getUser().getId());

                String message = CommunicationWithServer.createMessageForServer(idUser, term, fee, typeMoney, doljn, adress, area, rooms, year, floor, doors,
                        Boolean.toString(steelDoor), Boolean.toString(codeLock), Boolean.toString(unfireSystem), Boolean.toString(security), Boolean.toString(unfireMaterial), Boolean.toString(highRisk));
                System.out.println(message);

                ///Сохранение в базу данных
                String answer = CommunicationWithServer.request(Code.NEW_APP_PROPERTY, message);

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
