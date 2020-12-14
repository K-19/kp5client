package kursach.contracts;

import javafx.beans.property.*;
import kursach.App;

public class SimpleContractForTable extends App {
    private DoubleProperty sum;
    private DoubleProperty perMonth;

    public SimpleContractForTable(Integer numb, String type, String name, String number, Double sum, Double perMonth) {
        super(numb, type, name, number);
        this.sum = new SimpleDoubleProperty(sum);
        this.perMonth = new SimpleDoubleProperty(perMonth);
    }

    public DoubleProperty sumProperty() {
        return sum;
    }
    public DoubleProperty perMonthProperty() {
        return perMonth;
    }
}
