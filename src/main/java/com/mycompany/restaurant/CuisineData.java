package com.mycompany.restaurant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CuisineData {

    public static HashMap<Integer, String> getCuisineList() {
        File projectDirectory = new File(System.getProperty("user.dir"));
        File cuisineCsvFile = new File(projectDirectory,"/src/main/resources/csv/cuisines.csv");
        HashMap<Integer, String> cuisines  = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cuisineCsvFile))) {
            br.readLine(); // Skip the header.
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0].trim());
                String name = values[1].trim();
                cuisines.put(id, name);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }

        return cuisines;
    }
}