package kursach;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class App {
    private IntegerProperty numb;
    private StringProperty type;
    private StringProperty name;
    private StringProperty number;

    public App(Integer numb, String type, String name, String number) {
        this.numb = new SimpleIntegerProperty(numb);
        this.type = new SimpleStringProperty(type);
        this.name = new SimpleStringProperty(name);
        this.number = new SimpleStringProperty(number);
    }

    public IntegerProperty numbProperty() { return numb; }
    public StringProperty typeProperty() {
        return type;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty numberProperty() {
        return number;
    }
}
