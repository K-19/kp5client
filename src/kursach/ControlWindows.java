package kursach;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlWindows {
    public static void ChangeWindows(Button button, Class someClass, String url) {
        Stage stageOld = (Stage)button.getScene().getWindow();
        stageOld.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(someClass.getResource(url));
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
    }
}
