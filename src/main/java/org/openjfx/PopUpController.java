package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class PopUpController {

    public PopUpController() {}

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
