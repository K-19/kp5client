package kursach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kursach.*;
import kursach.exceptions.TermAppException;

public class newHealthAppController {

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
    private TextField termField;

    @FXML
    private CheckBox covidCheck;

    @FXML
    private CheckBox harmHappendCheck;

    @FXML
    private CheckBox healthHappendCheck;

    @FXML
    private CheckBox invaidHappendCheck;

    @FXML
    private CheckBox deathIllCheck;

    @FXML
    private CheckBox sportCheck;

    @FXML
    private CheckBox relaxCheck;

    @FXML
    private CheckBox invalid12Check;

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
            Boolean harmHappend = harmHappendCheck.isSelected();
            Boolean healthHappend = healthHappendCheck.isSelected();
            Boolean invalidHappend = invaidHappendCheck.isSelected();
            Boolean deathIll = deathIllCheck.isSelected();
            Boolean covid = covidCheck.isSelected();
            String term = termField.getText();
            Boolean sport = sportCheck.isSelected();
            Boolean relax = relaxCheck.isSelected();
            Boolean invalid12 = invalid12Check.isSelected();
            String fee;
            if (feeRadio1.isSelected()) fee = "Единовременно";
            else fee = "Поэтапно";
            String typeMoney;
            if (nalRadio.isSelected()) typeMoney = "Наличными";
            else typeMoney = "Банковской картой";
            String doljn;
            if (checkDoljn.isSelected()) doljn = "Должностное лицо";
            else doljn = "Не является должностным лицом";

            try {
                TestInput.testIntValue(term, 1, Const.MAX_TERM_YEARS, new TermAppException());

                String idUser = Integer.toString(User.getUser().getId());

                String message = CommunicationWithServer.createMessageForServer(idUser, term, fee, typeMoney, doljn,
                        Boolean.toString(harmHappend), Boolean.toString(healthHappend), Boolean.toString(invalidHappend), Boolean.toString(deathIll), Boolean.toString(covid),
                        Boolean.toString(sport), Boolean.toString(relax), Boolean.toString(invalid12));
                System.out.println(message);

                ///Сохранение в базу данных
                String answer = CommunicationWithServer.request(Code.NEW_APP_HEALTH, message);

                if (!"true".equals(answer)) labelError.setText("!!! " + answer);
                else ControlWindows.ChangeWindows(enterButton, getClass(), "/kursach/fxmls/mainMenu.fxml");

            } catch (NullPointerException e) {
                labelError.setText("!!! Все поля должны быть заполнены");
            } catch (Exception e) {
                labelError.setText("!!! " + e.toString());
            }
        });
    }
}
