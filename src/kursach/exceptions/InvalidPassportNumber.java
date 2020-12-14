package kursach.exceptions;

public class InvalidPassportNumber extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Номер пасспорта должен соответствовать формату";
    }
}
