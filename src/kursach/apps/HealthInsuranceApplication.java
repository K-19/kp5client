package kursach.apps;

import kursach.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HealthInsuranceApplication extends AbstractInsuranceApplication {
    private boolean healthAccident;           // несчастный случай (здоровье)
    private boolean lifeAccident;             // несчастный случай (жизнь)
    private boolean invalidAccident;          // несчастный случай (инвалидность)
    private boolean deathIllness;             // смерть в результате болезни
    private boolean harmToHealthWithCovid19;  // вред здоровью от COVID-19
    private boolean professionalSportsman;    // занятие профессиональным спортом
    private boolean leisure;                  // занятие активным видом отдыха
    private boolean invalidPerson;            // страхователь инвалид I, II группы


    public HealthInsuranceApplication(int id, User user, long term, Date beginTerm, boolean insuranceFee, boolean typeOfPayment, boolean executePersson, boolean healthAccident, boolean lifeAccident, boolean invalidAccident, boolean deathIllness, boolean harmToHealthWithCovid19, boolean professionalSportsman, boolean leisure, boolean invalidPerson) {
        super(id, user, term, beginTerm, insuranceFee, typeOfPayment, executePersson);
        this.healthAccident = healthAccident;
        this.lifeAccident = lifeAccident;
        this.invalidAccident = invalidAccident;
        this.deathIllness = deathIllness;
        this.harmToHealthWithCovid19 = harmToHealthWithCovid19;
        this.professionalSportsman = professionalSportsman;
        this.leisure = leisure;
        this.invalidPerson = invalidPerson;
    }

    public HealthInsuranceApplication(String[] words) throws ParseException {
        super(Integer.parseInt(words[1]), new User(Integer.parseInt(words[16]), words[17], words[18], words[19], words[20], words[21], words[22], words[23], Boolean.parseBoolean(words[24]), words[25], words[26], words[27]),
                Long.parseLong(words[3]), new SimpleDateFormat("dd-MM-yyyy").parse(words[4]), Boolean.parseBoolean(words[5]), Boolean.parseBoolean(words[6]), Boolean.parseBoolean(words[7]));
                this.healthAccident = Boolean.parseBoolean(words[8]);
                this.lifeAccident = Boolean.parseBoolean(words[9]);
                this.invalidAccident = Boolean.parseBoolean(words[10]);
                this.deathIllness = Boolean.parseBoolean(words[11]);
                this.harmToHealthWithCovid19 = Boolean.parseBoolean(words[12]);
                this.professionalSportsman = Boolean.parseBoolean(words[13]);
                this.leisure = Boolean.parseBoolean(words[14]);
                this.invalidPerson = Boolean.parseBoolean(words[15]);
    }

    public boolean isHealthAccident() { return healthAccident; }
    public void setHealthAccident(boolean healthAccident) { this.healthAccident = healthAccident; }
    public boolean isLifeAccident() { return lifeAccident; }
    public void setLifeAccident(boolean lifeAccident) { this.lifeAccident = lifeAccident; }
    public boolean isInvalidAccident() { return invalidAccident; }
    public void setInvalidAccident(boolean invalidAccident) { this.invalidAccident = invalidAccident; }
    public boolean isDeathIllness() { return deathIllness; }
    public void setDeathIllness(boolean deathIllness) { this.deathIllness = deathIllness; }
    public boolean isHarmToHealthWithCovid19() { return harmToHealthWithCovid19; }
    public void setHarmToHealthWithCovid19(boolean harmToHealthWithCovid19) { this.harmToHealthWithCovid19 = harmToHealthWithCovid19; }
    public boolean isProfessionalSportsman() { return professionalSportsman; }
    public void setProfessionalSportsman(boolean professionalSportsman) { this.professionalSportsman = professionalSportsman; }
    public boolean isLeisure() { return leisure; }
    public void setLeisure(boolean leisure) { this.leisure = leisure; }
    public boolean isInvalidPerson() { return invalidPerson; }
    public void setInvalidPerson(boolean invalidPerson) { this.invalidPerson = invalidPerson; }

    public static String info() {
        return "Проблемы, связанные с непредвиденным расстройством здоровья или возникновением угрозы жизни могут появиться в любой момент — в дороге, " +
                "дома, при поездке на транспорте, путешествии за границу. Как правило, подобные ситуации застигают врасплох, " +
                "человек оказывается не готов к их решению и в результате теряет время, деньги, нервы и, самое главное — здоровье.\n" +
                "\n" +
                "Медицинское страхование поможет справиться с любой подобной ситуацией легко и без ощутимых материальных вложений.";
    }
}
