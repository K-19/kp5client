package kursach.exceptions;

public class PasswordNotComplete extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Пароли не совпадают";
    }
}
