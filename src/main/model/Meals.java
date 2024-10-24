package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

public class Meals implements Writable {
    private List<FoodItem> eatenMeals; // List of food items eaten today
    private int calorieGoal; // Calorie goal of the day

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
    // EFFECTS: removes the specified FoodItem from the meal and updates eatenMeals
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

    public void setCalorieGoal(int calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public List<FoodItem> getEatenMeals() {
        return eatenMeals;
    }

    public int getCalorieGoal() {
        return calorieGoal;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("calorieGoal", calorieGoal);
        json.put("eatenMeals", foodItemToJson());
        return json;
    }

    // EFFECTS: returns things in this eatenMeals list as a JSON array
    private JSONArray foodItemToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FoodItem f : eatenMeals) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }

}
