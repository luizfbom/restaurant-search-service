package com.mycompany.restaurant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantData {

    public static ArrayList<Restaurant> getRestaurantList() {
        File projectDirectory = new File(System.getProperty("user.dir"));
        File restaurantCsvFile = new File(projectDirectory,"/src/main/resources/csv/restaurants.csv");
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        HashMap<Integer, String> cuisineMap = CuisineData.getCuisineList();

        try (BufferedReader br = new BufferedReader(new FileReader(restaurantCsvFile))) {

            br.readLine(); // Skip the header.
            String line;

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                String name = values[0].trim();
                int rating = Integer.parseInt(values[1].trim());
                int distance = Integer.parseInt(values[2].trim());
                int price = Integer.parseInt(values[3].trim());
                int cuisineId = Integer.parseInt(values[4].trim());
                // Replace cuisine Id with name.
                String cuisine = cuisineMap.get(cuisineId);

                restaurants.add(new Restaurant(name, rating, distance, price, cuisine));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return restaurants;
    }
}
