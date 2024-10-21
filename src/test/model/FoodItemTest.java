package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {

    private FoodItem foodItem;

    @BeforeEach
    void runBefore() {
        foodItem = new FoodItem("Banana", 100, 1.5);

    }

    @Test
    void testConstructor() {
        assertEquals("Banana", foodItem.getFoodName());
        assertEquals(100, foodItem.getCaloriesPerPortion());
        assertEquals(1.5, foodItem.getPortionSize());

    }

    @Test
    void testSetFoodName() {
        foodItem.setFoodName("Mango");
        assertEquals("Mango", foodItem.getFoodName());

    }

    @Test
    void testSetCaloriesPerPortion() {
        foodItem.setCaloriesPerPortion(200);
        assertEquals(200, foodItem.getCaloriesPerPortion());
    }

    @Test
    void testSetPortionSize() {
        foodItem.setPortionSize(2);
        assertEquals(2, foodItem.getPortionSize());
    }

    @Test
    void testCalculateTotalCalories() {
        assertEquals(150, foodItem.calculateTotalCalories());
    }

}
