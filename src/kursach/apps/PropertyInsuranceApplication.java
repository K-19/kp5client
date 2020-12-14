package kursach.apps;

import kursach.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PropertyInsuranceApplication extends AbstractInsuranceApplication{
    Property property;

    public PropertyInsuranceApplication(int id, User user, long term, Date beginTerm, boolean insuranceFee, boolean typeOfPayment, boolean executePersson, Property property) {
        super(id, user, term, beginTerm, insuranceFee, typeOfPayment, executePersson);
        this.property = property;
    }

    public PropertyInsuranceApplication(String[] words) throws ParseException {
        super(Integer.parseInt(words[1]), new User(Integer.parseInt(words[20]), words[21], words[22], words[23], words[24], words[25], words[26], words[27], Boolean.parseBoolean(words[28]), words[29], words[30], words[31]),
                Long.parseLong(words[3]), new SimpleDateFormat("dd-MM-yyyy").parse(words[4]), Boolean.parseBoolean(words[5]), Boolean.parseBoolean(words[6]), Boolean.parseBoolean(words[7]));
        this.property = new Property(words[8], Integer.parseInt(words[9]), Integer.parseInt(words[10]), Integer.parseInt(words[11]), Integer.parseInt(words[12]),
                Boolean.parseBoolean(words[14]), Integer.parseInt(words[13]),  Boolean.parseBoolean(words[15]),  Boolean.parseBoolean(words[16]),  Boolean.parseBoolean(words[17]),
                Boolean.parseBoolean(words[18]),  Boolean.parseBoolean(words[19]));
    }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }

    public static String info() {
        return "Забота о сохранности дома, квартиры, дачи, своего имущества и финансовых сбережений лежит на плечах каждого из нас невидимым грузом.\n" +
                "\n" +
                "Замки и решетки, охранная и пожарная сигнализации уменьшают вероятность утраты имущества, но если несчастье все-таки произошло, мы остаемся наедине с постигшей нас бедой.\n" +
                "\n" +
                "Страхование — самый надежный и недорогой способ защиты имуществен";
    }
}
