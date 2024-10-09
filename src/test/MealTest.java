import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Meal;
import model.FoodItem;

import static org.junit.jupiter.api.Assertions.*;

public class MealTest {

    private Meal breakfast;
    private Meal lunch;
    private FoodItem apple;
    private FoodItem banana;

    @BeforeEach
    void runBefore() {
        breakfast = new Meal("breakfast");
        lunch = new Meal("lunch");
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
    void testCalculateTotalCalories() {
        breakfast.addFoodItem(apple);
        breakfast.addFoodItem(banana);
        assertEquals(200, breakfast.calculateTotalCalories());

        lunch.addFoodItem(banana);
        assertEquals(105, lunch.calculateTotalCalories());
    }
}