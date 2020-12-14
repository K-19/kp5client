package kursach;

import kursach.exceptions.*;

import java.text.SimpleDateFormat;

public class TestInput {
    public static void authFieldAreOk(String login, String pass) throws LoginLengthException, PasswordLengthException {
        if (login.length() > 50 || login.length() < 5) throw new LoginLengthException();
        if (pass.length() > 50 || pass.length() < 5) throw new PasswordLengthException();
    }
    public static void registrationFieldsAreOk(String[] words)
            throws Exception {

        if (words.length != 12) throw new InvalidLengthArrayParametrs();
        if (words[6].length() > 50 || words[6].length() < 5) throw new LoginLengthException();
        if (words[1].length() > 50 || words[1].length() < 5) throw new PasswordLengthException();
        testPassword(words[0], words[1]);
        testNPassport(words[2]);
        testIdNPassword(words[3]);
        testEmail(words[5]);
        testPhone(words[4]);
        testDate(words[7]);
        if ("0".equals(words[8]) || "0".equals(words[9])) throw new CheckBoxException();
        testName(words[10]);
        testName(words[11]);
    }

    public static void editUserFieldsAreOk(String[] words) throws Exception{
            registrationFieldsAreOk(new String[]{words[4], words[4], words[0], words[1], words[2], words[3], words[6], words[5], "1", "1", words[7], words[8]});
    }

    public static void vehicleStrahIsOk(String model, String regNumb, String year, String body, String vEngine, String lifting, String seats, String powerEngine, String accidents)
        throws Exception {
        testRegNumber(regNumb);
        testYearCreated(year);
        testBodyNumber(body);
        testVEngine(vEngine);
        testLifting(lifting);
        testIntValue(seats, 1, Const.MAX_SEATS_IN_CAR, new SeatsException());
        testIntValue(powerEngine, 1, Const.MAX_POWER_ENGINE, new PowerEngineException());
        testIntValue(accidents, 0, Const.MAX_ACCIDENTS, new AccidentsException());
    }

    public static void propertyStrahIsOk(String address, String area, String rooms, String year, String floor, String doors)
            throws Exception {
        testAddress(address);
        testIntValue(area, 1, Const.MAX_AREA_PROPERTY, new AreaException());
        testIntValue(rooms, 1, Const.MAX_ROOMS_PROPERTY, new RoomsException());
        testYearCreated(year);
        testIntValue(floor, 1, Const.MAX_FLOOR_PROPERTY, new FloorException());
        testIntValue(doors, 1, Const.MAX_FLOOR_PROPERTY, new DoorsException());
    }

    public static void userStrahIsOk(String name, String lastName, String pasport, String idPasport, String phone, String email, String dolj) throws Exception {
        testName(name);
        testName(lastName);
        testNPassport(pasport);
        testIdNPassword(idPasport);
        testEmail(email);
        testPhone(phone);
        if (!"Является должностным лицом".equals(dolj) && !"Не является должностным лицом".equals(dolj)) throw new DoljException();
    }
    public static void userStrahIsOk2(String name, String lastName, String pasport, String idPasport, String phone, String email, String experiance, String dolj) throws Exception {
        testIntValue(experiance, 0, Const.MAX_EXP, new ExcperianceException());
        userStrahIsOk(name, lastName, pasport, idPasport, phone, email, dolj);
    }

    public static void mainStrahIsOk(String term, String fee, String beginTerm, String typeMoney, String summ, String perMonth) throws Exception {
        testIntValue(term, 1, Const.MAX_TERM_YEARS, new TermAppException());
        if (!"Единовременно".equals(fee) && !"Поэтапно".equals(fee)) throw new FeeException();
        if (!"Наличными".equals(typeMoney) && !"Банковской картой".equals(typeMoney)) throw new TypeMoneyException();
        if (!isNumber(summ)) throw new SummException();
        if (Integer.parseInt(summ) == 0) throw new SummException();
        testPerMonth(summ, perMonth);
        testDate(beginTerm);
    }
    private static void testPerMonth(String sum, String perMonth) throws PerMonthSumException{
        if (!isNumber(perMonth)) throw new PerMonthSumException();
        if (Integer.parseInt(perMonth) == 0) throw new PerMonthSumException();
        //if (Double.parseDouble(sum) % Double.parseDouble(perMonth) > 2) throw new PerMonthSumException();
    }

    private static void testName(String name) throws NameFieldException{
        if (name.length() < 2 || name.length() > 50) throw new NameFieldException();
        if (!isCharacters(name)) throw new NameFieldException();
        if (!Character.isUpperCase(name.charAt(0))) throw new NameFieldException();
        for (int i = 1; i < name.length(); i++) {
            if(!Character.isLowerCase(name.charAt(i))) throw new NameFieldException();
        }
    }

    public static void vehicleAppFieldsAreOk(String model, String regNumber, String year, String bodyNumb, String vEngine, String lifting, String seats, String powerEngine, String accidents, String battery, String term, String exp)
            throws Exception {

        testRegNumber(regNumber);
        testYearCreated(year);
        testBodyNumber(bodyNumb);
        testVEngine(vEngine);
        testLifting(lifting);
        testIntValue(seats, 1, Const.MAX_SEATS_IN_CAR, new SeatsException());
        testIntValue(powerEngine, 1, Const.MAX_POWER_ENGINE, new PowerEngineException());
        testIntValue(accidents, 0, Const.MAX_ACCIDENTS, new AccidentsException());
        if (!"not".equals(battery))
            testIntValue(battery, 1, Const.MAX_BATTERY, new BatteryException());
        testIntValue(term, 1, Const.MAX_TERM_YEARS, new TermAppException());
        testIntValue(exp, 0, Const.MAX_EXP, new ExcperianceException());
    }

