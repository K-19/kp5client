package kursach.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import kursach.contracts.Contract;

public class ViewController {

    @FXML
    private TextArea viewContract;

    @FXML
    private Button cancelButton;

    public static Contract contract;

    @FXML
    void initialize() {
        viewContract.setText(contract.toString());

        cancelButton.setOnAction(actionEvent -> {
            contract = null;
            Stage stageOld = (Stage)cancelButton.getScene().getWindow();
            stageOld.close();
        });
    }
}
