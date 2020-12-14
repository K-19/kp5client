package kursach.exceptions;

public class SummException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Страховая выплата неправильного формата";
    }
}
