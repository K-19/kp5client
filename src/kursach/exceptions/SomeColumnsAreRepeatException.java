package kursach.exceptions;

public class SomeColumnsAreRepeatException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Пользователь с таким логином/номером паспорта/ид. номером паспорта уже зарегистрирован";
    }
}
