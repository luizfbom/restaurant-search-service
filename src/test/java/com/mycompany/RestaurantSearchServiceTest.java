package com.mycompany;

import com.mycompany.restaurant.Restaurant;
import com.mycompany.restaurant.RestaurantData;
import org.junit.Test;

import java.util.*;

import static com.mycompany.RestaurantSearchService.searchRestaurants;
import static org.junit.Assert.*;

public class RestaurantSearchServiceTest {

    ArrayList<Restaurant> restaurantList = RestaurantData.getRestaurantList();

    @Test
    public void assertNullInput() {
        List<Restaurant> results = searchRestaurants(restaurantList, null, null, null, null, null);
        assertTrue(results.isEmpty());
    }

    @Test
    public void assertEmptyOutput() {
        List<Restaurant> results = searchRestaurants(restaurantList, "blablabla", null, null, null, "hai");
        assertTrue(results.isEmpty());
    }

    @Test
    public void assertInvalidRatingInput() {
        List<Restaurant> results = searchRestaurants(restaurantList, "deli", 6, 2, 20, "hai");
        assertTrue(results.isEmpty());
    }

    @Test
    public void assertInvalidDistanceInput() {
        List<Restaurant> results = searchRestaurants(restaurantList, "deli", 3, 11, 20, "hai");
        assertTrue(results.isEmpty());
    }

    @Test
    public void assertInvalidPriceInput() {
        List<Restaurant> results = searchRestaurants(restaurantList, "deli", 3, 8, 70, "hai");
        assertTrue(results.isEmpty());
    }

    @Test
    public void assertAscendentDistanceOutput() {
        List<Restaurant> results = searchRestaurants( restaurantList, "deli", null, null, null, null);
        assertNotNull(results);
        assertTrue(isSortedAscendentByDistance(results));
    }

    private boolean isSortedAscendentByDistance(List<Restaurant> restaurants) {
        for (int i = 0; i < restaurants.size() - 1; i++) {
            if (restaurants.get(i).distance() > restaurants.get(i + 1).distance()) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void assertDescendentRatingOutput() {
        List<Restaurant> results = searchRestaurants( restaurantList, "deli", null, null, null, null);
        assertNotNull(results);
        assertTrue(isSortedDescendentByRating(results));
    }

    private boolean isSortedDescendentByRating(List<Restaurant> restaurants) {
        for (int i = 0; i < restaurants.size() - 1; i++) {
            if ((restaurants.get(i).rating() < restaurants.get(i + 1).rating()) &&
                    (restaurants.get(i).distance() == restaurants.get(i + 1).distance())) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void assertAscendentPriceOutput() {
        List<Restaurant> results = searchRestaurants( restaurantList, "deli", null, null, null, null);
        assertNotNull(results);
        assertTrue(isSortedAscendentByPrice(results));
    }

    private boolean isSortedAscendentByPrice(List<Restaurant> restaurants) {
        for (int i = 0; i < restaurants.size() - 1; i++) {
            if ((restaurants.get(i).price() > restaurants.get(i + 1).price()) &&
                    (restaurants.get(i).distance() == restaurants.get(i + 1).distance())) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void assertMaxFiveOutput() {
        List<Restaurant> results = searchRestaurants( restaurantList, "a", 1, null, null, null);
        assertNotNull(results);
        assertEquals(5,results.size());
    }

    @Test
    public void assertOnlyRatingInput() {
        List<Restaurant> results = searchRestaurants( restaurantList, null, 5, null, null, null);
        assertNotNull(results);
    }

    @Test
    public void assertOnlyDistanceInput() {
        List<Restaurant> results = searchRestaurants(restaurantList, null, null, 2, null, null);
        assertNotNull(results);
    }

    @Test
    public void assertOnlyPriceInput() {
        List<Restaurant> results = searchRestaurants(restaurantList, null, null, null, 10, null);
        assertNotNull(results);
    }

    @Test
    public void assertOnlyCuisineAndParcialInput() {
        List<Restaurant> results = searchRestaurants(restaurantList, null, null, null, null, "hai");
        assertNotNull(results);
    }

    @Test
    public void assertLessThanFiveOutput() {
        List<Restaurant> results = searchRestaurants(restaurantList, "table", 1, 5, 40, "hai");
        assertEquals(1,results.size());

    }
}