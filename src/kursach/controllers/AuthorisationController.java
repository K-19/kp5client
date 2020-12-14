package kursach.controllers;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kursach.*;
import kursach.exceptions.UserNotFoundException;

public class AuthorisationController {

    @FXML
    private ImageView logo;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button enterButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button buttonRegistr;

    @FXML
    private Label errorLabel;

    @FXML
    private Label lableNameCompany;

    @FXML
    void initialize() {
        lableNameCompany.setText(Const.COMPANY_NAME);
        logo.setImage(new Image(Const.urlLogo));
//        File file = new File("port");
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
//            while (reader.ready()) {
//                Const.PORT = Integer.parseInt(reader.readLine());
//            }
//            reader.close();
//            loginField.setText(Integer.toString(Const.PORT));
//        } catch (Exception e) {
//            try {
//                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//                writer.write("3345");
//                writer.flush();
//                writer.close();
//                Const.PORT = 3345;
//                loginField.setText(Integer.toString(Const.PORT));
//            } catch (IOException e1) {}
//        }
//        Socket checkServer = null;
//        for(int i = 0; i < 20; i++) {
//            try {
//                Const.PORT = 3345 + i;
//                checkServer = MySingletonSocket.getInstance();
//                loginField.setText(Integer.toString(Const.PORT));
//                break;
//            } catch (IOException e) {}
//        }
//        if(checkServer == null) loginField.setText("Сервер недоступен!!! Проверьте порт..." + Const.PORT);

        buttonRegistr.setOnAction(actionEvent -> {
            ControlWindows.ChangeWindows(buttonRegistr, getClass(), "/kursach/fxmls/registration.fxml");
        });

        enterButton.setOnAction(actionEvent -> {
            Socket mySocket;
            try { mySocket = MySingletonSocket.getInstance();
            TestInput.authFieldAreOk(loginField.getText(), passwordField.getText());

            StringBuffer builder = new StringBuffer();
            builder.append(loginField.getText()).append(Const.DELIMITER).append(passwordField.getText());

            String answer = CommunicationWithServer.request(Code.AUTHORISATION, builder.toString());///

            if("false".equals(answer)) throw new UserNotFoundException();
            String email, password, birthday, registrDay, login, nPass, nIdPass, nPhone, name, lastName;
            Integer iD;
            Boolean isAdmin;
            String[] answerArr = answer.split(Const.DELIMITER);
            iD = Integer.parseInt(answerArr[0]);
            nPass =  answerArr[1];
            nIdPass =  answerArr[2];
            nPhone =  answerArr[3];
            email =  answerArr[4].trim();
            password =  answerArr[5].trim();
            birthday =  answerArr[6].trim();
            registrDay =  answerArr[7].trim();
            isAdmin = Boolean.parseBoolean(answerArr[8]);
            login = answerArr[9].trim();
            name = answerArr[10].trim();
            lastName = answerArr[11].trim();
            User.setUser(new User(iD, nPass, nIdPass, nPhone, email, password, birthday, registrDay, isAdmin, login, name, lastName));
            String url = null;
            if (isAdmin) url = "/kursach/fxmls/adminMenu.fxml";
            else url = "/kursach/fxmls/mainMenu.fxml";
            ControlWindows.ChangeWindows(enterButton, getClass(), url);

            } catch(Exception e) {
                errorLabel.setText(e.toString());
            }
        });
    }
}
