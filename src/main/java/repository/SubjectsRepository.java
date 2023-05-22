package repository;

import model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectsRepository {

    private List<Subject> subjects = new ArrayList<Subject>();



    public void createSubject(Subject subject) {

        subjects.add(subject);

    }

    public Subject readSubject(String clave) {

        for (Subject subject : subjects) {
            if (subject.getClave().equals(clave)) {
                return subject;
            }
        }
        return null;
    }

    public void updateSubject(Subject updatedSubject) {

        for (int i = 0; i < subjects.size(); i++) {
            Subject subject = subjects.get(i);
            if (subject.getClave().equals(updatedSubject.getClave())) {
                subjects.set(i, updatedSubject);
                return;
            }
        }
    }

    public void deleteSubject(String clave) {

        subjects.removeIf(subject -> subject.getClave().equals(clave));

    }

    public List<Subject> getSubject() {
        return subjects;
    }

}
