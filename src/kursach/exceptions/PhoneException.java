package kursach.exceptions;

public class PhoneException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Номер телефона не соответствует формату";
    }
}
