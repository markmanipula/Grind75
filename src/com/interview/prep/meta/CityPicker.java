package com.interview.prep.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * You are given a list of cities, where each city is represented as a list [cityName, population].
 * Implement a class CityPicker that allows picking a city randomly,
 * with the probability of selecting a city being proportional to its population.
 *
 *
 * Example:
 *
 * List<List<Object>> cityData = Arrays.asList(
 *     Arrays.asList("Seattle", 100),
 *     Arrays.asList("New York", 250),
 *     Arrays.asList("Los Angeles", 150)
 * );
 *
 * CityPicker obj = new CityPicker(cityData);
 * String city = obj.pickCity();
 * "New York" should be picked with probability 250/500,
 * "Seattle" with 100/500, and "Los Angeles" with 150/500.
 */

public class CityPicker {

    Random random;
    List<String> cities;
    int[] prefixSum;
    int totalPopulation;

    public CityPicker(List<List<Object>> cityData) {
        cities = new ArrayList<>();
        prefixSum = new int[cityData.size()];
        random = new Random();

        int sum = 0;
        for (int i = 0; i < cityData.size(); i++) {
            String city = (String) cityData.get(i).get(0);
            int population = (int) cityData.get(i).get(1);
            cities.add(city);
            sum += population;
            prefixSum[i] = sum;
        }
        totalPopulation = sum;
    }

    public String pickCity() {
        int target = random.nextInt(totalPopulation) + 1;
        int index = binarySearch(target);
        return cities.get(index);
    }

    private int binarySearch(int target) {
        int left = 0;
        int right = prefixSum.length - 1;

        while (left < right) {
            int middle = left + (right - left) / 2;

            if (prefixSum[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;

    }
}
