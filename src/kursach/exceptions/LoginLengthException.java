package kursach.exceptions;

public class LoginLengthException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Логин должен содержать от 5-ти до 50-ти символов";
    }
}
