package kursach.exceptions;

public class YearCreatedException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Год выпуска не соответсвует формату";
    }
}
