package ru.sber;

import ru.sber.City;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static ru.sber.Sber.*;
public class Main {

    public static void main(String[] args) {
        List<City> cities = parse();

        print(cities);
        sortListNameCityV1(cities);
        print(cities);


        sortListDistrictAndNameCityV1(cities);
        print(cities);


        searchСityMaxPopulation(cities);


        quantityOfCitiesByRegion(cities);


    }

    private static void quantityOfCitiesByRegionV1(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        cities.forEach(city -> regions.merge(city.getRegion(), 1, Integer::sum));
        regions.forEach((k, v) -> System.out.println(MessageFormat.format(" {0} = {1}", k, v)));
    }


    private static void quantityOfCitiesByRegion(List<City> cities) {
        cities.stream()
                .collect(Collectors.groupingBy(
                        City::getRegion, Collectors.counting()))
                .forEach((s, count) -> System.out.println(s + " - " + count));

    }


    private static void searchСityMaxPopulation(List<City> cities) {
        City[] arr = cities.toArray(new City[0]);
        int max = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getPopulation() > max) {
                max = arr[i].getPopulation();
                index = i;
            }
        }
        System.out.println(MessageFormat.format("[{0}] = {1}", index, max));

    }


    private static void searchСityMaxPopulationV1(List<City> cities) {
        System.out.println(cities.stream().max(Comparator.comparing(City::getPopulation)));
    }


    private static void sortListNameCityV1(List<City> cities) {
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }


    private static List<City> sortListNameCity(List<City> cities) {
        List<City> sortCitiesName = cities.stream()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
        return sortCitiesName;
    }


    private static List<City> sortListDistrictAndNameCity(List<City> cities) {
        List<City> sortCitiesDistrictAndName = cities.stream()
                .sorted(Comparator.comparing(City::getDistrict))
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
        return sortCitiesDistrictAndName;
    }


    private static void sortListDistrictAndNameCityV1(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    /*
      Вывод в консоль списка городов
     */
    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }

}