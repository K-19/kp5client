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
import kursach.apps.HealthInsuranceApplication;
import kursach.apps.Property;
import kursach.apps.PropertyInsuranceApplication;
import kursach.contracts.Contract;
import kursach.exceptions.TermAppException;

public class redHealthAppController {

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
    private CheckBox sportCheck;

    @FXML
    private Button deleteButton;

    @FXML
    private CheckBox relaxCheck;

    @FXML
    private CheckBox invalid12Check;

    public static HealthInsuranceApplication application;
    public static Contract contract;

    @FXML
    void initialize() {
        labelNameCompany.setText(Const.COMPANY_NAME);
        logo.setImage(new Image(Const.urlLogo));
        redHealthAppController.contract = null;

        if (application.isHealthAccident()) harmHapCheck.setSelected(true);
        if (application.isLifeAccident()) healthHapCheck.setSelected(true);
        if (application.isInvalidAccident()) invalidHapCheck.setSelected(true);
        if (application.isDeathIllness()) deathCheck.setSelected(true);
        if (application.isHarmToHealthWithCovid19()) covidCheck.setSelected(true);
        if (application.isProfessionalSportsman()) sportCheck.setSelected(true);
        if (application.isLeisure()) relaxCheck.setSelected(true);
        if (application.isInvalidPerson()) invalid12Check.setSelected(true);

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
                TestInput.testIntValue(termField.getText(), 1, Const.MAX_TERM_YEARS, new TermAppException());

                Boolean execute = false;
                if ("Является должностным лицом".equals(userDoljField.getText())) execute = true;
                String answer = CommunicationWithServer.request(Code.CALCULATE, CommunicationWithServer.createMessageForServer("3",
                        Boolean.toString(harmHapCheck.isSelected()),
                        Boolean.toString(healthHapCheck.isSelected()),
                        Boolean.toString(invalidHapCheck.isSelected()),
                        Boolean.toString(deathCheck.isSelected()),
                        Boolean.toString(covidCheck.isSelected()),
                        Boolean.toString(sportCheck.isSelected()),
                        Boolean.toString(relaxCheck.isSelected()),
                        Boolean.toString(invalidHapCheck.isSelected()),
                        Boolean.toString(execute),
                        termField.getText()));

                String[] ans = answer.split(Const.DELIMITER);
                Pair <Double, Double> result = new Pair<>(Double.parseDouble(ans[0]), Double.parseDouble(ans[1]));
//                Pair<Double, Double> result = Calculate.calculateHealth(harmHapCheck.isSelected(), healthHapCheck.isSelected(), invalidHapCheck.isSelected(),
//                        deathCheck.isSelected(), covidCheck.isSelected(), sportCheck.isSelected(), relaxCheck.isSelected(),
//                        invalid12Check.isSelected(), execute, Integer.parseInt(termField.getText()));
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
                TestInput.userStrahIsOk(userNameField.getText(), userLastnameField.getText(), userPasportField.getText(), userIdPasportField.getText(),
                        userPhoneField.getText(), userEmailField.getText(), userDoljField.getText());
                try {
                    TestInput.mainStrahIsOk(termField.getText(), feeField.getText(), beginTermField.getText(), moneyField.getText(), sumMoneyField.getText(), perMonthField.getText());
                    Boolean fee = false, payment = false, execute = false;
                    if ("Единовременно".equals(feeField.getText())) fee = true;
                    if ("Наличными".equals(moneyField.getText())) payment = true;
                    if ("Является должностным лицом".equals(userDoljField.getText())) execute = true;

//                    User contractUser = new User(application.getUser().getId(), userPasportField.getText(), userIdPasportField.getText(), userPhoneField.getText(),
//                            userEmailField.getText(), application.getUser().getPassword(), application.getUser().getBirthday(), application.getUser().getRegistrDay(),
//                            application.getUser().getAdmin(), application.getUser().getLogin(), userNameField.getText(), userLastnameField.getText());
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
        });

        deleteButton.setOnAction(actionEvent -> {
            CommunicationWithServer.request(Code.DELETE_APP, Integer.toString(application.getId()));
            ControlWindows.ChangeWindows(deleteButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });
    }
}
