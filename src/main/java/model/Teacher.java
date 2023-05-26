package model;

import exceptions.InvalidName;

public class Teacher {

    private String name, lastName1, lastName2, curp, cellphone;
    private Date date;

    public Teacher(){}

    public Teacher(String name, String lastName1, String lastName2, String curp, String cellphone, Date date) throws InvalidName {

        if (!name.matches("[a-zA-Z ]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.name = name;
        }
        if (!lastName1.matches("[a-zA-Z ]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.lastName1 = lastName1;
        }
        if (!lastName2.matches("[a-zA-Z ]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.lastName2 = lastName2;
        }

        this.curp = curp;
        this.cellphone = cellphone;
        this.date = date;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidName {

        if (!name.matches("[a-zA-Z ]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.name = name;
        }
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) throws InvalidName {

        if (!lastName1.matches("[a-zA-Z ]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.lastName1 = lastName1;
        }
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) throws InvalidName {

        if (!lastName2.matches("[a-zA-Z  ]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.lastName2 = lastName2;
        }
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {

        return "NOMBRE: "+name+" "+lastName1+" "+lastName2+"\nCELULAR: "+cellphone+"\nCURP: "+curp;

    }
}
