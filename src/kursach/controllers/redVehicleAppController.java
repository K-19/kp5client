package kursach.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import kursach.*;
import kursach.apps.VehicleInsuranceApplication;
import kursach.contracts.Contract;
import kursach.exceptions.ExcperianceException;
import kursach.exceptions.TermAppException;

public class redVehicleAppController {
    public static VehicleInsuranceApplication application;

    @FXML
    private ImageView logo;

    @FXML
    private Button enterButton;

    @FXML
    private Label labelNameCompany;

    @FXML
    private Button cancelButton;

    @FXML
    private Label mainErrorLabel;

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
    private TextField termField;

    @FXML
    private TextField userExpDrivingField;

    @FXML
    private TextField userEmailField;

    @FXML
    private TextField feeField;

    @FXML
    private TextField userPhoneField;

    @FXML
    private TextField userIdPasportField;

    @FXML
    private TextField userPasportField;

    @FXML
    private TextField userLastnameField;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField moneyField;

    @FXML
    private TextField beginTermField;

    @FXML
    private TextField userDoljField;

    @FXML
    private Label carErrorField;

    @FXML
    private Label userErrorLabel;

    @FXML
    private TextField sumMoneyField;

    @FXML
    private TextField perMonthField;

    @FXML
    private Button moneyButton;

    @FXML
    private Button deleteButton;

    public static Contract contract;

    @FXML
    void initialize() {
        labelNameCompany.setText(Const.COMPANY_NAME);
        logo.setImage(new Image(Const.urlLogo));
        redVehicleAppController.contract = null;
        modelField.setText(application.getVehicle().getModel());
        regNumbField.setText(application.getVehicle().getRegisterSign());
        yeadField.setText(Integer.toString(application.getVehicle().getYearOfCreated()));
        bodyNumbField.setText(application.getVehicle().getBodyNumber());
        vEngineField.setText(Double.toString(application.getVehicle().getEngineDisplacement()));
        liftingField.setText(Double.toString(application.getVehicle().getLiftingCapacity()));
        numbSeatsField.setText(Integer.toString(application.getVehicle().getNumberOfSeats()));
        powerEngineField.setText(Integer.toString(application.getVehicle().getEnginePower()));
        numbAccidentField.setText(Integer.toString(application.getVehicle().getRoadAccidents()));
        userNameField.setText(application.getUser().getName());
        userLastnameField.setText(application.getUser().getLastName());
        userPasportField.setText(application.getUser().getnPassport());
        userIdPasportField.setText(application.getUser().getnIdPassport());
        userPhoneField.setText(application.getUser().getnPhone());
        userEmailField.setText(application.getUser().getEmail());
        userExpDrivingField.setText(Integer.toString(application.getDrivingExperience()));
        if(application.isExecutePersson()) userDoljField.setText("Является должностным лицом");
        else userDoljField.setText("Не является должностным лицом");
        termField.setText(Long.toString(application.getTerm()));
        if(application.isInsuranceFee()) feeField.setText("Единовременно");
        else feeField.setText("Поэтапно");
        beginTermField.setText(new SimpleDateFormat("dd-MM-yyyy").format(application.getBeginTerm()));
        if(application.isTypeOfPayment()) moneyField.setText("Наличными");
        else moneyField.setText("Банковской картой");


        cancelButton.setOnAction(actionEvent -> {
            application = null;
            ControlWindows.ChangeWindows(cancelButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });

        moneyButton.setOnAction(actionEvent -> {
            mainErrorLabel.setText("");
            try {
                TestInput.vehicleAppFieldsAreOk("tesla", "1234AB-1", yeadField.getText(), "123456", vEngineField.getText(), liftingField.getText(),
                        numbSeatsField.getText(), powerEngineField.getText(), numbAccidentField.getText(), "not", termField.getText(), userExpDrivingField.getText());
               Boolean execute = false;
                if ("Является должностным лицом".equals(userDoljField.getText())) execute = true;
                String answer = CommunicationWithServer.request(Code.CALCULATE, CommunicationWithServer.createMessageForServer("1",
                        yeadField.getText(),
                        vEngineField.getText(),
                        liftingField.getText(),
                        numbSeatsField.getText(),
                        powerEngineField.getText(),
                        numbAccidentField.getText(),
                        userExpDrivingField.getText(),
                        Boolean.toString(execute),
                        termField.getText()));
                String[] ans = answer.split(Const.DELIMITER);
                Pair <Double, Double> result = new Pair<>(Double.parseDouble(ans[0]), Double.parseDouble(ans[1]));
                sumMoneyField.setText(Integer.toString(result.getKey().intValue()));
                perMonthField.setText(Integer.toString(result.getValue().intValue()));
            } catch (Exception e) {
                mainErrorLabel.setText(e.toString());
            }
        });

        enterButton.setOnAction(actionEvent -> {
            mainErrorLabel.setText("");
            userErrorLabel.setText("");
            carErrorField.setText("");
            try {
                TestInput.vehicleStrahIsOk(modelField.getText(), regNumbField.getText(), yeadField.getText(), bodyNumbField.getText(),
                        vEngineField.getText(),liftingField.getText(), numbSeatsField.getText(), powerEngineField.getText(), numbAccidentField.getText());
                try {
                    TestInput.userStrahIsOk2(userNameField.getText(), userLastnameField.getText(), userPasportField.getText(), userIdPasportField.getText(),
                            userPhoneField.getText(), userEmailField.getText(), userExpDrivingField.getText(), userDoljField.getText());
                    try {
                        TestInput.mainStrahIsOk(termField.getText(), feeField.getText(), beginTermField.getText(), moneyField.getText(), sumMoneyField.getText(), perMonthField.getText());


                        // Регистрация нового договора
                        //String message = concatString(application.get)
                        Boolean fee = false, payment = false, execute = false;
                        if ("Единовременно".equals(feeField.getText())) fee = true;
                        if ("Наличными".equals(moneyField.getText())) payment = true;
                        if ("Является должностным лицом".equals(userDoljField.getText())) execute = true;

                        Contract contract = new Contract(application.getId(), User.getUser().getId(), Integer.parseInt(new SimpleDateFormat("dd").format(new Date())),
                                Double.parseDouble(perMonthField.getText()), Double.parseDouble(sumMoneyField.getText()));
                        ViewContractController.contract = contract;

                        ControlWindows.ChangeWindows(enterButton, getClass(), "/kursach/fxmls/viewContract.fxml");
                    } catch (Exception e) {
                        mainErrorLabel.setText(e.toString());
                    }
                } catch (Exception e) {
                    userErrorLabel.setText(e.toString());
                }
            } catch (Exception e) {
                carErrorField.setText(e.toString());
            }
        });
        deleteButton.setOnAction(actionEvent -> {
            CommunicationWithServer.request(Code.DELETE_APP, Integer.toString(application.getId()));
            ControlWindows.ChangeWindows(deleteButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });
    }
}
