package org.openjfx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Course;
import model.Student;
import model.StudentCourses;
import repository.DataStore;
import java.io.IOException;
import java.util.Objects;

public class StudentCourseController {

    private final double coordinateX = 838;
    private final double coordinateY = 300;

    public StudentCourseController() {}

    public void initialize() {
        updateTableview();
    }

    @FXML
    private void updateTableview(){

        ObservableList<Student> students = DataStore.getStudents();
        ObservableList<Course> courses = DataStore.getCourses();

        txt_nS.setCellValueFactory(new PropertyValueFactory<>("name"));
        txt_lnS1.setCellValueFactory(new PropertyValueFactory<>("lastName1"));
        txt_lnS2.setCellValueFactory(new PropertyValueFactory<>("lastName2"));
        txt_nC.setCellValueFactory(new PropertyValueFactory<>("numControl"));
        txt_cS.setCellValueFactory(new PropertyValueFactory<>("curp"));
        txt_celS.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
        txt_fS.setCellValueFactory(new PropertyValueFactory<>("date"));
        txt_gS.setCellValueFactory(new PropertyValueFactory<>("gender"));
        txt_sS.setCellValueFactory(new PropertyValueFactory<>("status"));

        txt_mC.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        txt_sC.setCellValueFactory(new PropertyValueFactory<>("subject"));

        txt_viewStudents.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        txt_viewCourses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        txt_viewStudents.setItems(students);
        txt_viewCourses.setItems(courses);
    }

    @FXML
    private void registerCourses() {

        Student selectedStudent = txt_viewStudents.getSelectionModel().getSelectedItem();
        ObservableList<Course> selectedCourses = txt_viewCourses.getSelectionModel().getSelectedItems();

        if (selectedStudent != null && selectedCourses != null && !selectedCourses.isEmpty()) {
            StudentCourses studentCourses = new StudentCourses(selectedStudent, selectedCourses);

            DataStore.addStudentCourses(studentCourses);
        }
    }

    @FXML
    private void btnStudents() throws IOException {
        App.setRoot("Students");
    }

    @FXML
    private void btnSubjects() throws IOException {
        App.setRoot("Subjects");
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
    private void btnCourses() throws IOException {
        App.setRoot("Course");
    }

    @FXML
    private void btnRegister() throws IOException {
        App.setRoot("Registered");
    }

    @FXML
    private void btnRegistered() throws IOException {
        App.setRoot("Registered");
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
    private TableColumn<Course, String> txt_sC;

    @FXML
    private TableColumn<Course, String> txt_mC;

    @FXML
    private TableColumn<Student, String> txt_cS;

    @FXML
    private TableColumn<Student, String> txt_celS;

    @FXML
    private TableColumn<Student, String> txt_fS;

    @FXML
    private TableColumn<Student, String> txt_gS;

    @FXML
    private TableColumn<Student, String> txt_lnS1;

    @FXML
    private TableColumn<Student, String> txt_lnS2;

    @FXML
    private TableColumn<Student, String> txt_nC;

    @FXML
    private TableColumn<Student, String> txt_nS;

    @FXML
    private TableColumn<Student, String> txt_sS;

    @FXML
    private TableView<Course> txt_viewCourses;

    @FXML
    private TableView<Student> txt_viewStudents;
}
