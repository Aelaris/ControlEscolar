package repository;

import model.Subject;
import model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {

    private List<Teacher> teachers = new ArrayList<>();

    public void createTeacher(Teacher teacher) {

        teachers.add(teacher);

    }

    public Teacher readTeacher(String curp) {

        for (Teacher teacher : teachers) {
            if (teacher.getCurp().equals(curp)) {
                return teacher;
            }
        }
        return null;
    }

    public void updateTeacher(Teacher updatedTeacher) {

        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            if (teacher.getCurp().equals(updatedTeacher.getCurp())) {
                teachers.set(i, updatedTeacher);
                return;
            }
        }
    }

    public void deleteTeacher(String curp) {

        teachers.removeIf(teacher -> teacher.getCurp().equals(curp));

    }

    public List<Teacher> getTeachers() {

        return teachers;
    }
}
