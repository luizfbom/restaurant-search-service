# Restaurant Search Application

A simple Java application to search for local restaurants based on specified criteria.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Running Tests](#running-tests)

## Introduction

This project provides a basic search function for local restaurants. Users can search for restaurants based on criteria such as name, customer rating, distance, price, and cuisine. The application reads restaurant and cuisine data from CSV files, allowing users to find the best matches according to specified parameters.

## Description:
- Need a basic search function that allows your colleagues to search those restaurants, based on five criteria:
- Restaurant Name,
- Customer Rating(1 star ~ 5 stars),
- Distance(1 mile ~ 10 miles),
- Price(how much one person will spend on average, $10 ~ $50),
- Cuisine(Chinese, American, Thai, etc.).
- In the restaurants.csv file there is 5 fields: Name, Rating, Distance, Price, Cuisine_Id.
- In the cuisines.csv file there is 2 fields: Cuisine_Id, Cuisine_Type.

## Input:
- The function should allow users to provide up to five parameters based on the criteria listed above.
- Created a simple CLI just to make tests easier. Prompt with input examples:
  ```bash
  Enter Restaurant Name (or press Enter to skip): deli
  Enter Customer Rating (1-5, or press Enter to skip): 2
  Enter Distance (1-10, or press Enter to skip): 2
  Enter Price (10-50, or press Enter to skip): 30
  Enter Cuisine (or press Enter to skip): 
  ```

## Output:
- Up to 5 matches based on the provided criteria.
- The results should at least contain the restaurant name (decided to return all parameters).
- CLI output example:
  ```bash
  Name, Rating, Distance, Price, Cuisine
  Deliciousgenix, 4, 1, 10, Spanish
  Deliciouszilla, 4, 1, 15, Chinese
  Bang Delicious, 5, 2, 15, Russian
  Deliciouszoid, 3, 2, 30, Italian
  Deliciousquipo, 2, 2, 10, Other
  ```

### Requirements:
- Each parameter can contain only one value.
- If parameter values are invalid, return an error message.
- If no matches are found, return an empty list.
- If less than 5 matches are found, return them all.
- If more than 5 matches are found, return the best 5 matches.
- The returned results should be sorted according to the rules explained below:
  - Restaurant Name: exact or partial String match with what users provided.
  - Customer Rating: equal to or more than what users have asked for.
  - Distance: equal to or less than what users have asked for.
  - Price: equal to or less than what users have asked for.
  - Cuisine: exact or partial String match with what users provided.
  - The five parameters are holding an “AND” relationship.
  - Sort them as described below:
    - Default sort by Distance first (shorter first).
    - If 2 matches still equal, higher customer Rating.
    - If 2 matches still equal, lower Price.
    - If 2 matches still equal, randomly decide.

## Getting Started

### Prerequisites

- Java 17 or later
- Maven

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/luizfbom/restaurant-search-service.git
   ```

3. Build the project with Maven:

   ```bash
   mvn clean install
   ```
   
## Usage

- Ensure the CSV files (restaurants.csv and cuisines.csv) are populated with restaurant and cuisine data inside /src/main/resources/csv.
- Modify the Main class to specify the correct paths to your CSV files.
- Run the application:

   ```bash
   java -jar target/bestMatchedRestaurants-1.0.jar
   ```
  
- Follow the instruction to input the search criteria parameters.

## Running Tests

Execute the following command to run unit tests:

   ```bash
   mvn test
   ```