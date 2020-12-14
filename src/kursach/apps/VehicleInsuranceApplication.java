package kursach.apps;

import kursach.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VehicleInsuranceApplication extends AbstractInsuranceApplication {
    Vehicle vehicle;            // транспортное средство
    int drivingExperience;      // стаж вождения

    public VehicleInsuranceApplication(int id, User user, long term, Date beginTerm, boolean insuranceFee, boolean typeOfPayment, boolean executePersson, Vehicle vehicle, int drivingExperience) {
        super(id, user, term, beginTerm, insuranceFee, typeOfPayment, executePersson);
        this.vehicle = vehicle;
        this.drivingExperience = drivingExperience;
    }

    public VehicleInsuranceApplication(String[] words) throws ParseException {
        super(Integer.parseInt(words[1]), new User(Integer.parseInt(words[18]), words[19], words[20], words[21], words[22], words[23], words[24], words[25], Boolean.parseBoolean(words[26]), words[27], words[28], words[29]),
                Long.parseLong(words[3]), new SimpleDateFormat("dd-MM-yyyy").parse(words[4]), Boolean.parseBoolean(words[5]), Boolean.parseBoolean(words[6]), Boolean.parseBoolean(words[7]));
        this.vehicle = new Vehicle(words[9], words[10], words[11], Double.parseDouble(words[12]), Double.parseDouble(words[13]),
                Integer.parseInt(words[14]), Integer.parseInt(words[15]), Integer.parseInt(words[16]), Integer.parseInt(words[17]));
        this.drivingExperience = Integer.parseInt(words[8]);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public int getDrivingExperience() {
        return drivingExperience;
    }
    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }
    public static String info() {
        return "Страхование автомобиля — это одна из наших сильных сторон. Мы предлагаем выгодные условия и размер страхового тарифа по страхованию авто, " +
                "индивидуальный подход и оперативное решение вопросов при наступлении страхового случая.\n" +
                "Помните, что автостраховка сможет защитить вас от таких распространенных рисков как повреждение или кража автомобиля. " +
                "Она также сохранит ваше здоровье и поможет с легкостью справиться с любой неблагоприятной жизненной ситуацией. " +
                "Приобрести полис автострахования можно в любой точке продаж страховой компании «ТАСК». С нами ваш авто будет под надежной защитой.";
    }
}
