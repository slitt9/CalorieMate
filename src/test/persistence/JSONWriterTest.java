package persistence;

import model.FoodItem;
import model.Meal;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONWriterTest extends JSONTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Meal meal = new Meal("lunch");
            JSONWriter writer = new JSONWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMeal() {
        try {
            Meal meal = new Meal("breakfast");
            JSONWriter writer = new JSONWriter("./data/testWriterEmptyMeal.json");
            writer.open();
            writer.write(meal);
            writer.close();

            JSONReader reader = new JSONReader("./data/testWriterEmptyMeal.json");
            meal = reader.readMeal();
            assertEquals("breakfast", meal.getMealType());
            assertEquals(0, meal.getFoodItems().size());
            assertEquals(0, meal.calculateTotalCalories());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMeal() {
        try {
            Meal meal = new Meal("dinner");
            meal.addFoodItem(new FoodItem("steak", 700, 1));
            meal.addFoodItem(new FoodItem("salad", 150, 1));
            JSONWriter writer = new JSONWriter("./data/testWriterGeneralMeal.json");
            writer.open();
            writer.write(meal);
            writer.close();

            JSONReader reader = new JSONReader("./data/testWriterGeneralMeal.json");
            meal = reader.readMeal();
            assertEquals("dinner", meal.getMealType());
            List<FoodItem> foodItems = meal.getFoodItems();
            assertEquals(2, foodItems.size());
            checkFoodItem("steak", 700, 1, foodItems.get(0));
            checkFoodItem("salad", 150, 1, foodItems.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
