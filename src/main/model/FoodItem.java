package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a food item, with its name and calories
public class FoodItem implements Writable {
    private String foodName; // name of food
    private int calories; // calories 

    /*
     * REQUIRES: foodName has a non-zero length;
     * calories is non-negative;
     * EFFECTS: foodName is set to the given foodName;
     */

    public FoodItem(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;

    }

    public String getFoodName() {
        return foodName;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("foodName", foodName);
        json.put("calories", calories);
        return json;
    }

}
