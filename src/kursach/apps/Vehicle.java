package kursach.apps;

public class Vehicle {
    protected String model;                  // модель
    protected String registerSign;           // регистрационный знак
    protected String bodyNumber;             // номер кузова(шасси)
    protected double engineDisplacement;     // рабочий объем двигателя (л)
    protected double liftingCapacity;        // грузоподъемность
    protected int numberOfSeats;             // число посадочных мест
    protected int enginePower;               // мощность двигателя (л.с.  P.S. МВт для электромобиля)
    protected int roadAccidents;             // количество дтп
    protected int yearOfCreated;             // год выпуска

    public Vehicle(String model, String registerSign, String bodyNumber, double engineDisplacement, double liftingCapacity, int numberOfSeats, int enginePower, int roadAccidents, int yearOfCreated) {
        this.model = model;
        this.registerSign = registerSign;
        this.bodyNumber = bodyNumber;
        this.engineDisplacement = engineDisplacement;
        this.liftingCapacity = liftingCapacity;
        this.numberOfSeats = numberOfSeats;
        this.enginePower = enginePower;
        this.roadAccidents = roadAccidents;
        this.yearOfCreated = yearOfCreated;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getRegisterSign() {
        return registerSign;
    }
    public void setRegisterSign(String registerSign) {
        this.registerSign = registerSign;
    }
    public String getBodyNumber() {
        return bodyNumber;
    }
    public void setBodyNumber(String bodyNumber) {
        this.bodyNumber = bodyNumber;
    }
    public double getEngineDisplacement() { return engineDisplacement; }
    public void setEngineDisplacement(double engineDisplacement) { this.engineDisplacement = engineDisplacement; }
    public double getLiftingCapacity() { return liftingCapacity; }
    public void setLiftingCapacity(double liftingCapacity) { this.liftingCapacity = liftingCapacity; }
    public int getNumberOfSeats() { return numberOfSeats; }
    public void setNumberOfSeats(int numberOfSeats) { this.numberOfSeats = numberOfSeats; }
    public int getEnginePower() { return enginePower; }
    public void setEnginePower(int enginePower) { this.enginePower = enginePower; }
    public int getRoadAccidents() { return roadAccidents; }
    public void setRoadAccidents(int roadAccidents) { this.roadAccidents = roadAccidents; }
    public int getYearOfCreated() { return yearOfCreated; }
    public void setYearOfCreated(int yearOfCreated) { this.yearOfCreated = yearOfCreated; }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", registerSign='" + registerSign + '\'' +
                ", bodyNumber='" + bodyNumber + '\'' +
                ", engineDisplacement=" + engineDisplacement +
                ", liftingCapacity=" + liftingCapacity +
                ", numberOfSeats=" + numberOfSeats +
                ", enginePower=" + enginePower +
                ", roadAccidents=" + roadAccidents +
                ", yearOfCreated=" + yearOfCreated +
                '}';
    }
}
