package kursach.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kursach.*;
import kursach.apps.AbstractInsuranceApplication;
import kursach.apps.HealthInsuranceApplication;
import kursach.apps.PropertyInsuranceApplication;
import kursach.apps.VehicleInsuranceApplication;
import kursach.contracts.Contract;
import kursach.contracts.SimpleContractForTable;
import kursach.Question;

public class adminMenuController {

    @FXML
    private AnchorPane usersPane;

    @FXML
    private Label labelUserName;

    @FXML
    private ImageView logo;

    @FXML
    private Button exitButton;

    @FXML
    private Label labelNameCompany;

    @FXML
    private Button usersButton;

    @FXML
    private Button contrctsButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button newAdminButton;

    @FXML
    private Button applicationsButton;

    @FXML
    private Button messagesButton;

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, Integer> usersColumnId;

    @FXML
    private TableColumn<User, String> usersColumnPasport;

    @FXML
    private TableColumn<User, String> usersColumnIdPasport;

    @FXML
    private TableColumn<User, String> usersColumnPhone;

    @FXML
    private TableColumn<User, String> usersColumnEmail;

    @FXML
    private TableColumn<User, String> usersColumnBirth;

    @FXML
    private TableColumn<User, String> usersColumnRegDay;

    @FXML
    private TableColumn<User, Boolean> usersColumnAdmin;

    @FXML
    private TableColumn<User, String> usersColumnLogin;

    @FXML
    private TableColumn<User, String> usersColumnName;

    @FXML
    private TableColumn<User, String> usersColumnLastname;

    @FXML
    private Label loadingLabel;

    @FXML
    private AnchorPane appPane;

    @FXML
    private Button startRedactButton;

    @FXML
    private TableView<App> applicationsTable;

    @FXML
    private TableColumn<App, Integer> AppidColumn;

    @FXML
    private TableColumn<App, String> ApptypeColumn;

    @FXML
    private TableColumn<App, String> AppobjectName;

    @FXML
    private TableColumn<App, String> AppobjectNumber;

    @FXML
    private AnchorPane contractsPane;

    @FXML
    private Button openContractButton;

    @FXML
    private TextField numbContractField;

    @FXML
    private TableView<SimpleContractForTable> contractsTable;

    @FXML
    private TableColumn<SimpleContractForTable, Integer> ContrIdColumn;

    @FXML
    private TableColumn<SimpleContractForTable, String> ContrTypeColumn;

    @FXML
    private TableColumn<SimpleContractForTable, String> ContrObjectName;

    @FXML
    private TableColumn<SimpleContractForTable, String> ContrObjectNumber;

    @FXML
    private TableColumn<SimpleContractForTable, Double> ContrSum;

    @FXML
    private TableColumn<SimpleContractForTable, Double> ContrPerMonth;

    @FXML
    private Button deleteContractButton;

    @FXML
    private Button filterButton;

    @FXML
    private TextField iDotField;

    @FXML
    private TextField iDdoField;

    @FXML
    private ChoiceBox<String> contractCombo;

    @FXML
    private TextField sumOtField;

    @FXML
    private TextField sumDoField;

    @FXML
    private TextField perOtField;

    @FXML
    private TextField perDoField;

    @FXML
    private Label filter2Label;

    @FXML
    private Label filter3Label;

    @FXML
    private Label filter1Label;

    @FXML
    private Button loadContractsButton;

    @FXML
    private Button editInfoUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private TextField idUserField;

    @FXML
    private AnchorPane supportPane;

    @FXML
    private Button answerTheQuestionButton;

    @FXML
    private TableView<Question> questionsTable;

    @FXML
    private TableColumn<Question, Integer> questIdUserColumn;

    @FXML
    private TableColumn<Question, String> questTextColumn;

    @FXML
    private AnchorPane aboutPane;

    @FXML
    private TextArea aboutTextArea;

    @FXML
    private Button confirmAbout;

