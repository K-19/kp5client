package kursach.apps;

import kursach.User;

import java.util.Date;

public abstract class AbstractInsuranceApplication {
    protected int id;
    protected User user;                        // страхователь
    protected long term;                        // срок
    protected Date beginTerm;                   // начало действия
    protected boolean insuranceFee;             // страховой взнос уплачен (единовременно, поэтапно)
    protected boolean typeOfPayment;            // вид оплаты (нал, безнал)
    protected boolean executePersson;           // должностное лицо

    AbstractInsuranceApplication(int id, User user, long term, Date beginTerm, boolean insuranceFee, boolean typeOfPayment, boolean executePersson) {
        this.id = id;
        this.user = user;
        this.term = term;
        this.beginTerm = beginTerm;
        this.insuranceFee = insuranceFee;
        this.typeOfPayment = typeOfPayment;
        this.executePersson = executePersson;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public long getTerm() {
        return term;
    }
    public void setTerm(long term) {
        this.term = term;
    }
    public Date getBeginTerm() {
        return beginTerm;
    }
    public void setBeginTerm(Date beginTerm) {
        this.beginTerm = beginTerm;
    }
    public boolean isInsuranceFee() {
        return insuranceFee;
    }
    public void setInsuranceFee(boolean insuranceFee) {
        this.insuranceFee = insuranceFee;
    }
    public boolean isTypeOfPayment() {
        return typeOfPayment;
    }
    public void setTypeOfPayment(boolean typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }
    public boolean isExecutePersson() {
        return executePersson;
    }
    public void setExecutePersson(boolean executePersson) {
        this.executePersson = executePersson;
    }
}
