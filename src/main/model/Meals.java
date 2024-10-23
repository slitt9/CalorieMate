package model;

import java.util.ArrayList;
import java.util.List;
import model.FoodItem;

public class Meals {
    private List<FoodItem> eatenMeals; // List of food items eaten today
    private double calorieGoal; // Calorie goal of the day

    // EFFECTS: constructs a Meal with an empty list of
    // food items and calorieGoal
    public Meals() {
        this.eatenMeals = new ArrayList<>();
        calorieGoal = 0;
    }

    // REQUIRES: foodItem cannot be null
    // MODIFIES: this
    // EFFECTS: adds the specified FoodItem to the meals list
    public void addFoodItem(FoodItem foodItem) {
        eatenMeals.add(foodItem);
    }

    // REQUIRES: foodItem must be in the list of food items
    // MODIFIES: this
    // EFFECTS: removes the specified FoodItem from the meal and updates
    public void removeFoodItem(String foodName) {
        boolean removed = false;
        for (FoodItem item : eatenMeals) {
            if (item.getFoodName().equals(foodName)) {
                eatenMeals.remove(item);
                removed = true;
                break; // Exit the loop once the item is found and removed
            }
        }
        if (!removed) {
            throw new IllegalArgumentException("Food item not found in the meal");
        }
    }

    public void setCalorieGoal(double calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public List<FoodItem> getEatenMeals() {
        return eatenMeals;
    }

    public double getCalorieGoal() {
        return calorieGoal;
    }

}
