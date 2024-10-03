package model;

// Represents a food item, with its name, calories per portion, and size of the portion.
public class FoodItem {
    public String foodName; // name of food
    public int caloriesPerPortion; // calories per portion of food
    public double portionSize; // number of portions being consumed

    /*
     * REQUIRES: foodName has a non-zero length;
     * caloriesPerPortion is non-negative;
     * portionSize is greater than 0.
     * EFFECTS: foodName is set to the given foodName;
     * caloriesPerPortion is set to the given caloriesPerPortion;
     * portionSize is set to the given portionSize.
     */

    public FoodItem(String foodName, int caloriesPerPortion, double portionSize) {
        this.foodName = foodName;
        this.caloriesPerPortion = caloriesPerPortion;
        this.portionSize = portionSize;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;

    }

    public String getFoodName() {
        return foodName;
    }

    public void setCaloriesPerPortion(int caloriesPerPortion) {
        this.caloriesPerPortion = caloriesPerPortion;
    }

    public int getCaloriesPerPortion() {
        return caloriesPerPortion;
    }

    public void setPortionSize(double portionSize) {
        this.portionSize = portionSize;
    }

    public double getPortionSize() {
        return portionSize;
    }

    /*
     * EFFECTS: calculates and returns the total calories based on the portion size
     * and calories per portion
     */
    public int calculateTotalCalories() {
        return (int) (caloriesPerPortion * portionSize);
    }

}
