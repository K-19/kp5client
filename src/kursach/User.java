package kursach;

import javafx.beans.property.*;

public class User {
    private static User user;

    private Integer iD;
    private String nPassport;
    private String nIdPassport;
    private String nPhone;
    private String email;
    private String password;
    private String birthday;
    private String registrDay;
    private Boolean isAdmin;
    private String login;
    private String name;
    private String lastName;

    public User(Integer iD, String nPassport, String nIdPassport, String nPhone, String email, String password, String birthday, String registrDay, Boolean isAdmin, String login, String name, String lastName) {
        this.iD = iD;
        this.nPassport = nPassport;
        this.nIdPassport = nIdPassport;
        this.nPhone = nPhone;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.registrDay = registrDay;
        this.isAdmin = isAdmin;
        this.login = login;
        this.name = name;
        this.lastName = lastName;
    }

    public User(String info) {
        String[] param = info.split(Const.DELIMITER);
        if (param.length == 12) {
            this.iD = Integer.parseInt(param[0]);
            this.nPassport = param[1];
            this.nIdPassport = param[2];
            this.nPhone = param[3];
            this.email = param[4];
            this.password = param[5];
            this.birthday = param[6];
            this.registrDay = param[7];
            this.isAdmin = Boolean.parseBoolean(param[8]);
            this.login = param[9];
            this.name = param[10];
            this.lastName = param[11];
        }
    }
    public IntegerProperty iDProperty() { return new SimpleIntegerProperty(iD); }
    public StringProperty nPassportProperty() { return new SimpleStringProperty(nPassport); }
    public StringProperty nIdPassportProperty() { return new SimpleStringProperty(nIdPassport); }
    public StringProperty nPhoneProperty() { return new SimpleStringProperty(nPhone); }
    public StringProperty emailProperty() { return new SimpleStringProperty(email); }
    public StringProperty birthdayProperty() { return new SimpleStringProperty(birthday); }
    public StringProperty registrDayProperty() { return new SimpleStringProperty(registrDay); }
    public BooleanProperty isAdminProperty() { return new SimpleBooleanProperty(isAdmin); }
    public StringProperty loginProperty() { return new SimpleStringProperty(login); }
    public StringProperty nameProperty() { return new SimpleStringProperty(name); }
    public StringProperty lastNameProperty() { return new SimpleStringProperty(lastName); }

    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        User.user = user;
    }
    public Integer getId() {
        return iD;
    }
    public void setId(Integer iD) {
        this.iD = iD;
    }
    public String getnPassport() {
        return nPassport;
    }
    public void setnPassport(String nPassport) {
        this.nPassport = nPassport;
    }
    public String getnIdPassport() {
        return nIdPassport;
    }
    public void setnIdPassport(String nIdPassport) { this.nIdPassport = nIdPassport; }
    public String getnPhone() {
        return nPhone;
    }
    public void setnPhone(String nPhone) {
        this.nPhone = nPhone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getRegistrDay() {
        return registrDay;
    }
    public void setRegistrDay(String registrDay) {
        this.registrDay = registrDay;
    }
    public Boolean getAdmin() {
        return isAdmin;
    }
    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "iD=" + iD +
                ", nPassport=" + nPassport +
                ", nIdPassport=" + nIdPassport +
                ", nPhone=" + nPhone +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", registrDay='" + registrDay + '\'' +
                ", isAdmin=" + isAdmin +
                ", login='" + login + '\'' +
                '}';
    }
}
