package persistence;

import model.FoodItem;
import model.Meals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class JWriterTest {
    private static final String TEST_FILE = "./project-j4e0x/data/testWriter.json";
    private JWriter writer;
    private Meals meal;

    @BeforeEach
    void setup() {
        meal = new Meals();
        writer = new JWriter(TEST_FILE);
    }

    @Test
    void testWriteEmptyMeals() {
        try{
            writer.open();
            writer.write(meal);
            writer.close();

            // Read back the file to ensure content is correctly written
            JReader reader = new JReader(TEST_FILE);
            Meals fullMeal = reader.read();
            assertEquals(0, fullMeal.getCalorieGoal());
            assertTrue(fullMeal.getEatenMeals().isEmpty());
        } catch(IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterMealsWithContent() {
        try{
            // Setup Meals with content
            meal.setCalorieGoal(1000);
            meal.addFoodItem(new FoodItem("Apple", 50));
            meal.addFoodItem(new FoodItem("Banana", 100));
            writer.open();
            writer.write(meal);
            writer.close();
            JReader reader = new JReader(TEST_FILE);
            Meals fullMeal = reader.read();
            assertEquals(1000, fullMeal.getCalorieGoal());
            assertEquals(2, fullMeal.getEatenMeals().size());

            FoodItem food1 = fullMeal.getEatenMeals().get(0);
            assertEquals("Apple", food1.getFoodName());
            assertEquals(50, food1.getCalories());
            FoodItem food2 = fullMeal.getEatenMeals().get(1);
            assertEquals("Banana", food2.getFoodName());
            assertEquals(100, food2.getCalories());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
