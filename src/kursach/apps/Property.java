package kursach.apps;

public class Property {
    private String address;                      // адрес
    private Integer area;
    private Integer rooms;
    private Integer year;
    private Integer floor;                       // этаж
    private boolean steelDoor;                   // металлическая дверь
    private Integer doors;                       // входов
    private boolean combinationLock;             // кодовый замок
    private boolean fireAlarm;                   // противопожарная сигнализация
    private boolean securityAlarm;               // охранная сигнализация
    private boolean refractoryBuildingMaterials; // огнеупорные стройматериалы
    private boolean highRiskArea;                // зона повышенного риска

    public Property(String address, Integer area, Integer rooms, Integer year, Integer floor, boolean steelDoor, Integer doors,
                    boolean combinationLock, boolean fireAlarm, boolean securityAlarm, boolean refractoryBuildingMaterials, boolean highRiskArea) {
        this.address = address;
        this.area = area;
        this.rooms = rooms;
        this.year = year;
        this.floor = floor;
        this.steelDoor = steelDoor;
        this.doors = doors;
        this.combinationLock = combinationLock;
        this.fireAlarm = fireAlarm;
        this.securityAlarm = securityAlarm;
        this.refractoryBuildingMaterials = refractoryBuildingMaterials;
        this.highRiskArea = highRiskArea;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Integer getArea() { return area; }
    public void setArea(Integer area) { this.area = area; }
    public Integer getRooms() { return rooms; }
    public void setRooms(Integer rooms) { this.rooms = rooms; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getFloor() { return floor; }
    public void setFloor(Integer floor) { this.floor = floor; }
    public boolean isSteelDoor() { return steelDoor; }
    public void setSteelDoor(boolean steelDoor) { this.steelDoor = steelDoor; }
    public Integer getDoors() { return doors; }
    public void setDoors(Integer doors) { this.doors = doors; }
    public boolean isCombinationLock() { return combinationLock; }
    public void setCombinationLock(boolean combinationLock) { this.combinationLock = combinationLock; }
    public boolean isFireAlarm() { return fireAlarm; }
    public void setFireAlarm(boolean fireAlarm) { this.fireAlarm = fireAlarm; }
    public boolean isSecurityAlarm() { return securityAlarm; }
    public void setSecurityAlarm(boolean securityAlarm) { this.securityAlarm = securityAlarm; }
    public boolean isRefractoryBuildingMaterials() { return refractoryBuildingMaterials; }
    public void setRefractoryBuildingMaterials(boolean refractoryBuildingMaterials) { this.refractoryBuildingMaterials = refractoryBuildingMaterials; }
    public boolean isHighRiskArea() { return highRiskArea; }
    public void setHighRiskArea(boolean highRiskArea) { this.highRiskArea = highRiskArea; }
}
