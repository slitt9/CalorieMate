package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemTest {
    private FoodItem foodItem;

    @BeforeEach
    void setUp() {
        // Initialize a FoodItem object before each test
        foodItem = new FoodItem("Apple", 95);
    }

    @Test
    void testConstructorValidInput() {
        assertEquals("Apple", foodItem.getFoodName());
        assertEquals(95, foodItem.getCalories());
    }

    @Test
    void testSetFoodName() {
        foodItem.setFoodName("Banana");
        assertEquals("Banana", foodItem.getFoodName());
    }

    @Test
    void testGetFoodName() {
        assertEquals("Apple", foodItem.getFoodName());
    }

    @Test
    void testSetCalories() {
        foodItem.setCalories(105);
        assertEquals(105, foodItem.getCalories());
    }

    @Test
    void testGetCalories() {
        assertEquals(95, foodItem.getCalories());
    }

    @Test
    void testSetFoodNameToEmptyString() {
        // Setting food name to an empty string should not throw an exception
        foodItem.setFoodName("");
        assertEquals("", foodItem.getFoodName());
    }

    @Test
    void testSetCaloriesToNegative() {
        // Setting calories to a negative number should not throw an exception
        foodItem.setCalories(-10);
        assertEquals(-10, foodItem.getCalories());
    }
}