    @FXML
    private AnchorPane vakansiiPane;

    @FXML
    private Button confirmVakansii;

    @FXML
    private TextArea vakansiiTextArea;

    @FXML
    private AnchorPane contactsPane;

    @FXML
    private TextArea contactsTextArea;

    @FXML
    private Button confirmContacts;

    @FXML
    private Button aboutButton;

    @FXML
    private Button vakansiiButton;

    @FXML
    private Button ContactsButton;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    void initialize() {
        logo.setImage(new Image(Const.urlLogo));
        labelNameCompany.setText(Const.COMPANY_NAME);
        labelUserName.setText("Администратор " + User.getUser().getLogin());
        setVisibleAll(false, usersPane, appPane, contractsPane, loadingLabel, supportPane, aboutPane, contactsPane, vakansiiPane);
        setDisableAll(true, iDotField, iDdoField, contractCombo, sumOtField, sumDoField, perOtField, perDoField, filter1Label, filter2Label, filter3Label);
        contractCombo.setValue("Все");
        contractCombo.setDisable(true);

        usersColumnId.setCellValueFactory(new PropertyValueFactory<>("iD"));
        usersColumnPasport.setCellValueFactory(new PropertyValueFactory<>("nPassport"));
        usersColumnIdPasport.setCellValueFactory(new PropertyValueFactory<>("nIdPassport"));
        usersColumnPhone.setCellValueFactory(new PropertyValueFactory<>("nPhone"));
        usersColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        usersColumnBirth.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        usersColumnRegDay.setCellValueFactory(new PropertyValueFactory<>("registrDay"));
        usersColumnAdmin.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        usersColumnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        usersColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        usersColumnLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        AppidColumn.setCellValueFactory(new PropertyValueFactory<>("numb"));
        ApptypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        AppobjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AppobjectNumber.setCellValueFactory(new PropertyValueFactory<>("number"));

        ContrIdColumn.setCellValueFactory(new PropertyValueFactory<>("numb"));
        ContrTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        ContrObjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ContrObjectNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        ContrSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        ContrPerMonth.setCellValueFactory(new PropertyValueFactory<>("perMonth"));

        questIdUserColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        questTextColumn.setCellValueFactory(new PropertyValueFactory<>("text"));

        EditUserController.user = null;
        exitButton.setOnAction(actionEvent -> {
            User.setUser(null);
            ControlWindows.ChangeWindows(exitButton, getClass(), "/kursach/fxmls/authorisation.fxml");
        });

        filterButton.setOnAction(actionEvent -> {
            Boolean disable = true;
            if (iDotField.isDisable()) disable = false;
            setDisableAll(disable, iDotField, iDdoField, contractCombo, sumOtField, sumDoField, perOtField, perDoField, filter1Label, filter2Label, filter3Label);
            contractCombo.setDisable(disable);
        });

        deleteUserButton.setOnAction(actionEvent -> {
            int id;
            try {
                id = Integer.parseInt(idUserField.getText());

                String answer = CommunicationWithServer.request(Code.DELETE_USER, Integer.toString(id));
                if ("true".equals(answer)) idUserField.setText("Удалён");
                else idUserField.setText(answer);
            } catch (Exception e) {
                idUserField.setText("Не найден");
            }
        });

        editInfoUserButton.setOnAction(actionEvent -> {
            int id;
            String answer;
            try {
                id = Integer.parseInt(idUserField.getText());
                answer = CommunicationWithServer.request(Code.ADMIN_USER_INFO, Integer.toString(id));
                if (answer == null || answer.equals("")) {
                    idUserField.setText("Произошла ошибка");
                } else {
                    EditUserController.user = new User(answer);
                    ControlWindows.ChangeWindows(editInfoUserButton, getClass(), "/kursach/fxmls/editUser.fxml");
                }
            } catch (Exception e) {
                idUserField.setText(e.toString());
            }
        });

        usersButton.setOnAction(actionEvent -> {
            if (openPane(usersPane)) {
                loadingLabel.setVisible(true);
                String answer = CommunicationWithServer.request(Code.ADMIN_ALL_USERS, null);
                String[] users = answer.split("/");

                ObservableList<User> userList = FXCollections.observableArrayList();
                userList.clear();

                for (String application : users) {
                    String[] words = application.split("&");
                    userList.add(new User(Integer.parseInt(words[0]), words[1], words[2], words[3], words[4], words[5], words[6], words[7], Boolean.parseBoolean(words[8]), words[9], words[10], words[11]));
                }
                tableUsers.setItems(userList);
                loadingLabel.setVisible(false);
            }
        });

        messagesButton.setOnAction(actionEvent -> {
            if(openPane(supportPane)) {
                loadingLabel.setVisible(true);
                String answer = CommunicationWithServer.request(Code.ADMIN_ALL_QUESTIONS, null);
                if(!"false".equals(answer)) {
                    String[] quests = answer.split("/");

                    ObservableList<Question> questList = FXCollections.observableArrayList();
                    questList.clear();

                    for (String application : quests) {
                        String[] words = application.split("&");
                        questList.add(new Question(Integer.parseInt(words[0]), Integer.parseInt(words[1]), words[2]));
                    }
                    questionsTable.setItems(questList);
                }
                loadingLabel.setVisible(false);
            }
        });

        applicationsButton.setOnAction(actionEvent -> {
            if(openPane(appPane)) {
                ObservableList<App> appList = FXCollections.observableArrayList();
                String answer = CommunicationWithServer.request(Code.ADMIN_ALL_APPS, null);

                if ("".equals(answer)) return;

                String[] apps = answer.split("/");

                appList.clear();
                for (String application : apps) {
                    String[] words = application.split("&");
                    appList.add(new App(Integer.parseInt(words[0]), words[1], words[2], words[3]));
                }
                applicationsTable.setItems(appList);
            }
        });

        answerTheQuestionButton.setOnAction(actionEvent -> {
            String answer = CommunicationWithServer.request(Code.ADMIN_NEXT_QUESTION, null);
            if (!"false".equals(answer)) {
                String[] words = answer.split(Const.DELIMITER);
                AnswerTheQuestionsController.question = new Question(Integer.parseInt(words[0]), Integer.parseInt(words[1]), words[2]);
                ControlWindows.ChangeWindows(answerTheQuestionButton, getClass(), "/kursach/fxmls/answerTheQuestions.fxml");
            }
        });

        startRedactButton.setOnAction(actionEvent -> {
            String answer = CommunicationWithServer.request(Code.ADMIN_APP_FOR_REDACTION, null);
            if (!"false".equals(answer)) {
                String[] words = answer.split(Const.DELIMITER);
                AbstractInsuranceApplication abstrApp = null;
                try {
                    switch (answer.charAt(0)) {
                        case '1':
                            abstrApp = new PropertyInsuranceApplication(words);
                            break;
                        case '2':
                            abstrApp = new VehicleInsuranceApplication(words);
                            break;
                        case '3':
                            abstrApp = new HealthInsuranceApplication(words);
                            break;
                        default:
                            ;
                    }
                } catch (Exception e) {
                }
                if (abstrApp instanceof VehicleInsuranceApplication) {
                    redVehicleAppController.application = (VehicleInsuranceApplication) abstrApp;
                    ControlWindows.ChangeWindows(startRedactButton, getClass(), "/kursach/fxmls/redVehicleApp.fxml");
                }
                if (abstrApp instanceof PropertyInsuranceApplication) {
                    redPropertyAppController.application = (PropertyInsuranceApplication) abstrApp;
                    ControlWindows.ChangeWindows(startRedactButton, getClass(), "/kursach/fxmls/redPropertyApp.fxml");
                }
                if (abstrApp instanceof HealthInsuranceApplication) {
                    redHealthAppController.application = (HealthInsuranceApplication) abstrApp;
                    ControlWindows.ChangeWindows(startRedactButton, getClass(), "/kursach/fxmls/redHealthApp.fxml");
                }
            }
        });

        deleteContractButton.setOnAction(actionEvent -> {
            int id;
            try {
                id = Integer.parseInt(numbContractField.getText());
                String answer = CommunicationWithServer.request(Code.DELETE_CONTRACT, Integer.toString(id));
                if ("true".equals(answer)) numbContractField.setText("Договор удалён");
                else numbContractField.setText(answer);
            } catch (Exception e) {
                numbContractField.setText("Договор не найден");
            }
        });

        loadContractsButton.setOnAction(actionEvent -> {
            ObservableList<SimpleContractForTable> appList = FXCollections.observableArrayList();
            String answer = CommunicationWithServer.request(Code.ADMIN_ALL_CONTRACTS, null);
            if (!"".equals(answer)) {
                String[] apps = answer.split("/");

                appList.clear();
                for (String application : apps) {
                    String[] words = application.split("&");
                    if (filterContract(words))
                    appList.add(new SimpleContractForTable(Integer.parseInt(words[0]), words[1], words[2], words[3], Double.parseDouble(words[4]), Double.parseDouble(words[5])));
                }
                contractsTable.setItems(appList);
            } else {
                contractsTable.setItems(null);
            }
        });

        newAdminButton.setOnAction(actionEvent -> {
            ControlWindows.ChangeWindows(newAdminButton, getClass(), "/kursach/fxmls/newAdmin.fxml");
        });

        contrctsButton.setOnAction(actionEvent -> openPane(contractsPane));
        aboutButton.setOnAction(actionEvent -> {
                if (openPane(aboutPane)) {
                    aboutTextArea.setText(CommunicationWithServer.request(Code.ABOUT_COMPANY, null));
                }
            });
        ContactsButton.setOnAction(actionEvent -> {
                if (openPane(contactsPane)) {
                    contactsTextArea.setText(CommunicationWithServer.request(Code.CONTACTS, null));
                }
            });
        vakansiiButton.setOnAction(actionEvent -> {
                if (openPane(vakansiiPane)) {
                    vakansiiTextArea.setText(CommunicationWithServer.request(Code.LIST_VAKANS, null));
                }
            });

        infoButton.setOnAction(actionEvent -> {
                generatePieChart();
                generateBarChart();

                VBox vbox = new VBox(pieChart, barChart);
                Stage stage = new Stage();
                stage.setTitle("Графики");
                Scene scene = new Scene(vbox, 400, 200);
                Image icon = new Image("/kursach/resource/icon.png");
                stage.getIcons().add(icon);
                stage.setScene(scene);
                stage.setHeight(500);
                stage.setWidth(500);
                stage.show();
        });

        confirmAbout.setOnAction(actionEvent -> {
            CommunicationWithServer.request(Code.CONFIRM_ABOUT, aboutTextArea.getText());
        });

        confirmContacts.setOnAction(actionEvent -> {
            CommunicationWithServer.request(Code.CONFIRM_CONTACTS, contactsTextArea.getText());
        });

        confirmVakansii.setOnAction(actionEvent -> {
            CommunicationWithServer.request(Code.CONFIRM_VAKANSII, vakansiiTextArea.getText());
        });

        openContractButton.setOnAction(actionEvent -> {

            String number = numbContractField.getText();
            if(number == null || "".equals(number)) return;
            try {
                Integer.parseInt(number);
            } catch (Exception e) {
                return;
            }
            String strContract = CommunicationWithServer.request(Code.VIEW_CONTRACT, number);
            if ("false".equals(strContract)) return;

            Contract contract = new Contract(strContract);

            ViewController.contract = contract;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/kursach/fxmls/view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            Image icon = new Image("/kursach/resource/icon.png");
            stage.getIcons().add(icon);
            stage.setTitle(Const.COMPANY_NAME);
            stage.setScene(new Scene(root));
            stage.show();
        });

    }
    private boolean openPane(AnchorPane pane) {
        AnchorPane[] panes = new AnchorPane[]{usersPane, appPane, contractsPane, supportPane, aboutPane, contactsPane, vakansiiPane};
        for (AnchorPane tempPane : panes)
            if (tempPane != pane) tempPane.setVisible(false);
        if (pane.isVisible()) {
            pane.setVisible(false);
            return false;
        }
        else {
            pane.setVisible(true);
            return true;
        }
    }

