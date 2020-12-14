package kursach;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Question {
    private Integer id;
    private Integer id_user;
    private String text;

    public Question(Integer id, Integer id_user, String text) {
        this.id = id;
        this.id_user = id_user;
        this.text = text;
    }
    public IntegerProperty idProperty() { return new SimpleIntegerProperty(id); }
    public IntegerProperty id_userProperty() { return new SimpleIntegerProperty(id_user); }
    public StringProperty textProperty() { return new SimpleStringProperty(text); }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId_user() {
        return id_user;
    }
    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
