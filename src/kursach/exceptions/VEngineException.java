package kursach.exceptions;

public class VEngineException extends Exception{
    @Override
    public String toString() {
        return "Ошибка !!! Объем двигателя неправильного формата";
    }
}
