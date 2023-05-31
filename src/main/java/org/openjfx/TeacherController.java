package org.openjfx;

import exceptions.*;
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
import model.Date;
import model.Teacher;
import repository.DataStore;
import java.io.IOException;
import java.util.Objects;

public class TeacherController {

    private Teacher teacher;
    private Date date;
    private final double coordinateX = 838;
    private final double coordinateY = 300;


    public TeacherController(){}

    @FXML
    public void initialize() {

        UpdateTextView();

        txt_viewTeachers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateTeacherFields(newSelection);
            }
        });
    }

    private void updateTeacherFields(Teacher teacher) {

        Teacher selectedTeacher = txt_viewTeachers.getSelectionModel().getSelectedItem();

        if (selectedTeacher != null) {

            txt_nameTeacher.setText(selectedTeacher.getName());
            txt_lastName1Teacher.setText(selectedTeacher.getLastName1());
            txt_lastName2Teacher.setText(selectedTeacher.getLastName2());
            txt_curpTeacher.setText(selectedTeacher.getCurp());
            txt_cel.setText(selectedTeacher.getCellphone());

            Date date = selectedTeacher.getDate();

            if (date != null) {

                txt_dayTeacher.setText(Integer.toString(date.getDay()));
                txt_monthTeacher.setText(Integer.toString(date.getMonth()));
                txt_yearTeacher.setText(Integer.toString(date.getYear()));

            }
        }
    }


    private void createTeacher() throws IOException {

        try {

            int day = Integer.parseInt(txt_dayTeacher.getText());
            int month = Integer.parseInt(txt_monthTeacher.getText());
            int year = Integer.parseInt(txt_yearTeacher.getText());

            date = new Date(day, month, year);

            String name = txt_nameTeacher.getText();
            String lastName1 = txt_lastName1Teacher.getText();
            String lastName2 = txt_lastName2Teacher.getText();
            String curp = txt_curpTeacher.getText();
            String cellphone = txt_cel.getText();

            teacher = new Teacher(name, lastName1, lastName2, curp, cellphone, date);
            DataStore.addTeacher(teacher);

        }catch (InvalidDay | InvalidMonth | InvalidYear e) {

            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorDate.fxml")));
            popupStage.setScene(new Scene(popupRoot));
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();
            popupStage.centerOnScreen();
            popupStage.setX(coordinateX);
            popupStage.setY(coordinateY);


        }catch (InvalidName e) {

            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorName.fxml")));
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
    private void btnCreateTeacher() throws IOException {

        createTeacher();

        if (teacher != null){
            clearTeacher();
        }
    }

    @FXML
    private void btnDeleteTeacher() {

        Teacher selectedTeacher = txt_viewTeachers.getSelectionModel().getSelectedItem();

        if (selectedTeacher != null) {
            DataStore.removeTeacher(selectedTeacher);
        }

        clearTeacher();
    }

    @FXML
    private void btnUpdateTeacher() throws IOException {

        Teacher selectedTeacher = txt_viewTeachers.getSelectionModel().getSelectedItem();

        int day = Integer.parseInt(txt_dayTeacher.getText());
        int month = Integer.parseInt(txt_monthTeacher.getText());
        int year = Integer.parseInt(txt_yearTeacher.getText());

        Date newDate = null;
        try {

            newDate = new Date(day, month, year);

        } catch (InvalidDay | InvalidMonth | InvalidYear e) {

            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorDate.fxml")));
            popupStage.setScene(new Scene(popupRoot));
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();
            popupStage.centerOnScreen();
            popupStage.setX(coordinateX);
            popupStage.setY(coordinateY);
        }

        if (selectedTeacher != null) {

            try {
                Teacher updatedTeacher = new Teacher(

                        txt_nameTeacher.getText(),
                        txt_lastName1Teacher.getText(),
                        txt_lastName2Teacher.getText(),
                        txt_curpTeacher.getText(),
                        txt_cel.getText(),
                        newDate
                );

                DataStore.updateTeacher(selectedTeacher, updatedTeacher);
                txt_viewTeachers.refresh();

            } catch(InvalidName e) {

                Stage popupStage = new Stage();
                Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorName.fxml")));
                popupStage.setScene(new Scene(popupRoot));
                popupStage.initStyle(StageStyle.UNDECORATED);
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.show();
                popupStage.centerOnScreen();
                popupStage.setX(coordinateX);
                popupStage.setY(coordinateY);
            }

        }
    }

    @FXML
    private void UpdateTextView() {

        ObservableList<Teacher> teachers = DataStore.getTeachers();

        txt_nT.setCellValueFactory(new PropertyValueFactory<>("name"));
        txt_a1T.setCellValueFactory(new PropertyValueFactory<>("lastName1"));
        txt_a2T.setCellValueFactory(new PropertyValueFactory<>("lastName2"));
        txt_cT.setCellValueFactory(new PropertyValueFactory<>("curp"));
        txt_clT.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
        txt_fT.setCellValueFactory(new PropertyValueFactory<>("date"));

        txt_viewTeachers.setItems(teachers);
    }

    @FXML
    private void clearTeacher() {

        txt_nameTeacher.clear();
        txt_lastName1Teacher.clear();
        txt_lastName2Teacher.clear();
        txt_curpTeacher.clear();
        txt_cel.clear();

        txt_dayTeacher.clear();
        txt_monthTeacher.clear();
        txt_yearTeacher.clear();
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
    private void btnCourses() throws IOException {
        App.setRoot("Course");
    }

    @FXML
    private void btnRegistered() throws IOException {
        App.setRoot("Registered");
    }

    @FXML
    private TableColumn<Teacher, String> txt_a1T;

    @FXML
    private TableColumn<Teacher, String> txt_a2T;

    @FXML
    private TableColumn<Teacher, String> txt_cT;

    @FXML
    private TableColumn<Teacher, String> txt_fT;

    @FXML
    private TableColumn<Teacher, String> txt_clT;

    @FXML
    private TableColumn<Teacher, String> txt_nT;

    @FXML
    private TextField txt_cel;

    @FXML
    private TextField txt_curpTeacher;

    @FXML
    private TextField txt_dayTeacher;

    @FXML
    private TextField txt_lastName1Teacher;

    @FXML
    private TextField txt_lastName2Teacher;

    @FXML
    private TextField txt_monthTeacher;

    @FXML
    private TextField txt_nameTeacher;

    @FXML
    private TableView<Teacher> txt_viewTeachers;

    @FXML
    private TextField txt_yearTeacher;
}
