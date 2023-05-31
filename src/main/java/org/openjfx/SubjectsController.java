package org.openjfx;

import exceptions.ClaveSubject;
import exceptions.CreditsSubject;
import exceptions.SemesterSubject;
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
import repository.DataStore;
import java.io.IOException;
import java.util.Objects;

public class SubjectsController {

    private Subject subject;
    private final double coordinateX = 838;
    private final double coordinateY = 300;

    public SubjectsController() {}

    @FXML
    public void initialize() {
        UpdateTextView();
    }

    private void createSubject() throws IOException {

        try {
            String clave = txt_clave.getText();
            String name = txt_nameSubject.getText();
            String semester = txt_SemesterSubject.getText();
            String credits = txt_creditsSubject.getText();

            subject = new Subject(clave, name, semester, credits);

            DataStore.addSubject(subject);


        }catch (ClaveSubject | SemesterSubject | CreditsSubject e) {

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

        if (subject != null) {
            clearSubjects();
        }
    }

    @FXML
    private void btnDeleteSubject() {

        Subject selectedSubject = txt_viewSubjects.getSelectionModel().getSelectedItem();

        if (selectedSubject != null) {
            DataStore.removeSubject(selectedSubject);

        }
    }

    @FXML
    private void UpdateTextView() {

        ObservableList<Subject> subjects = DataStore.getSubjects();

        txt_c.setCellValueFactory(new PropertyValueFactory<>("clave"));
        txt_n.setCellValueFactory(new PropertyValueFactory<>("name"));
        txt_s.setCellValueFactory(new PropertyValueFactory<>("semester"));
        txt_cr.setCellValueFactory(new PropertyValueFactory<>("credits"));

        txt_viewSubjects.setItems(subjects);
    }

    @FXML
    private void clearSubjects() {

        txt_clave.clear();
        txt_nameSubject.clear();
        txt_SemesterSubject.clear();
        txt_creditsSubject.clear();
    }

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
    private void btnCourses() throws IOException {
        App.setRoot("Course");
    }

    @FXML
    private void btnRegistered() throws IOException {
        App.setRoot("Registered");
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
