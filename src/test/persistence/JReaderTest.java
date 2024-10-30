package persistence;

import model.FoodItem;
import model.Meals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class JReaderTest {
    private JReader reader;

    @BeforeEach
    void setUp() {
        // Initialize the JReader with the path to the test JSON file
        reader = new JReader("./data/testReaderValidFile.json");
    }

    @Test
    void testReadValidFile() {
        try {
            Meals testMeal = reader.read();
            assertNotNull(testMeal);

            // Test calorie goal
            assertEquals(2000, testMeal.getCalorieGoal());

            // Test food items in eatenMeals
            assertEquals(3, testMeal.getEatenMeals().size());

            // Check the first food item
            FoodItem food1 = testMeal.getEatenMeals().get(0);
            assertEquals("Banana", food1.getFoodName());
            assertEquals(105, food1.getCalories());

            // Check the second food item
            FoodItem food2 = testMeal.getEatenMeals().get(1);
            assertEquals("Apple", food2.getFoodName());
            assertEquals(95, food2.getCalories());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testReadFileNotFound() {
        JReader readerWithInvalidPath = new JReader("./data/NotFound.json");
        assertThrows(IOException.class, readerWithInvalidPath::read);
    }

    @Test
    void testReadEmptyFile() {
        JReader readerEmptyFile = new JReader("./data/testReaderEmptyFile.json");
        assertThrows(java.nio.file.NoSuchFileException.class, readerEmptyFile::read);
    }
}
