package kursach.exceptions;

public class DateFormatException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Дата указана неверно";
    }
}
