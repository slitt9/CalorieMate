package model;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private List<FoodItem> foodItems; // List of food items in the meal
    private String mealType; // Type of the meal (e.g., breakfast, lunch, dinner, snack)
    private double totalCalories; // Total calories of the meal

    // REQUIRES: mealType must be a non-null string
    // EFFECTS: constructs a Meal with the specified meal type and an empty list of
    // food items
    public Meal(String mealType) {
        this.mealType = mealType;
        this.foodItems = new ArrayList<>();
        this.totalCalories = 0.0;
    }

    // REQUIRES: foodItem cannot be null
    // MODIFIES: this
    // EFFECTS: adds the specified FoodItem to the meal, updates totalCalories and
    // totalPortionSize
    public void addFoodItem(FoodItem foodItem) {
        // stub
    }

    // REQUIRES: foodItem must be in the list of food items
    // MODIFIES: this
    // EFFECTS: removes the specified FoodItem from the meal and updates
    // totalCalories and totalPortionSize
    public void removeFoodItem(FoodItem foodItem) {
        // stub
    }

    // EFFECTS: returns the total calories for the meal by summing the calories of
    // all FoodItem objects in the meal
    public double calculateTotalCalories() {
        return 0.0; // stub
    }

    public List<FoodItem> getFoodItems() {
        return null; // stub
    }

    public String getMealType() {
        return ""; // stub
    }

    public double getTotalCalories() {
        return 0.0; // stub
    }

}
