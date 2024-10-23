package model;

// Represents a food item, with its name, calories per portion, and size of the portion.
public class FoodItem {
    public String foodName; // name of food
    public int calories; // calories per portion of food

    /*
     * REQUIRES: foodName has a non-zero length;
     * caloriesPerPortion is non-negative;
     * portionSize is greater than 0.
     * EFFECTS: foodName is set to the given foodName;
     * caloriesPerPortion is set to the given caloriesPerPortion;
     * portionSize is set to the given portionSize.
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

}
