package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


public class PopUpController {

    @FXML
    private void exitProgram() {

        System.exit(0);

    }

    @FXML
    private void btnClosePopUp() {

        Stage stage = (Stage) btn_closePopup.getScene().getWindow();
        stage.close();

    }

    @FXML private Button btn_closePopup;

}
