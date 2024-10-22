package persistence;

import model.FoodItem;
import model.Meal;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest extends JSONTest {
    private static final String EMPTY_MEAL_FILE = "./ProjectStarter/data/testReaderEmptyMeal.json"; 
    private static final String GENERAL_MEAL_FILE = "./ProjectStarter/data/testReaderGeneralMeal.json"; 
    private static final String USER_FILE = "./ProjectStarter/data/testReaderGeneralUser.json"; 
    private static final String NON_EXISTENT_FILE = "./ProjectStarter/data/noSuchFile.json";

    @Test
    void testReaderNonExistentFile() {
        JSONReader reader = new JSONReader(NON_EXISTENT_FILE);
        try {
            Meal meal = reader.readMeal();
            fail("IOException expected");
        } catch (IOException e) {
            
        }
    }

    @Test
    void testReaderEmptyMeal() {
        JSONReader reader = new JSONReader(EMPTY_MEAL_FILE);
        try {
            Meal meal = reader.readMeal();
            assertEquals("Breakfast", meal.getMealType());
            assertEquals(0, meal.getFoodItems().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMeal() {
        JSONReader reader = new JSONReader(GENERAL_MEAL_FILE);
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

    @Test
    void testReaderUser() {
        JSONReader reader = new JSONReader(USER_FILE);
        try {
            User user = reader.readUser();
            assertEquals("John Doe", user.getName()); 
            assertEquals(25, user.getAge()); 
            assertEquals("male", user.getSex()); 
            assertEquals(175.0, user.getHeight(), 0.01); 
            assertEquals(70.0, user.getWeight(), 0.01); 
            assertEquals(3, user.getActivityLevel()); 
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
