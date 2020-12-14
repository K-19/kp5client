package kursach.exceptions;

public class InvalidIdentificationNumberPassport extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Идентификационный номер не соответствует формату";
    }
}
