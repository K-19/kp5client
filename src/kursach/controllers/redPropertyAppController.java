package kursach.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
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
import kursach.apps.Property;
import kursach.apps.PropertyInsuranceApplication;
import kursach.apps.Vehicle;
import kursach.apps.VehicleInsuranceApplication;
import kursach.contracts.Contract;
import kursach.exceptions.ExcperianceException;
import kursach.exceptions.TermAppException;

public class redPropertyAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView logo;

    @FXML
    private Button enterButton;

    @FXML
    private Label supportText;

    @FXML
    private Label labelNameCompany;

    @FXML
    private Button cancelButton;

    @FXML
    private Label mainErrorLabel;

    @FXML
    private TextField addressField;

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
    private Label propertyErrorField;

    @FXML
    private Label userErrorLabel;

    @FXML
    private TextField sumMoneyField;

    @FXML
    private TextField perMonthField;

    @FXML
    private Button moneyButton;

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
    private Button deleteButton;


    public static PropertyInsuranceApplication application;

    public static Contract contract;

    @FXML
    void initialize() {
        labelNameCompany.setText(Const.COMPANY_NAME);
        logo.setImage(new Image(Const.urlLogo));
        redPropertyAppController.contract = null;

        addressField.setText(application.getProperty().getAddress());
        areaField.setText(Integer.toString(application.getProperty().getArea()));
        roomsField.setText(Integer.toString(application.getProperty().getRooms()));
        yearField.setText(Integer.toString(application.getProperty().getYear()));
        floorField.setText(Integer.toString(application.getProperty().getFloor()));
        doorsField.setText(Integer.toString(application.getProperty().getDoors()));
        if (application.getProperty().isSteelDoor()) steelDoorCheck.setSelected(true);
        if (application.getProperty().isCombinationLock()) codeLockCheck.setSelected(true);
        if (application.getProperty().isFireAlarm()) unfireSystemCheck.setSelected(true);
        if (application.getProperty().isSecurityAlarm()) securityCheck.setSelected(true);
        if (application.getProperty().isRefractoryBuildingMaterials()) materialCheck.setSelected(true);
        if (application.getProperty().isHighRiskArea()) riskCheck.setSelected(true);

        userNameField.setText(application.getUser().getName());
        userLastnameField.setText(application.getUser().getLastName());
        userPasportField.setText(application.getUser().getnPassport());
        userIdPasportField.setText(application.getUser().getnIdPassport());
        userPhoneField.setText(application.getUser().getnPhone());
        userEmailField.setText(application.getUser().getEmail());

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
                TestInput.propertyAppFieldsAreOk(addressField.getText(), areaField.getText(), roomsField.getText(),
                        yearField.getText(), floorField.getText(), doorsField.getText(), termField.getText());

                Boolean execute = false;
                if ("Является должностным лицом".equals(userDoljField.getText())) execute = true;

                String answer = CommunicationWithServer.request(Code.CALCULATE, CommunicationWithServer.createMessageForServer("2",
                        areaField.getText(),
                        roomsField.getText(),
                        yearField.getText(),
                        floorField.getText(),
                        doorsField.getText(),
                        Boolean.toString(steelDoorCheck.isSelected()),
                        Boolean.toString(codeLockCheck.isSelected()),
                        Boolean.toString(unfireSystemCheck.isSelected()),
                        Boolean.toString(securityCheck.isSelected()),
                        Boolean.toString(materialCheck.isSelected()),
                        Boolean.toString(riskCheck.isSelected()),
                        Boolean.toString(execute),
                        termField.getText()));

                String[] ans = answer.split(Const.DELIMITER);
                Pair <Double, Double> result = new Pair<>(Double.parseDouble(ans[0]), Double.parseDouble(ans[1]));


//                Pair<Double, Double> result = Calculate.calculateProperty(Integer.parseInt(areaField.getText()), Integer.parseInt(roomsField.getText()),
//                        Integer.parseInt(yearField.getText()), Integer.parseInt(floorField.getText()),
//                        Integer.parseInt(doorsField.getText()), steelDoorCheck.isSelected(), codeLockCheck.isSelected(),
//                        unfireSystemCheck.isSelected(), securityCheck.isSelected(), materialCheck.isSelected(), riskCheck.isSelected(),
//                        execute, Integer.parseInt(termField.getText()));
                sumMoneyField.setText(Integer.toString(result.getKey().intValue()));
                perMonthField.setText(Integer.toString(result.getValue().intValue()));
            } catch (Exception e) {
                mainErrorLabel.setText(e.toString());
            }
        });

        enterButton.setOnAction(actionEvent -> {
            mainErrorLabel.setText("");
            userErrorLabel.setText("");
            propertyErrorField.setText("");
            try {
                TestInput.propertyStrahIsOk(addressField.getText(), areaField.getText(), roomsField.getText(), yearField.getText(),
                        floorField.getText(),doorsField.getText());
                try {
                    TestInput.userStrahIsOk(userNameField.getText(), userLastnameField.getText(), userPasportField.getText(), userIdPasportField.getText(),
                            userPhoneField.getText(), userEmailField.getText(), userDoljField.getText());
                    try {
                        TestInput.mainStrahIsOk(termField.getText(), feeField.getText(), beginTermField.getText(), moneyField.getText(), sumMoneyField.getText(), perMonthField.getText());



                        // Регистрация нового договора
                        //String message = concatString(application.get)
                        Boolean fee = false, payment = false, execute = false;
                        if ("Единовременно".equals(feeField.getText())) fee = true;
                        if ("Наличными".equals(moneyField.getText())) payment = true;
                        if ("Является должностным лицом".equals(userDoljField.getText())) execute = true;

                        User contractUser = new User(application.getUser().getId(), userPasportField.getText(), userIdPasportField.getText(), userPhoneField.getText(),
                                userEmailField.getText(), application.getUser().getPassword(), application.getUser().getBirthday(), application.getUser().getRegistrDay(),
                                application.getUser().getAdmin(), application.getUser().getLogin(), userNameField.getText(), userLastnameField.getText());
                        Property contractProperty = new Property(addressField.getText(), Integer.parseInt(areaField.getText()), Integer.parseInt(roomsField.getText()),
                                Integer.parseInt(yearField.getText()), Integer.parseInt(floorField.getText()), steelDoorCheck.isSelected(),
                                Integer.parseInt(doorsField.getText()), codeLockCheck.isSelected(),unfireSystemCheck.isSelected(),securityCheck.isSelected(),
                                materialCheck.isSelected(), riskCheck.isSelected());
                        PropertyInsuranceApplication contractApp = new PropertyInsuranceApplication(application.getId(), contractUser, Long.parseLong(termField.getText()),
                                new SimpleDateFormat("dd-MM-yyyy").parse(beginTermField.getText()),
                                fee, payment, execute, contractProperty);

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
                propertyErrorField.setText(e.toString());
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            CommunicationWithServer.request(Code.DELETE_APP, Integer.toString(application.getId()));
            ControlWindows.ChangeWindows(deleteButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });
    }
}
