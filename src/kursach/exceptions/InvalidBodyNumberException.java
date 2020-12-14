package kursach.exceptions;

public class InvalidBodyNumberException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Номер кузова не соответствует формату";
    }
}
