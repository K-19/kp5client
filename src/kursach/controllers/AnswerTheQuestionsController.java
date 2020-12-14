package kursach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kursach.*;
import kursach.Question;

public class AnswerTheQuestionsController {

    @FXML
    private ImageView logo;

    @FXML
    private Button enterButton;

    @FXML
    private Label labelNameCompany;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea textUserArea;

    @FXML
    private Label idUserLabel;

    @FXML
    private TextArea textAnswerArea;

    public static Question question;

    @FXML
    void initialize() {
        idUserLabel.setText("Сообщение от пользователя:");
        textUserArea.setText(question.getText());
        labelNameCompany.setText(Const.COMPANY_NAME);
        logo.setImage(new Image(Const.urlLogo));

        cancelButton.setOnAction(actionEvent -> {
            question = null;
            ControlWindows.ChangeWindows(cancelButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });

        deleteButton.setOnAction(actionEvent -> {
            CommunicationWithServer.request(Code.DELETE_QUESTION, Integer.toString(question.getId()));
            question = null;
            ControlWindows.ChangeWindows(deleteButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });

        enterButton.setOnAction(actionEvent -> {
            String result = concatString(Integer.toString(question.getId()), Integer.toString(question.getId_user()), question.getText(), Integer.toString(User.getUser().getId()), textAnswerArea.getText());
            CommunicationWithServer.request(Code.ANSWER_THE_QUESTION, result);
            question = null;
            ControlWindows.ChangeWindows(deleteButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });
    }

    private String concatString(String...strs) {
        String result = "";
        for(String temp : strs) {
            result += temp;
            result += Const.DELIMITER;
        }
        return result.substring(0, result.length() - 1);
    }
}
