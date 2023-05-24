package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Objects;


public class LoginController {

    private final String key = "22550658";
    private final String password = "Hola";


    @FXML
    private void openPopup() throws IOException {

        double coordinateX = 650;
        double coordinateY = 300;

        Stage popupStage = new Stage();
        Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ConfirmacionExit.fxml")));
        popupStage.setScene(new Scene(popupRoot));
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.show();

        popupStage.centerOnScreen();
        popupStage.setX(coordinateX);
        popupStage.setY(coordinateY);

    }

    @FXML
    private void btnLogin() throws IOException {

    String key1 = txt_key.getText();
    String password1 = txt_password.getText();

    if (key1.equals(key) && password1.equals(password)){

        App.setRoot("Home");

    }else {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Usuario o Contraseña Invalidos.");
        alert.showAndWait();

        }
    }

    @FXML
    private TextField txt_key;

    @FXML
    private TextField txt_password;



}
