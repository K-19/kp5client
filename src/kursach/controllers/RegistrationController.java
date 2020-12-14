package kursach.controllers;

import java.net.Socket;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kursach.*;

public class RegistrationController {


    @FXML
    private ImageView logo;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField checkPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField telField;

    @FXML
    private DatePicker birthdayField;

    @FXML
    private CheckBox checkBoxAgreementData;

    @FXML
    private TextField iDPassField;

    @FXML
    private TextField passportNField;

    @FXML
    private CheckBox checkBoxAgreementLic;

    @FXML
    private Label labelNameCompany;

    @FXML
    private Label labelError;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField nameField;

    @FXML
    void initialize() {
        labelNameCompany.setText(Const.COMPANY_NAME);
        logo.setImage(new Image(Const.urlLogo));
        registrButton.setOnAction(actionEvent -> {
            Socket mySocket;
            labelError.setText("");
            try {
            mySocket = MySingletonSocket.getInstance();

            StringBuffer builder = new StringBuffer();
            builder.append(checkPasswordField.getText()).append(Const.DELIMITER).append(passwordField.getText()).append(Const.DELIMITER).append(passportNField.getText()).append(Const.DELIMITER);
            builder.append(iDPassField.getText()).append(Const.DELIMITER).append(telField.getText()).append(Const.DELIMITER).append(emailField.getText()).append(Const.DELIMITER);
            builder.append(loginField.getText()).append(Const.DELIMITER);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            builder.append(dateFormatter.format(birthdayField.getValue())).append(Const.DELIMITER);

            if (checkBoxAgreementData.isSelected()) builder.append("1");
            else builder.append("0");
            builder.append(Const.DELIMITER);
            if (checkBoxAgreementLic.isSelected()) builder.append("1");
            else builder.append("0");
                builder.append(Const.DELIMITER);
            builder.append(nameField.getText()).append(Const.DELIMITER).append(lastnameField.getText());

            TestInput.registrationFieldsAreOk(builder.toString().split(Const.DELIMITER));  /// проверка
            String answer = CommunicationWithServer.request(Code.REGISTRATION, builder.toString());
            if (!"true".equals(answer)) labelError.setText("!!! " + answer);
            else ControlWindows.ChangeWindows(registrButton, getClass(), "/kursach/fxmls/authorisation.fxml");
            } catch(NullPointerException e) {
                labelError.setText("!!! Все поля должны быть заполнены");
            } catch (Exception e) {
                labelError.setText("!!! " + e.toString());
            }
        });

        cancelButton.setOnAction(actionEvent -> {
            ControlWindows.ChangeWindows(cancelButton, getClass(), "/kursach/fxmls/authorisation.fxml");
        });
    }
}
