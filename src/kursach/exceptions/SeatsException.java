package kursach.exceptions;

public class SeatsException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Количество сидений неправильного формата";
    }
}
