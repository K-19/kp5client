package kursach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import kursach.Code;
import kursach.CommunicationWithServer;
import kursach.ControlWindows;
import kursach.contracts.Contract;

public class ViewContractController {

    @FXML
    private TextArea viewContract;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    public static Contract contract;

    @FXML
    void initialize() {
        viewContract.setText(contract.toString());

        cancelButton.setOnAction(actionEvent -> {
            contract = null;
            ControlWindows.ChangeWindows(cancelButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });

        confirmButton.setOnAction(actionEvent -> {
            CommunicationWithServer.request(Code.NEW_CONTRACT, contract.toStringArray());
            CommunicationWithServer.request(Code.GENERATE_FILE, CommunicationWithServer.createMessageForServer(Integer.toString(contract.getIdApp()), contract.toString()));
            contract = null;
            ControlWindows.ChangeWindows(confirmButton, getClass(), "/kursach/fxmls/adminMenu.fxml");
        });
    }
}
