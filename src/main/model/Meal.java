package model;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private List<FoodItem> foodItems; // List of food items in the meal
    private String mealType; // Type of the meal (e.g., breakfast, lunch, dinner, snack)
    private double totalCalories; // Total calories of the meal

    // REQUIRES: mealType must be one of "breakfast", "lunch", "dinner", or "snack"
    // EFFECTS: constructs a Meal with the specified meal type and an empty list of
    // food items
    public Meal(String mealType) {
        if (mealType.equalsIgnoreCase("breakfast") || mealType.equalsIgnoreCase("lunch")
                || mealType.equalsIgnoreCase("dinner") || mealType.equalsIgnoreCase("snack")
                || mealType.equalsIgnoreCase("daily log")) {
            this.mealType = mealType;
        } else {
            throw new IllegalArgumentException("Invalid meal type: " + mealType);
        }
        this.foodItems = new ArrayList<>();
        this.totalCalories = 0.0;
    }

    // REQUIRES: foodItem cannot be null
    // MODIFIES: this
    // EFFECTS: adds the specified FoodItem to the meal, updates totalCalories and
    // totalPortionSize
    public void addFoodItem(FoodItem foodItem) {
        if (foodItem == null) {
            throw new IllegalArgumentException("Food item cannot be null");
        }
        foodItems.add(foodItem);
        totalCalories += foodItem.calculateTotalCalories();
    }

    // REQUIRES: foodItem must be in the list of food items
    // MODIFIES: this
    // EFFECTS: removes the specified FoodItem from the meal and updates
    // totalCalories and totalPortionSize
    public void removeFoodItem(FoodItem foodItem) {
        if (foodItems.contains(foodItem)) {
            foodItems.remove(foodItem);
            totalCalories -= foodItem.calculateTotalCalories();
        } else {
            throw new IllegalArgumentException("Food item not found in the meal");
        }
    }

    // EFFECTS: returns the total calories for the meal by summing the calories of
    // all FoodItem objects in the meal
    public double calculateTotalCalories() {
        return totalCalories;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public String getMealType() {
        return mealType;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

}
