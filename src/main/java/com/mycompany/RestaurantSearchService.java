package com.mycompany;

import java.util.*;
import java.util.stream.Collectors;
import com.mycompany.restaurant.Restaurant;

public class RestaurantSearchService {

    public static List<Restaurant> searchRestaurants(ArrayList<Restaurant> restaurants, String name, Integer rating, Integer distance, Integer price, String cuisine) {
        if ((name == null || name.isEmpty()) && rating == null && distance == null && price == null && (cuisine == null || cuisine.isEmpty())) {
            System.out.println("At least one search criteria is required.");
            return Collections.emptyList();
        }

        if (!isValidRating(rating) || !isValidDistance(distance) || !isValidPrice(price)) {
            return Collections.emptyList();
        }

        List<Restaurant> searchResult = restaurants.stream()
                .filter(restaurant -> matchesName(restaurant, name)
                        && matchesRating(restaurant, rating)
                        && matchesDistance(restaurant, distance)
                        && matchesPrice(restaurant, price)
                        && matchesCuisine(restaurant, cuisine))
                .sorted(getRestaurantComparator())
                .limit(5)
                .collect(Collectors.toList());

        if (searchResult.isEmpty()) {
            System.out.println("No matches found.");
        }

        return searchResult;
    }

    private static boolean isValidRating(Integer rating) {
        if (rating == null || (rating >= 1 && rating <= 5)){
            return true;
        }
        System.out.println("Invalid user rating value.");
        return false;
    }

    private static boolean isValidDistance(Integer distance) {
        if (distance == null || (distance >= 1 && distance <= 10)){
            return true;
        }
        System.out.println("Invalid distance value.");
        return false;
    }

    private static boolean isValidPrice(Integer price) {
        if (price == null || (price >= 10 && price <= 50)){
            return true;
        }
        System.out.println("Invalid price value.");
        return false;
    }

    private static boolean matchesName(Restaurant restaurant, String name) {
        return name == null || restaurant.name().toLowerCase().contains(name.toLowerCase());
    }

    private static boolean matchesRating(Restaurant restaurant, Integer rating) {
        return rating == null || restaurant.rating() >= rating;
    }

    private static boolean matchesDistance(Restaurant restaurant, Integer distance) {
        return distance == null || restaurant.distance() <= distance;
    }

    private static boolean matchesPrice(Restaurant restaurant, Integer price) {
        return price == null || restaurant.price() <= price;
    }

    private static boolean matchesCuisine(Restaurant restaurant, String cuisine) {
        return cuisine == null || restaurant.cuisine().toLowerCase().contains(cuisine.toLowerCase());
    }

    private static Comparator<Restaurant> getRestaurantComparator() {
        return Comparator.comparingInt(Restaurant::distance)
                .thenComparingInt(restaurant -> -restaurant.rating())
                .thenComparingInt(Restaurant::price);
    }
}
