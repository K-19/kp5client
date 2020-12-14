package kursach.exceptions;

public class AddressException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Адрес неправильного формата";
    }
}
