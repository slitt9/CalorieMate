package persistence;

import model.FoodItem;
import model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONTest {

    protected void checkFoodItem(String name, int caloriesPerPortion, double portionSize, FoodItem foodItem) {
        assertEquals(name, foodItem.getFoodName());
        assertEquals(caloriesPerPortion, foodItem.getCaloriesPerPortion());
        assertEquals(portionSize, foodItem.getPortionSize(), 0.01); 
    }

    protected void checkUser(String name, int age, String sex, double height, double weight, int activityLevel,
            User user) {
        assertEquals(name, user.getName());
        assertEquals(age, user.getAge());
        assertEquals(sex, user.getSex());
        assertEquals(height, user.getHeight(), 0.01); 
        assertEquals(weight, user.getWeight(), 0.01); 
        assertEquals(activityLevel, user.getActivityLevel());
    }
}
