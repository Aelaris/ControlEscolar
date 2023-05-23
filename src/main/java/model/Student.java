package model;

import exceptions.*;


public class Student {

    private String numControl, name, lastName1, lastName2, curp, cellphone, gender, status;
    private Date date;

    public Student(){}

    public Student(String name, String lastName1, String lastName2, String numControl, String curp, String cellphone,
                   Date date, String gender, String status) throws InvalidName, InvalidStatus {

        if (!name.matches("[a-zA-Z]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.name = name;
        }
        if (!lastName1.matches("[a-zA-Z]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.lastName1 = lastName1;
        }
        if (!lastName2.matches("[a-zA-Z]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.lastName2 = lastName2;
        }
        if (status == null || status.isEmpty()) {
            this.status = "Inscrito";
        } else {
            if (status.length() != 3 ||
                    status.charAt(0) != 'B' ||
                    (status.charAt(1) != 'D' && status.charAt(1) != 'T') ||
                    !Character.isDigit(status.charAt(2)) ||
                    status.charAt(2) == '0') {
                throw new InvalidStatus("Formato de status inválido. Debe seguir el formato: 'BD' o 'BT' seguido de un dígito (1-9).");
            } else {
                this.status = status;
            }
        }

        this.numControl = numControl;
        this.curp = curp;
        this.cellphone = cellphone;
        this.date = date;
        this.gender = gender;

    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidName {

        if (!name.matches("[a-zA-Z]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.name = name;
        }
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) throws InvalidName {

        if (!lastName1.matches("[a-zA-Z]+")){
            throw new InvalidName("El nombre no tiene el formato adecuado.");
        }else {
            this.lastName1 = lastName1;
        }
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) throws InvalidName {

        if (!lastName2.matches("[a-zA-Z]+")){
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws InvalidStatus {

        if (status == null || status.isEmpty()) {
            this.status = "Inscrito";
        } else {
            if (status.length() != 3 ||
                    status.charAt(0) != 'B' ||
                    (status.charAt(1) != 'D' && status.charAt(1) != 'T') ||
                    !Character.isDigit(status.charAt(2)) ||
                    status.charAt(2) == '0') {
                throw new InvalidStatus("Formato de status inválido. Debe seguir el formato: 'BD' o 'BT' seguido de un dígito (1-9).");
            } else {
                this.status = status;
            }
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
