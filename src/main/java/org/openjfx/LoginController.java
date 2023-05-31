package org.openjfx;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginController {

    private Map<String, String> userDatabase = new HashMap<>();
    private final double coordinateX = 838;
    private final double coordinateY = 300;

    public LoginController() {}

    @FXML
    private void openPopup() throws IOException {

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
    private void btnRegister() throws IOException {
        String username = txt_key.getText();
        String password = txt_password.getText();


        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorEmptyFields.fxml")));
            popupStage.setScene(new Scene(popupRoot));
            popupStage.initStyle(StageStyle.TRANSPARENT);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();
            popupStage.centerOnScreen();
            popupStage.setX(coordinateX);
            popupStage.setY(coordinateY);

        } else if (!userDatabase.containsKey(username)) {

            userDatabase.put(username, password);
            txt_key.clear();
            txt_password.clear();

            double coordinateX = 900;
            double coordinateY = 200;

            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UsuarioRegistrado.fxml")));
            popupStage.setScene(new Scene(popupRoot));
            popupStage.initStyle(StageStyle.TRANSPARENT);
            popupStage.show();
            popupStage.centerOnScreen();
            popupStage.setX(coordinateX);
            popupStage.setY(coordinateY);

            FadeTransition ft = new FadeTransition(Duration.millis(3000), popupRoot);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setOnFinished(actionEvent -> popupStage.close());
            ft.play();

        } else {

            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorUsuario.fxml")));
            popupStage.setScene(new Scene(popupRoot));
            popupStage.initStyle(StageStyle.TRANSPARENT);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();
            popupStage.centerOnScreen();
            popupStage.setX(coordinateX);
            popupStage.setY(coordinateY);
        }
    }

    @FXML
    private void btnLogin() throws IOException {
        String username = txt_key.getText();
        String password = txt_password.getText();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            App.setRoot("Home");
        } else {

            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorLogin.fxml")));
            popupStage.setScene(new Scene(popupRoot));
            popupStage.initStyle(StageStyle.TRANSPARENT);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();
            popupStage.centerOnScreen();
            popupStage.setX(coordinateX);
            popupStage.setY(coordinateY);
        }
    }

    @FXML
    public void onEnter(javafx.scene.input.KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            btnLogin();
        }
    }

    @FXML
    private TextField txt_key;

    @FXML
    private TextField txt_password;

}
