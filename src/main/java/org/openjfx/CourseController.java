package org.openjfx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Course;
import model.Subject;
import model.Teacher;
import repository.DataStore;
import java.io.IOException;
import java.util.Objects;

public class CourseController {

    public CourseController(){}

    @FXML
    public void initialize() {
        UpdateTextView();
    }

    @FXML
    private void UpdateTextView() {

        ObservableList<Teacher> teachers = DataStore.getTeachers();
        ObservableList<Subject> subjects = DataStore.getSubjects();
        ObservableList<Course> courses = DataStore.getCourses();

        txt_nT.setCellValueFactory(new PropertyValueFactory<>("name"));
        txt_ln1.setCellValueFactory(new PropertyValueFactory<>("lastName1"));
        txt_ln2.setCellValueFactory(new PropertyValueFactory<>("lastName2"));
        txt_cT.setCellValueFactory(new PropertyValueFactory<>("curp"));

        txt_cM.setCellValueFactory(new PropertyValueFactory<>("clave"));
        txt_nM.setCellValueFactory(new PropertyValueFactory<>("name"));
        txt_sM.setCellValueFactory(new PropertyValueFactory<>("semester"));
        txt_crM.setCellValueFactory(new PropertyValueFactory<>("credits"));

        txt_teacherCourse.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        txt_subjectCourse.setCellValueFactory(new PropertyValueFactory<>("subject"));

        txt_viewTeachers.setItems(teachers);
        txt_viewSubjects.setItems(subjects);
        txt_viewCourses.setItems(courses);
    }

    @FXML
    private void createCourse() {
        Teacher selectedTeacher = txt_viewTeachers.getSelectionModel().getSelectedItem();
        Subject selectedSubject = txt_viewSubjects.getSelectionModel().getSelectedItem();

        if (selectedTeacher != null && selectedSubject != null) {
            Course course = new Course(selectedTeacher, selectedSubject);
            DataStore.addCourse(course);

        } else {
            System.out.println(" ");
        }
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
    private void btnStudents() throws IOException {
        App.setRoot("Students");
    }

    @FXML
    private void btnTeachers() throws IOException {
        App.setRoot("Teachers");
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
    private TableView<Teacher> txt_viewTeachers;

    @FXML
    private TableColumn<Subject, String> txt_cM;

    @FXML
    private TableColumn<Teacher, String> txt_cT;

    @FXML
    private TableColumn<Subject, String> txt_crM;

    @FXML
    private TableColumn<Teacher, String> txt_ln1;

    @FXML
    private TableColumn<Teacher, String> txt_ln2;

    @FXML
    private TableColumn<Subject, String> txt_nM;

    @FXML
    private TableColumn<Teacher, String> txt_nT;

    @FXML
    private TableColumn<Subject, String> txt_sM;

    @FXML
    private TableView<Subject> txt_viewSubjects;

    @FXML
    private TableColumn<Subject, String> txt_subjectCourse;

    @FXML
    private TableColumn<Teacher, String> txt_teacherCourse;

    @FXML
    private TableView<Course> txt_viewCourses;

}

