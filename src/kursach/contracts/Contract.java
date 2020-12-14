package kursach.contracts;

import kursach.Code;
import kursach.CommunicationWithServer;
import kursach.Const;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Contract {
    private Integer id_app;
    private Integer idAdmin;
    private Integer dateOfPay;
    private Double costPerMonth;
    private Double summOfPayment;

    public Contract(Integer id_app, Integer idAdmin, Integer dateOfPay, Double costPerMonth, Double summOfPayment) {
        this.id_app = id_app;
        this.idAdmin = idAdmin;
        this.dateOfPay = dateOfPay;
        this.costPerMonth = costPerMonth;
        this.summOfPayment = summOfPayment;
    }

    public Contract(String str) {
        String[] strs = str.split(Const.DELIMITER);
        this.id_app = Integer.parseInt(strs[0]);
        this.idAdmin = Integer.parseInt(strs[1]);
        this.dateOfPay = Integer.parseInt(strs[2]);
        this.costPerMonth = Double.parseDouble(strs[3]);
        this.summOfPayment = Double.parseDouble(strs[4]);
    }

    public String toString() {
        try {
            String[] words = (CommunicationWithServer.request(Code.VIEW_FULL_CONTRACT, (Integer.toString(id_app) + Const.DELIMITER + Integer.toString(idAdmin)))).split(Const.DELIMITER);
            String result = "Unknown error";
            StringBuilder builder = new StringBuilder(25500);
            if (id_app >= 300000) {
                File file = new File("info3.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

                while(reader.ready()) {
                    builder.append(reader.readLine()).append("\n");
                }
                result = String.format(builder.toString(), words[0], words[3], words[28], words[27], words[26], words[25],
                        costPerMonth, words[3], words[2], words[26], words[28], words[25], words[27], words[18], words[19]);

            } else if (id_app >= 200000) {
                File file = new File("info2.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

                while(reader.ready()) {
                    builder.append(reader.readLine()).append("\n");
                }
                result = String.format(builder.toString(), words[0], words[3], words[30], words[29], costPerMonth, words[7],
                        words[8], words[9], words[11], words[12], words[5], dateOfPay, words[2], words[2], words[30], words[29],
                        words[22], words[23], words[32], words[31]);


            } else if (id_app >= 100000) {
                File file = new File("info1.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

                while(reader.ready()) {
                    builder.append(reader.readLine()).append("\n");
                }
                result = String.format(builder.toString(), words[0], words[3], words[28] + " " + words[27], "\"СерГосСтрах\"", words[29] + " " + words[30], words[2],
                        words[8], words[9], words[10], words[11], words[12], words[13], words[14], words[16], summOfPayment, dateOfPay,
                        costPerMonth, words[3], words[2], words[28], words[30], words[27], words[29], words[20], words[21]);

            }
            //System.out.println(result);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public Integer getIdApp() { return id_app; }
    public void setIdApp(Integer id_app) { this.id_app = id_app; }
    public Integer getDateOfPay() { return dateOfPay; }
    public Integer getIdAdmin() { return idAdmin; }
    public void setIdAdmin(Integer idAdmin) { this.idAdmin = idAdmin; }
    public void setDateOfPay(Integer dateOfPay) { this.dateOfPay = dateOfPay; }
    public Double getCostPerMonth() { return costPerMonth; }
    public void setCostPerMonth(Double costPerMonth) { this.costPerMonth = costPerMonth; }
    public Double getSummOfPayment() { return summOfPayment; }
    public void setSummOfPayment(Double summOfPayment) { this.summOfPayment = summOfPayment; }

    public String toStringArray() {
        String[] arr = new String[5];
        arr[0] = Integer.toString(getIdApp());
        arr[1] = Integer.toString(idAdmin);
        arr[2] = Integer.toString(getDateOfPay());
        arr[3] = Double.toString(getSummOfPayment());
        arr[4] = Double.toString(getCostPerMonth());

        String result = "";
        for (String temp : arr) {
            result += temp;
            result += Const.DELIMITER;
        }
        return result.substring(0, result.length() - 1);
    }
}
