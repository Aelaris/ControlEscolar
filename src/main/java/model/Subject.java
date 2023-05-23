package model;

import exceptions.ClaveSubject;
import exceptions.CreditsSubject;
import exceptions.SemesterSubject;

public class Subject {

    private String clave;
    private String name;
    private String semester;
    private String credits;

    public Subject(){}

    public Subject(String clave, String name, String semester, String credits) throws ClaveSubject, SemesterSubject,
            CreditsSubject {

        if (!checkClave(clave, semester)) {
            throw new ClaveSubject("La clave de la materia no cumple con el formato requerido.");
        }else {
            this.clave = clave;
        }

        if (semester.length() != 1 || semester.charAt(0) < '1' || semester.charAt(0) > '9'){
            throw new SemesterSubject("La clave de la materia no cumple con el formato requerido.");
        }else{
            this.semester = semester;
        }

        if (!credits.equals("3") && !credits.equals("4") && !credits.equals("5") && !credits.equals("6") && !credits.equals("10")){
            throw new CreditsSubject("La clave de la materia no cumple con el formato requerido.");
        }else {
            this.credits = credits;
        }

        this.name = name;
    }

    private boolean checkClave(String clave, String semester) {

        String regex = semester + "[A-Za-z][1-7]";
        return clave.matches(regex);
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) throws ClaveSubject {

        if (!checkClave(clave, semester)) {
            throw new ClaveSubject("La clave de la materia no cumple con el formato requerido.");
        }else {
            this.clave = clave;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) throws SemesterSubject {

        if (semester.length() != 1 || semester.charAt(0) < '1' || semester.charAt(0) > '9'){
            throw new SemesterSubject("La clave de la materia no cumple con el formato requerido.");
        }else{
            this.semester = semester;
        }
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) throws CreditsSubject {

        if (!credits.equals("3") && !credits.equals("4") && !credits.equals("5") && !credits.equals("6") && !credits.equals("10")){
            throw new CreditsSubject("La clave de la materia no cumple con el formato requerido.");
        }else {
            this.credits = credits;
        }
    }

    public String toString(){

        return "CLAVE: "+clave+"\nNOMBRE: "+name+"\nSEMESTRE: "+semester+"\nCREDITOS: "+credits;

    }
}
