package model;

import exceptions.InvalidDay;
import exceptions.InvalidMonth;
import exceptions.InvalidYear;

public class Date {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) throws InvalidDay, InvalidMonth, InvalidYear {

        if (day < 1 || day > 31) {
            throw new InvalidDay("Dia Invalido: " + day);
        }else {
            this.day = day;
        }
        if (month < 1 || month > 12) {
            throw new InvalidMonth("Mes Invalido: " + month);
        }else {
            this.month = month;
        }
        if (year < 1900 || year > 2100) {
            throw new InvalidYear("Año invalido: " + year);
        }else {
            this.year = year;
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) throws InvalidDay {

        if (day < 1 || day > 31) {
            throw new InvalidDay("Dia Invalido: " + day);
        }else {
            this.day = day;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) throws InvalidMonth {

        if (month < 1 || month > 12) {
            throw new InvalidMonth("Mes Invalido: " + month);
        }else {
            this.month = month;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws InvalidYear {

        if (year < 1900 || year > 2100) {
            throw new InvalidYear("Año invalido: " + year);
        }else {
            this.year = year;
        }
    }
}