    private boolean filterContract(String[] param) {
        if (iDotField.isDisable()) return true;
        double sum, per;
        int id = Integer.parseInt(param[0]);
        sum = Double.parseDouble(param[4]);
        per = Double.parseDouble(param[5]);
        try {
            if (!iDotField.getText().equals("")) if (id < Integer.parseInt(iDotField.getText())) return false;
            if (!iDdoField.getText().equals("")) if (id > Integer.parseInt(iDdoField.getText())) return false;
            if (!sumOtField.getText().equals("")) if (sum < Double.parseDouble(sumOtField.getText())) return false;
            if (!sumDoField.getText().equals("")) if (sum > Double.parseDouble(sumDoField.getText())) return false;
            if (!perOtField.getText().equals("")) if (per < Double.parseDouble(perOtField.getText())) return false;
            if (!perDoField.getText().equals("")) if (per > Double.parseDouble(perDoField.getText())) return false;
        } catch (Exception e) {
            return false;
        }

        if("Все".equals(contractCombo.getValue())) return true;
        if(!param[1].equals(contractCombo.getValue())) return false;
        return true;
    }

    private void setVisibleAll(boolean visible, Object...objects) {
        for (Object obj : objects) {
            if (obj instanceof Button) ((Button)obj).setVisible(visible);
            if (obj instanceof Label) ((Label)obj).setVisible(visible);
            if (obj instanceof AnchorPane) ((AnchorPane)obj).setVisible(visible);
        }
    }

