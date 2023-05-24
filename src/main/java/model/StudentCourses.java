package model;

import javafx.collections.ObservableList;

public class StudentCourses {

    private final Student student;
    private final ObservableList<Course> courses;

    public StudentCourses(Student student, ObservableList<Course> courses) {
        this.student = student;
        this.courses = courses;
    }

    public Student getStudent() {
        return student;
    }

    public ObservableList<Course> getCourses() {
        return courses;
    }


}
