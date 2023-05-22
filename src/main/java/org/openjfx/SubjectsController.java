package org.openjfx;

import exceptions.ClaveSubject;
import exceptions.CreditsSubject;
import exceptions.SemesterSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Subject;
import repository.SubjectsRepository;
import java.io.IOException;
import java.util.Objects;

public class SubjectsController {

    private SubjectsRepository subjectsRepository = new SubjectsRepository();
    private Subject subject;

    private void createSubject() throws IOException {

        try {
            String clave = txt_clave.getText();
            String name = txt_nameSubject.getText();
            String semester = txt_SemesterSubject.getText();
            String credits = txt_creditsSubject.getText();

            subject = new Subject(clave, name, semester, credits);

            subjectsRepository.createSubject(subject);

        }catch (ClaveSubject | SemesterSubject | CreditsSubject e) {

            double coordinateX = 650;
            double coordinateY = 300;

            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorSubject.fxml")));
            popupStage.setScene(new Scene(popupRoot));
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();

            popupStage.centerOnScreen();
            popupStage.setX(coordinateX);
            popupStage.setY(coordinateY);
        }
    }

    @FXML
    private void btnCreateSubject() throws IOException {

        createSubject();

    }

    @FXML
    private void UpdateTextView() {

        ObservableList<Subject> subjects = FXCollections.observableArrayList(subjectsRepository.getSubject());

        txt_c.setCellValueFactory(new PropertyValueFactory<>("clave"));
        txt_n.setCellValueFactory(new PropertyValueFactory<>("name"));
        txt_s.setCellValueFactory(new PropertyValueFactory<>("semester"));
        txt_cr.setCellValueFactory(new PropertyValueFactory<>("credits"));

        txt_viewSubjects.setItems(subjects);
    }

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
    private void btnHome() throws IOException {

        App.setRoot("Home");

    }

    @FXML
    private void btnTeachers() throws IOException {

        App.setRoot("Teachers");

    }

    @FXML
    private void btnStudents() throws IOException {

        App.setRoot("Students");

    }

    @FXML
    private TextField txt_SemesterSubject;

    @FXML
    private TextField txt_clave;

    @FXML
    private TextField txt_creditsSubject;

    @FXML
    private TextField txt_nameSubject;

    @FXML
    private TableView<Subject> txt_viewSubjects;

    @FXML
    private TableColumn<Subject, String> txt_c;

    @FXML
    private TableColumn<Subject, String> txt_n;

    @FXML
    private TableColumn<Subject, String> txt_s;

    @FXML
    private TableColumn<Subject, String> txt_cr;
}
