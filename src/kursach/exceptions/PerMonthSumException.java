package kursach.exceptions;

public class PerMonthSumException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Ежемесячная оплата не соответсвует формату";
    }
}
