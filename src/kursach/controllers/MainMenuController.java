package kursach.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import kursach.*;
import kursach.apps.HealthInsuranceApplication;
import kursach.apps.PropertyInsuranceApplication;
import kursach.apps.VehicleInsuranceApplication;
import kursach.contracts.SimpleContractForTable;

public class MainMenuController {

    @FXML
    private Label labelUserName;

    @FXML
    private ImageView logo;

    @FXML
    private Button licCabinetButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label licCabinetLabel;

    @FXML
    private Label strahLabel;

    @FXML
    private Label contractsLabels;

    @FXML
    private Label personalDataLabel;

    @FXML
    private Label labelNameCompany;

    @FXML
    private Button fixFacesButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Button contactsButton;

    @FXML
    private Button communicateButton;

    @FXML
    private Button pressButton;

    @FXML
    private Button calculatorButton;

    @FXML
    private AnchorPane dataPane;

    @FXML
    private Label loginLabelSet;

    @FXML
    private Label iDNPasLabelSet;

    @FXML
    private Label phoneLabelSet;

    @FXML
    private Label emailLabelSet;

    @FXML
    private Label nPassLabelSet;

    @FXML
    private Label birthdayLabelSet;

    @FXML
    private Label regDayLabelSet;

    @FXML
    private AnchorPane dogovorsPane;

    @FXML
    private TableView<SimpleContractForTable> dogovorsTabl;

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
    private AnchorPane strahPane;

    @FXML
    private AnchorPane fizFacesPane;

    @FXML
    private Button appHouseButton;

    @FXML
    private Button appCarButton;

    @FXML
    private Button appHealthButton;

    @FXML
    private Button healthInfoButton;

    @FXML
    private Button carInfoButton;

    @FXML
    private Button houseInfoButton;

    @FXML
    private Button dataPaneButton;

    @FXML
    private TableView<App> tableApplications;

    @FXML
    private TableColumn<App, Integer> idColumn;

    @FXML
    private TableColumn<App, String> typeColumn;

    @FXML
    private TableColumn<App, String> objectName;

    @FXML
    private TableColumn<App, String> objectNumber;

    @FXML
    private Button strahPaneButton;

    @FXML
    private Button dogovorPaneButton;

    @FXML
    private AnchorPane vakansiiPane;

    @FXML
    private AnchorPane pressPane;

    @FXML
    private TextArea textAreaPress;

    @FXML
    private Button enterPressButton;

    @FXML
    private AnchorPane aboutPane;

    @FXML
    private AnchorPane contactsPane;

    @FXML
    private Label answerPressLabel;

    @FXML
    private TextArea adminAnswersText;

    @FXML
    private TextArea listVakanciiText;

    @FXML
    private Label aboutCompanyLabel;

    @FXML
    private Label contactsLabel;

    @FXML
    private Label infoStrahLabel;

