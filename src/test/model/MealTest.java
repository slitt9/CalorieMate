package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Meal;
import model.FoodItem;

import static org.junit.jupiter.api.Assertions.*;

public class MealTest {

    private Meal breakfast;
    private Meal lunch;
    private Meal dinner;
    private Meal snack;
    private FoodItem apple;
    private FoodItem banana;

    @BeforeEach
    void runBefore() {
        breakfast = new Meal("breakfast");
        lunch = new Meal("lunch");
        dinner = new Meal("dinner");
        snack = new Meal("snack");
        apple = new FoodItem("Apple", 95, 1.0);
        banana = new FoodItem("Banana", 105, 1.0);
    }

    @Test
    void testConstructor() {
        assertEquals("breakfast", breakfast.getMealType());
        assertEquals(0.0, breakfast.getTotalCalories());
        assertTrue(breakfast.getFoodItems().isEmpty());

        assertEquals("lunch", lunch.getMealType());
        assertEquals(0.0, lunch.getTotalCalories());
        assertTrue(lunch.getFoodItems().isEmpty());
    }

    @Test
    void testAddFoodItem() {
        breakfast.addFoodItem(apple);
        assertEquals(1, breakfast.getFoodItems().size());
        assertEquals(apple, breakfast.getFoodItems().get(0));
        assertEquals(95, breakfast.getTotalCalories());

        breakfast.addFoodItem(banana);
        assertEquals(2, breakfast.getFoodItems().size());
        assertEquals(200, breakfast.getTotalCalories());
    }

    @Test
    void testRemoveFoodItem() {
        breakfast.addFoodItem(apple);
        breakfast.addFoodItem(banana);
        assertEquals(200, breakfast.getTotalCalories());

        breakfast.removeFoodItem(apple);
        assertEquals(1, breakfast.getFoodItems().size());
        assertEquals(banana, breakfast.getFoodItems().get(0));
        assertEquals(105, breakfast.getTotalCalories());
    }

    @Test
    void testValidMealType() {
        Meal breakfast = new Meal("breakfast");
        assertEquals("breakfast", breakfast.getMealType());
    }

    @Test
    void testInvalidMealType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Meal("brunch"); // Invalid meal type
        });
        assertEquals("Invalid meal type: brunch", exception.getMessage());
    }

    @Test
    void testAddValidFoodItem() {
        FoodItem apple = new FoodItem("Apple", 95, 1.0);
        breakfast.addFoodItem(apple);
        assertTrue(breakfast.getFoodItems().contains(apple));
        assertEquals(95, breakfast.calculateTotalCalories());
    }

    @Test
    void testAddNullFoodItem() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            lunch.addFoodItem(null);
        });
        assertEquals("Food item cannot be null", exception.getMessage());
    }

    @Test
    void testCalculateTotalCalories() {
        dinner.addFoodItem(apple);
        dinner.addFoodItem(banana);
        assertEquals(200, dinner.calculateTotalCalories());

        snack.addFoodItem(banana);
        assertEquals(105, snack.calculateTotalCalories());
    }

    @Test
    void testRemoveValidFoodItem() {
        FoodItem apple = new FoodItem("Apple", 95, 1.0);
        breakfast.addFoodItem(apple);
        breakfast.removeFoodItem(apple);
        assertFalse(breakfast.getFoodItems().contains(apple));
        assertEquals(0, breakfast.calculateTotalCalories());
    }

    @Test
    void testDailyLogMealType() {
        Meal dailyLog = new Meal("daily log");
        assertEquals("daily log", dailyLog.getMealType());
        assertEquals(0.0, dailyLog.getTotalCalories());
        assertTrue(dailyLog.getFoodItems().isEmpty());
    }

    // New test for removing a non-existent food item
    @Test
    void testRemoveNonExistentFoodItem() {
        FoodItem nonExistentItem = new FoodItem("Non-Existent", 100, 1.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            breakfast.removeFoodItem(nonExistentItem);
        });
        assertEquals("Food item not found in the meal", exception.getMessage());
    }

    // New test for contains method
    @Test
    void testContainsFoodItem() {
        breakfast.addFoodItem(apple);
        assertTrue(breakfast.getFoodItems().contains(apple));
        assertFalse(breakfast.getFoodItems().contains(banana));
    }
}
