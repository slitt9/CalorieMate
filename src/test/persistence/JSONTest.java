package persistence;

import model.FoodItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONTest {
    protected void checkFoodItem(String foodName, int caloriesPerPortion, double portionSize, FoodItem foodItem) {
        assertEquals(foodName, foodItem.getFoodName());
        assertEquals(caloriesPerPortion, foodItem.getCaloriesPerPortion());
        assertEquals(portionSize, foodItem.getPortionSize());
    }
}
