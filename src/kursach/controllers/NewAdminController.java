package kursach.controllers;

import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kursach.*;

public class NewAdminController {

    @FXML
    private ImageView logo;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrButton;

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
    private TextField passportNField;

    @FXML
    private TextField iDPassField;

    @FXML
    private Label labelNameCompany;

    @FXML
    private Button cancelButton;

    @FXML
    private Label labelError;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField nameField;

    @FXML
    void initialize() {
        logo.setImage(new Image(Const.urlLogo));
        labelNameCompany.setText(Const.COMPANY_NAME);

        cancelButton.setOnAction(actionEvent -> {
            ControlWindows.ChangeWindows(cancelButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });

        registrButton.setOnAction(actionEvent -> {
            labelError.setText("");
            try {
                StringBuffer builder = new StringBuffer();
                builder.append(checkPasswordField.getText()).append(Const.DELIMITER).append(passwordField.getText()).append(Const.DELIMITER).append(passportNField.getText()).append(Const.DELIMITER);
                builder.append(iDPassField.getText()).append(Const.DELIMITER).append(telField.getText()).append(Const.DELIMITER).append(emailField.getText()).append(Const.DELIMITER);
                builder.append(loginField.getText()).append(Const.DELIMITER);

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                builder.append(dateFormatter.format(birthdayField.getValue())).append(Const.DELIMITER);

                builder.append("1");
                builder.append(Const.DELIMITER);
                builder.append("1");
                builder.append(Const.DELIMITER);
                builder.append(nameField.getText()).append(Const.DELIMITER).append(lastnameField.getText());

                TestInput.registrationFieldsAreOk(builder.toString().split(Const.DELIMITER));  /// проверка
                String answer = CommunicationWithServer.request(Code.NEW_ADMIN, builder.toString());
                if (!"true".equals(answer)) labelError.setText("!!! " + answer);
                else ControlWindows.ChangeWindows(registrButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
            } catch(NullPointerException e) {
                labelError.setText("!!! Все поля должны быть заполнены");
            } catch (Exception e) {
                labelError.setText("!!! " + e.toString());
            }
        });
    }
}
