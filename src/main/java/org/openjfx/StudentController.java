package org.openjfx;

import exceptions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Date;
import model.Student;
import repository.DataStore;
import java.io.IOException;
import java.util.Objects;

public class StudentController {

    private Date date;
    private Student student;


    @FXML
    public void initialize() {

        updateTableview();

        txt_viewStudents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateStudentFields(newSelection);
            }
        });
    }

    @FXML
    private void updateTableview(){

        ObservableList<String> gender = FXCollections.observableArrayList("Masculino", "Femenino", "Otro");
        ObservableList<Student> students = DataStore.getStudents();

        txt_nS.setCellValueFactory(new PropertyValueFactory<>("name"));
        txt_lnS1.setCellValueFactory(new PropertyValueFactory<>("lastName1"));
        txt_lnS2.setCellValueFactory(new PropertyValueFactory<>("lastName2"));
        txt_cS.setCellValueFactory(new PropertyValueFactory<>("curp"));
        txt_cellS.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
        txt_fS.setCellValueFactory(new PropertyValueFactory<>("date"));
        txt_gS.setCellValueFactory(new PropertyValueFactory<>("status"));

        txt_viewStudents.setItems(students);
        cb_gender.setItems(gender);
    }


    private void createStudent() throws IOException {

        try {

            int day = Integer.parseInt(txt_dayStudent.getText());
            int month = Integer.parseInt(txt_monthStudent.getText());
            int year = Integer.parseInt(txt_yearStudent.getText());

            date = new Date(day, month, year);

            String name = txt_nameStudent.getText();
            String lastName1 = txt_lastName1Student.getText();
            String lastName2 = txt_lastName2Student.getText();
            String controlNumber = txt_controlNumber.getText();
            String curp = txt_curpStudent.getText();
            String cellphone = txt_cellStudent.getText();
            String gender = cb_gender.getValue();
            String status = txt_status.getText();

            student = new Student(name, lastName1, lastName2, controlNumber, curp, cellphone, date, gender, status);
            DataStore.addStudent(student);

        }catch (InvalidDay | InvalidMonth | InvalidYear e) {

            double coordinateX = 650;
            double coordinateY = 300;

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

            double coordinateX = 650;
            double coordinateY = 300;

            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorName.fxml")));
            popupStage.setScene(new Scene(popupRoot));
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();
            popupStage.centerOnScreen();
            popupStage.setX(coordinateX);
            popupStage.setY(coordinateY);

        } catch (InvalidStatus e) {

            double coordinateX = 650;
            double coordinateY = 300;

            Stage popupStage = new Stage();
            Parent popupRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorStatus.fxml")));
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
    private void btnUpdateStudent() throws InvalidName, IOException, InvalidStatus {

        Student selectedStudent = txt_viewStudents.getSelectionModel().getSelectedItem();

        int day = Integer.parseInt(txt_dayStudent.getText());
        int month = Integer.parseInt(txt_monthStudent.getText());
        int year = Integer.parseInt(txt_yearStudent.getText());

        Date newDate = null;
        try {

            newDate = new Date(day, month, year);

        } catch (InvalidDay | InvalidMonth | InvalidYear ex) {

            double coordinateX = 650;
            double coordinateY = 300;

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

        if (selectedStudent != null) {

            Student updatedStudent = new Student(

                    txt_nameStudent.getText(),
                    txt_lastName1Student.getText(),
                    txt_lastName2Student.getText(),
                    txt_controlNumber.getText(),
                    txt_curpStudent.getText(),
                    txt_cellStudent.getText(),
                    newDate,
                    cb_gender.getValue(),
                    txt_status.getText()

            );

            DataStore.updateStudent(selectedStudent, updatedStudent);

            txt_viewStudents.refresh();
        }
    }

    @FXML
    private void btnDeleteStudent() {

        Student selectedStudent = txt_viewStudents.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            DataStore.removeStudent(selectedStudent);
        }

        clearStudent();

    }

    private void updateStudentFields(Student student) {

        Student selectedStudent = txt_viewStudents.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {

            txt_nameStudent.setText(selectedStudent.getName());
            txt_lastName1Student.setText(selectedStudent.getLastName1());
            txt_lastName2Student.setText(selectedStudent.getLastName2());
            txt_controlNumber.setText(selectedStudent.getNumControl());
            txt_curpStudent.setText(selectedStudent.getCurp());
            txt_cellStudent.setText(selectedStudent.getCellphone());
            txt_status.setText(selectedStudent.getStatus());

            Date date = selectedStudent.getDate();

            if (date != null) {

                txt_dayStudent.setText(Integer.toString(date.getDay()));
                txt_monthStudent.setText(Integer.toString(date.getMonth()));
                txt_yearStudent.setText(Integer.toString(date.getYear()));

            }
        }
    }

    @FXML
    private void btnCreateStudent() throws IOException {

        createStudent();

        if (student != null){
            clearStudent();
        }

    }

    @FXML
    private void clearStudent() {

        txt_nameStudent.clear();
        txt_lastName1Student.clear();
        txt_lastName2Student.clear();
        txt_controlNumber.clear();
        txt_curpStudent.clear();
        txt_cellStudent.clear();
        cb_gender.setValue(null);
        txt_status.clear();

        txt_dayStudent.clear();
        txt_monthStudent.clear();
        txt_yearStudent.clear();

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
    private void btnStudentCourse() throws IOException {
        App.setRoot("StudentCourse");
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
    private TableColumn<Student, String> txt_cS;

    @FXML
    private TableColumn<Student, String> txt_cellS;

    @FXML
    private TextField txt_cellStudent;

    @FXML
    private TextField txt_controlNumber;

    @FXML
    private TextField txt_curpStudent;

    @FXML
    private TextField txt_dayStudent;

    @FXML
    private TableColumn<Student, String> txt_fS;

    @FXML
    private TableColumn<Student, String> txt_gS;

    @FXML
    private TextField txt_lastName1Student;

    @FXML
    private TextField txt_lastName2Student;

    @FXML
    private TableColumn<Student, String> txt_lnS1;

    @FXML
    private TableColumn<Student, String> txt_lnS2;

    @FXML
    private TextField txt_monthStudent;

    @FXML
    private TableColumn<Student, String> txt_nS;

    @FXML
    private TextField txt_nameStudent;

    @FXML
    private TableView<Student> txt_viewStudents;

    @FXML
    private TextField txt_yearStudent;

    @FXML
    private ComboBox<String> cb_gender;

    @FXML
    private TextField txt_status;
}