    @FXML
    void initialize() {
        startInitial();
        exitButton.setOnAction(actionEvent -> {
            User.setUser(null);
            ControlWindows.ChangeWindows(exitButton, getClass(), "/kursach/fxmls/authorisation.fxml");
        });

        licCabinetButton.setOnAction((actionEvent -> {
            closeAllParts();
            setVisibleAll(true, licCabinetLabel, strahLabel, strahPaneButton, contractsLabels, dogovorPaneButton, dataPaneButton, personalDataLabel);
        }));

        fixFacesButton.setOnAction(actionEvent -> {
            setVisibleAll(false, dataPane, dataPaneButton, dogovorsPane, dogovorPaneButton, strahPane, strahPaneButton, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, aboutPane, contactsPane, vakansiiPane, pressPane);
            if(fizFacesPane.isVisible())
            {
                fizFacesPane.setVisible(false);
                setVisibleAll(true, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, dataPaneButton, dogovorPaneButton, strahPaneButton);
            }
            else fizFacesPane.setVisible(true);
        });

        aboutButton.setOnAction(actionEvent -> {
            setVisibleAll(false, dataPane, dataPaneButton, dogovorsPane, dogovorPaneButton, strahPane, strahPaneButton, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, fizFacesPane, contactsPane, vakansiiPane, pressPane);
            if(aboutPane.isVisible())
            {
                aboutPane.setVisible(false);
                setVisibleAll(true, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, dataPaneButton, dogovorPaneButton, strahPaneButton);
            }
            else {
                aboutPane.setVisible(true);


            aboutCompanyLabel.setText(CommunicationWithServer.request(Code.ABOUT_COMPANY, null));
            }
        });

        contactsButton.setOnAction(actionEvent -> {
            setVisibleAll(false, dataPane, dataPaneButton, dogovorsPane, dogovorPaneButton, strahPane, strahPaneButton, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, fizFacesPane, aboutPane, vakansiiPane, pressPane);
            if(contactsPane.isVisible())
            {
                contactsPane.setVisible(false);
                setVisibleAll(true, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, dataPaneButton, dogovorPaneButton, strahPaneButton);
            }
            else {
                contactsPane.setVisible(true);
                contactsLabel.setText(CommunicationWithServer.request(Code.CONTACTS, null));
            }
        });

        carInfoButton.setOnAction(actionEvent -> {
            infoStrahLabel.setText(VehicleInsuranceApplication.info());
        });

        houseInfoButton.setOnAction(actionEvent -> {
            infoStrahLabel.setText(PropertyInsuranceApplication.info());
        });

        healthInfoButton.setOnAction(actionEvent -> {
            infoStrahLabel.setText(HealthInsuranceApplication.info());
        });

        communicateButton.setOnAction(actionEvent -> {
            setVisibleAll(false, dataPane, dataPaneButton, dogovorsPane, dogovorPaneButton, strahPane, strahPaneButton, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, fizFacesPane, contactsPane, aboutPane, pressPane);
            if(vakansiiPane.isVisible())
            {
                vakansiiPane.setVisible(false);
                setVisibleAll(true, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, dataPaneButton, dogovorPaneButton, strahPaneButton);
            }
            else {
                vakansiiPane.setVisible(true);
                listVakanciiText.setText(CommunicationWithServer.request(Code.LIST_VAKANS, null));
            }
        });

        pressButton.setOnAction(actionEvent -> {
            setVisibleAll(false, dataPane, dataPaneButton, dogovorsPane, dogovorPaneButton, strahPane, strahPaneButton, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, fizFacesPane, contactsPane, aboutPane, vakansiiPane);
            if(pressPane.isVisible())
            {
                pressPane.setVisible(false);
                setVisibleAll(true, licCabinetLabel, strahLabel, contractsLabels, personalDataLabel, dataPaneButton, dogovorPaneButton, strahPaneButton);
            }
            else {
                pressPane.setVisible(true);
                adminAnswersText.setText(CommunicationWithServer.request(Code.HISTORY_ANSWERS, Integer.toString(User.getUser().getId())));
            }
        });

        enterPressButton.setOnAction(actionEvent -> {
            if ("".equals(textAreaPress.getText())) answerPressLabel.setText("Ошибка !!! Поле не может быть пустым");
            else {
                String answer = CommunicationWithServer.request(Code.NEW_PRESS_QUERY, CommunicationWithServer.createMessageForServer(User.getUser().getId().toString(), textAreaPress.getText()));
                answerPressLabel.setText(answer);
                textAreaPress.clear();
            }
        });

        dataPaneButton.setOnAction((actionEvent -> {
            setVisibleAll(false, strahPane, dogovorsPane);
            if (dataPane.isVisible())
                dataPane.setVisible(false);
            else dataPane.setVisible(true);
        }));

        strahPaneButton.setOnAction((actionEvent -> {
            setVisibleAll(false, dataPane, dataPaneButton, dogovorsPane, dogovorPaneButton);
            if (strahPane.isVisible()) {
                strahPane.setVisible(false);
                setVisibleAll(true, dataPaneButton, dogovorPaneButton);
            }
            else {
                strahPane.setVisible(true);

                String answer = CommunicationWithServer.request(Code.LIST_APPLICATIONS, Integer.toString(User.getUser().getId()));
                if (!answer.equals("")) {
                    String[] applications = answer.split("/");
                    ObservableList<App> appList = FXCollections.observableArrayList();
                    appList.clear();
                    for (String application : applications) {
                        String[] words = application.split("&");
                        appList.add(new App(Integer.parseInt(words[0]), words[1], words[2], words[3]));
                    }
                    tableApplications.setItems(appList);
                }
            }
        }));

        dogovorPaneButton.setOnAction((actionEvent -> {
            setVisibleAll(false, dataPane, strahPane, dataPaneButton);
            if (dogovorsPane.isVisible()) {
                dogovorsPane.setVisible(false);
                dataPaneButton.setVisible(true);
            }
            else {
                dogovorsPane.setVisible(true);
                String answer = CommunicationWithServer.request(Code.LIST_CONTRACTS, Integer.toString(User.getUser().getId()));
                if (!answer.equals("")) {
                    String[] contracts = answer.split("/");
                    ObservableList<SimpleContractForTable> contrList = FXCollections.observableArrayList();
                    contrList.clear();
                    for (String application : contracts) {
                        String[] words = application.split("&");
                        contrList.add(new SimpleContractForTable(Integer.parseInt(words[0]), words[1], words[2], words[3], Double.parseDouble(words[4]), Double.parseDouble(words[5])));
                    }
                    dogovorsTabl.setItems(contrList);
                }
            }
        }));

        appCarButton.setOnAction((actionEvent -> {
            ControlWindows.ChangeWindows(appCarButton, getClass(), "/kursach/fxmls/newVehicleApp.fxml");
        }));

        appHouseButton.setOnAction(actionEvent -> {
            ControlWindows.ChangeWindows(appHouseButton, getClass(), "/kursach/fxmls/newPropertyApp.fxml");
        });

        appHealthButton.setOnAction(actionEvent -> {
            ControlWindows.ChangeWindows(appHealthButton, getClass(), "/kursach/fxmls/newHealthApp.fxml");
        });

        calculatorButton.setOnAction(actionEvent -> {
            ControlWindows.ChangeWindows(calculatorButton, getClass(), "/kursach/fxmls/calculator.fxml");
        });
    }