    private void setDisableAll(boolean disable, Object...objects) {
        for (Object obj : objects) {
            if (obj instanceof Label) ((Label)obj).setDisable(disable);
            if (obj instanceof TextField) ((TextField)obj).setDisable(disable);
        }
    }

    private void generatePieChart() {
        String answer = CommunicationWithServer.request(Code.INFO_FRO_PAIRCHART, null);
        if (!"false".equals(answer)) {
            String[] answers = answer.split(Const.DELIMITER);

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Автомобиль", Integer.parseInt(answers[0])),
                            new PieChart.Data("Недвижимость", Integer.parseInt(answers[1])),
                            new PieChart.Data("Здоровье", Integer.parseInt(answers[2])));
            pieChart = new PieChart(pieChartData);
            pieChart.setLegendVisible(false);
            pieChart.setTitle("Графики");
        }
    }

    private void generateBarChart() {
        String answer = CommunicationWithServer.request(Code.INFO_FROM_BARCHART, null);
        String[] answers = answer.split(Const.DELIMITER);
        ArrayList<Integer> intArr = new ArrayList<>();
        for (int i = 0; i < answers.length; i++) intArr.add(Integer.parseInt(answers[i]));
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Количество заключенных договоров");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(Collections.max(intArr));
        yAxis.setTickUnit(1);
        yAxis.setLabel("Пользователей");
        barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
        barChart.setLegendVisible(false);

        for (int i = 0; i < answers.length; i++) {
            dataSeries1.getData().add((new XYChart.Data<>(Integer.toString(i), intArr.get(i))));
        }
        barChart.getData().add(dataSeries1);
        barChart.setTitle("Кооличество договоров у пользователей");
    }
}
