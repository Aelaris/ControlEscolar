package org.openjfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.StudentCourses;
import repository.DataStore;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class RegisteredController {

    private final double coordinateX = 838;
    private final double coordinateY = 300;

    public RegisteredController() {}

    public void initialize() {
        updateTableview();
    }

    @FXML
    private void updateTableview() {

        ObservableList<StudentCourses> studentCourses = DataStore.getStudentCourses();

        txt_s.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent().toString()));
        txt_c.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getCourses().stream()
                        .map(course -> course.getSubject().toString())
                        .collect(Collectors.joining("\n\n"))
        ));
        txt_viewCoursesStudent.setItems(studentCourses);
    }

    @FXML
    private void deleteSelectedStudentCourse() {

        StudentCourses selected = txt_viewCoursesStudent.getSelectionModel().getSelectedItem();

        if (selected != null) {

            DataStore.removeStudentCourse(selected);
            updateTableview();
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
    private TableColumn<StudentCourses, String> txt_c;

    @FXML
    private TableColumn<StudentCourses, String> txt_s;

    @FXML
    private TableView<StudentCourses> txt_viewCoursesStudent;
}
