package ru.sber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sber {
    public static List<City> parse() {
        List<City> cities = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File("city_ru.csv"));
            while (sc.hasNextLine()) {
                cities.add(parse(sc.nextLine()));
            }
            sc.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return cities;
    }
    private static City parse (String line){
        Scanner sc = new Scanner(line);
        String[] values = sc.nextLine().split(";",6);
        if (values[5].isEmpty()){
            values[5] = null;
        }
        sc.close();
        return new City(values[1], values[2], values[3], Integer.parseInt(values[4]), values[5]);
    }

}
