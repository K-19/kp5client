package kursach.exceptions;

public class UserNotFoundException  extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Пользователь с такими данными не найден";
    }
}
