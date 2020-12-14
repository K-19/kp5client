package kursach;

import kursach.Main;

import java.io.File;
import java.util.Objects;

public class Start {
    public static void main(String[] args) {
        if(args.length == 1) {
            try {
                Const.PORT = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Неверный порт!!!");
                return;
            }
        }
        Main.main(args);
    }
}
