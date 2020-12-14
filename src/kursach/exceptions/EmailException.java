package kursach.exceptions;

public class EmailException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Адрес электронной почты не соответствует формату";
    }
}
