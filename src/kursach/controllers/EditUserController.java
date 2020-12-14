package kursach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kursach.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditUserController {

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

    public static User user;

    @FXML
    void initialize() {
        labelNameCompany.setText(Const.COMPANY_NAME);
        logo.setImage(new Image(Const.urlLogo));
        nameField.setText(user.getName());
        lastnameField.setText(user.getLastName());
        passportNField.setText(user.getnPassport());
        iDPassField.setText(user.getnIdPassport());
        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        checkPasswordField.setText(user.getPassword());
        emailField.setText(user.getEmail());
        telField.setText(user.getnPhone());
        birthdayField.setValue(LocalDate.parse(user.getBirthday(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        cancelButton.setOnAction(actionEvent -> {
            user = null;
            ControlWindows.ChangeWindows(cancelButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });

        registrButton.setOnAction(actionEvent -> {
            labelError.setText("");
            try {
                StringBuffer builder = new StringBuffer();
                builder.append(passportNField.getText()).append(Const.DELIMITER)
                        .append(iDPassField.getText()).append(Const.DELIMITER)
                        .append(telField.getText()).append(Const.DELIMITER)
                        .append(emailField.getText()).append(Const.DELIMITER)
                        .append(passwordField.getText()).append(Const.DELIMITER);

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                builder.append(dateFormatter.format(birthdayField.getValue())).append(Const.DELIMITER)
                        .append(loginField.getText()).append(Const.DELIMITER)
                        .append(nameField.getText()).append(Const.DELIMITER)
                        .append(lastnameField.getText()).append(Const.DELIMITER);

                TestInput.editUserFieldsAreOk(builder.toString().split(Const.DELIMITER));  /// проверка
                builder.append(user.getId());
                String answer = CommunicationWithServer.request(Code.ADMIN_EDIT_USER, builder.toString());
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
