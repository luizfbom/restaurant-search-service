package com.mycompany;

import com.mycompany.restaurant.Restaurant;
import com.mycompany.restaurant.RestaurantData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mycompany.RestaurantSearchService.searchRestaurants;

public class Main {
    public static void main(String[] args) {

        ArrayList<Restaurant> restaurantList = RestaurantData.getRestaurantList();

        Scanner terminalInput = new Scanner(System.in);
        boolean continueInteractiveSearching = true;

        while (continueInteractiveSearching) {
            System.out.print("Enter Restaurant Name (or press Enter to skip): ");
            String name = terminalInput.nextLine().trim();

            Integer rating = null;
            System.out.print("Enter Customer Rating (1-5, or press Enter to skip): ");
            String ratingInput = terminalInput.nextLine().trim();
            if (!ratingInput.isEmpty()) {
                rating = Integer.parseInt(ratingInput);
            }

            Integer distance = null;
            System.out.print("Enter Distance (1-10, or press Enter to skip): ");
            String distanceInput = terminalInput.nextLine().trim();
            if (!distanceInput.isEmpty()) {
                distance = Integer.parseInt(distanceInput);
            }

            Integer price = null;
            System.out.print("Enter Price (10-50, or press Enter to skip): ");
            String priceInput = terminalInput.nextLine().trim();
            if (!priceInput.isEmpty()) {
                price = Integer.parseInt(priceInput);
            }

            System.out.print("Enter Cuisine (or press Enter to skip): ");
            String cuisine = terminalInput.nextLine().trim();

            System.out.println("\nSearching for restaurants...");

            List<Restaurant> results = searchRestaurants( restaurantList, name, rating, distance, price, cuisine);

            if (!results.isEmpty()) {
                System.out.println("\nName, Rating, Distance, Price, Cuisine");
            }
            results.forEach(result -> System.out.println(result.name() +", "+
                    result.rating() +", "+
                    result.distance() +", "+
                    result.price() +", "+
                    result.cuisine()));

            System.out.print("\nDo you want to perform another search? (y/n): ");
            String userInput = terminalInput.nextLine().trim().toLowerCase();
            continueInteractiveSearching = userInput.equals("y");
        }

        terminalInput.close();
    }
}