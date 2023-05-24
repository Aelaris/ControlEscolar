package repository;

import exceptions.InvalidName;
import exceptions.InvalidStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class DataStore {

    private static ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    private static ObservableList<Subject> subjects = FXCollections.observableArrayList();
    private static ObservableList<Course> courses = FXCollections.observableArrayList();
    private static ObservableList<Student> students = FXCollections.observableArrayList();
    private static ObservableList<StudentCourses> studentCourses = FXCollections.observableArrayList();


    public static ObservableList<StudentCourses> getStudentCourses() {
        return studentCourses;
    }

    public static void addStudentCourses(StudentCourses newStudentCourses) {
        studentCourses.add(newStudentCourses);
    }

    public static ObservableList<Teacher> getTeachers() {
        return teachers;
    }

    public static ObservableList<Course> getCourses() {
        return courses;
    }

    public static ObservableList<Subject> getSubjects() {
        return subjects;
    }

    public static ObservableList<Student> getStudents() {
        return students;
    }

    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public static void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public static void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    public static void removeCourse(Course course) {
        courses.remove(course);
    }

    public static void removeStudent(Student student) {
        students.remove(student);
    }

    public static void updateTeacher(Teacher oldTeacher, Teacher newTeacher) throws InvalidName {

        oldTeacher.setName(newTeacher.getName());
        oldTeacher.setLastName1(newTeacher.getLastName1());
        oldTeacher.setLastName2(newTeacher.getLastName2());
        oldTeacher.setCurp(newTeacher.getCurp());
        oldTeacher.setCellphone(newTeacher.getCellphone());
        oldTeacher.setDate(newTeacher.getDate());

    }

    public static void updateStudent(Student oldStudent, Student newStudent) throws InvalidName, InvalidStatus {

        oldStudent.setName(newStudent.getName());
        oldStudent.setLastName1(newStudent.getLastName1());
        oldStudent.setLastName2(newStudent.getLastName2());
        oldStudent.setNumControl(newStudent.getNumControl());
        oldStudent.setCurp(newStudent.getCurp());
        oldStudent.setCellphone(newStudent.getCellphone());
        oldStudent.setDate(newStudent.getDate());
        oldStudent.setGender(newStudent.getGender());
        oldStudent.setStatus(newStudent.getStatus());

    }

    public static void updateSubject(Subject oldSubject, Subject newSubject) {

    }
}