    public static void propertyAppFieldsAreOk(String adress, String area, String rooms, String year, String floor, String doors, String term) throws Exception {
        testAddress(adress);
        testIntValue(area, 1, Const.MAX_AREA_PROPERTY, new AreaException());
        testIntValue(rooms, 1, Const.MAX_ROOMS_PROPERTY, new RoomsException());
        testYearCreated(year);
        testIntValue(floor, 1, Const.MAX_FLOOR_PROPERTY, new FloorException());
        testIntValue(doors, 1, Const.MAX_FLOOR_PROPERTY, new DoorsException());
        testIntValue(term, 1, Const.MAX_TERM_YEARS, new TermAppException());
    }

    public static void testIntValue(String value, int min, int max, Exception exception) throws Exception {
        try {
            int num = Integer.parseInt(value);
            if (num > max || num < min) throw exception;
        } catch (Exception e) {
            throw exception;
        }
    }

    private static void testAddress(String address) throws AddressException{

    }


    private static void testVEngine(String vEngine) throws VEngineException {
        try {
            Double.parseDouble(vEngine);
        } catch (Exception e) {
            throw new VEngineException();
        }
    }

    private static void testLifting(String lifting) throws LiftingException {
        try {
            testVEngine(lifting);
        } catch (Exception e) {
            throw new LiftingException();
        }
    }

    private static void testBodyNumber(String bodyNumb) throws InvalidBodyNumberException {
        if(bodyNumb.length() != 6) throw new InvalidBodyNumberException();
        if(!isNumber(bodyNumb)) throw new InvalidBodyNumberException();
    }



    private static void testRegNumber(String number) throws RegNumberException {
        if (number.length() != 8) throw new RegNumberException();
        if (!isNumber(number.substring(0, 4))) throw new RegNumberException();
        if (!isCharacters(number.substring(4, 6))) throw new RegNumberException();
        if (!"-".equals(number.substring(6, 7))) throw new RegNumberException();
        if (!isNumber(number.substring(7, 8))) throw new RegNumberException();
    }

    private static void testYearCreated(String year) throws YearCreatedException {
        if(year.length() != 4) throw new YearCreatedException();
        if(!isNumber(year)) throw new YearCreatedException();
        if(Integer.parseInt(year) > 2020) throw new YearCreatedException();
    }

    private static void testPassword(String pass, String confirmPass) throws PasswordNotComplete{
        if (!pass.equals(confirmPass)) throw new PasswordNotComplete();
    }
    private static void testNPassport(String nPass) throws InvalidPassportNumber {
        if (nPass.length() != 9) throw new InvalidPassportNumber();
            String codeRegion = nPass.substring(0, 2);
            String numbers = nPass.substring(2, nPass.length());
            if (!codeRegion.equals("AB") && !codeRegion.equals("BM") && !codeRegion.equals("HB") && !codeRegion.equals("KH") && !codeRegion.equals("MP") && !codeRegion.equals("MC") && !codeRegion.equals("KB") && !codeRegion.equals("PP") && !codeRegion.equals("SP") && !codeRegion.equals("DP"))
                throw new InvalidPassportNumber();
            if(!isNumber(numbers)) throw new InvalidPassportNumber();
    }
    private static void testIdNPassword(String iDNPass) throws InvalidIdentificationNumberPassport {
        if (iDNPass.length() != 14) throw new InvalidIdentificationNumberPassport();
        if (!isNumber(iDNPass.substring(0, 7))) throw new InvalidIdentificationNumberPassport();
        if (!isCharacters(iDNPass.substring(7, 8))) throw new InvalidIdentificationNumberPassport();
        if (!isNumber(iDNPass.substring(8, 11))) throw new InvalidIdentificationNumberPassport();
        if (!isCharacters(iDNPass.substring(11, 13))) throw new InvalidIdentificationNumberPassport();
        if (!isNumber(iDNPass.substring(13, 14))) throw new InvalidIdentificationNumberPassport();
    }
    private static void testEmail(String email) throws EmailException {
        if ((email.length() > 70) || !email.contains("@")) throw new EmailException();
        if((email.indexOf("@") == 0) || (email.indexOf("@") == email.length() - 1)) throw new EmailException();
        String lastEmail = email.substring(email.indexOf("@") + 1);
        if (!"mail.ru".equals(lastEmail)) throw new EmailException();
    }
    private static void testPhone(String phone) throws PhoneException {
        if (phone.length() > 12 || phone.length() < 10) throw new PhoneException();
        if (!isNumber(phone)) throw new PhoneException();
    }
    private static void testDate(String strDate) throws DateFormatException {
        if (strDate == null) throw new DateFormatException();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            String format = simpleDateFormat.format(simpleDateFormat.parse(strDate));
        } catch (Exception e) {
            throw new DateFormatException();
        }
    }
    private static boolean isNumber(String word) {
        try {
            Long.parseLong(word);
            return true;
        } catch (Exception e) {
            try {
                Double.parseDouble(word);
                    return true;
                } catch (Exception e1) {
                    return false;
                }
        }
    }
    private static boolean isCharacters(String word) {
        for (Character ch : word.toCharArray()) {
            if (!Character.isLetter(ch)) return false;
        }
        return true;
    }
}
