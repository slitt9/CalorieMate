package persistence;

import model.FoodItem;
import model.Meal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest extends JSONTest {
    private static final String TEST_FILE = "./data/testMeal.json";

    @Test
    void testReaderNonExistentFile() {
        JSONReader reader = new JSONReader("./data/noSuchFile.json");
        try {
            Meal meal = reader.readMeal();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMeal() {
        JSONReader reader = new JSONReader(TEST_FILE);
        try {
            Meal meal = reader.readMeal();
            assertEquals("daily log", meal.getMealType()); // Adjust based on your implementation
            assertEquals(0, meal.getFoodItems().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMeal() {
        JSONReader reader = new JSONReader(TEST_FILE);
        try {
            Meal meal = reader.readMeal();
            assertEquals("Breakfast", meal.getMealType());
            List<FoodItem> foodItems = meal.getFoodItems();
            assertEquals(2, foodItems.size());
            checkFoodItem("Eggs", 155, 2.0, foodItems.get(0));
            checkFoodItem("Bacon", 42, 3.0, foodItems.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