    private void startInitial() {
        logo.setImage(new Image(Const.urlLogo));
        labelNameCompany.setText(Const.COMPANY_NAME);
        labelUserName.setText(User.getUser().getLogin());
        loginLabelSet.setText(User.getUser().getLogin());
        iDNPasLabelSet.setText(User.getUser().getnIdPassport());
        phoneLabelSet.setText(User.getUser().getnPhone());
        emailLabelSet.setText(User.getUser().getnPhone());
        nPassLabelSet.setText(User.getUser().getnPassport());
        birthdayLabelSet.setText(User.getUser().getBirthday());
        regDayLabelSet.setText(User.getUser().getRegistrDay());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("numb"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        objectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        objectNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        ContrIdColumn.setCellValueFactory(new PropertyValueFactory<>("numb"));
        ContrTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        ContrObjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ContrObjectNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        ContrSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        ContrPerMonth.setCellValueFactory(new PropertyValueFactory<>("perMonth"));
        setVisibleAll(false, dataPane, dogovorsPane, strahPane, fizFacesPane, aboutPane, contactsPane, vakansiiPane, pressPane);
    }

    private void closeAllParts() {
        setVisibleAll(false, strahLabel, strahPane, strahPaneButton, dogovorPaneButton, contractsLabels, dogovorsPane, dataPaneButton, personalDataLabel, dataPane, fizFacesPane, aboutPane, contactsPane, vakansiiPane, pressPane);
    }

    private void setVisibleAll(boolean visible, Object...objects) {
        for (Object obj : objects) {
            if (obj instanceof Button) ((Button)obj).setVisible(visible);
            if (obj instanceof Label) ((Label)obj).setVisible(visible);
            if (obj instanceof AnchorPane) ((AnchorPane)obj).setVisible(visible);
        }
    }
}
