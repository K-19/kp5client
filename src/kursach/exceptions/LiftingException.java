package kursach.exceptions;

public class LiftingException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Грузоподьемность не соответствует формату";
    }
}
