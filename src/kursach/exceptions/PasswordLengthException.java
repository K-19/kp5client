package kursach.exceptions;

public class PasswordLengthException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Пароль должен содержать от 5-ти до 50-ти символов";
    }
}
