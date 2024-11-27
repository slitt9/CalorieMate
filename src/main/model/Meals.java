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
    // EFFECTS: adds the specified FoodItem to the meals list and logs the event
    public void addFoodItem(FoodItem foodItem) {
        eatenMeals.add(foodItem);
        EventLog.getInstance().logEvent(new Event("Added food item: " + foodItem.getFoodName() 
                + " (" + foodItem.getCalories() + " calories)"));
    }

    // REQUIRES: foodItem must be in the list of food items
    // MODIFIES: this
    // EFFECTS: removes the specified FoodItem from the meal, updates eatenMeals, and logs the event
    public void removeFoodItem(String foodName) {
        boolean removed = false;
        for (FoodItem item : eatenMeals) {
            if (item.getFoodName().equals(foodName)) {
                eatenMeals.remove(item);
                EventLog.getInstance().logEvent(new Event("Removed food item: " + foodName));
                removed = true;
                break; // Exit the loop once the item is found and removed
            }
        }
        if (!removed) {
            throw new IllegalArgumentException("Food item not found in the meal");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the calorie goal and logs the event
    public void setCalorieGoal(int calorieGoal) {
        this.calorieGoal = calorieGoal;
        EventLog.getInstance().logEvent(new Event("Set calorie goal to: " + calorieGoal));
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

    // MODIFIES: this
    // EFFECTS: sorts the eaten meals list by calories in descending order and logs the event
    public void sortByCaloriesDescending() {
        eatenMeals.sort((f1, f2) -> Integer.compare(f2.getCalories(), f1.getCalories()));
        EventLog.getInstance().logEvent(new Event("Sorted meals by calories in descending order"));
    }

    // EFFECTS: Calculates total calories consumed
    public int calculateTotalCalories() {
        return eatenMeals.stream()
                .mapToInt(FoodItem::getCalories)
                .sum();
    }

    // EFFECTS: Calculates remaining calories based on goal
    public int calculateRemainingCalories() {
        return calorieGoal - calculateTotalCalories();
    }
}
