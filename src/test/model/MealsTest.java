package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MealsTest {
    private Meals meals;
    private FoodItem apple;
    private FoodItem banana;

    @BeforeEach
    void setUp() {
        meals = new Meals();
        apple = new FoodItem("Apple", 95);
        banana = new FoodItem("Banana", 105);
    }

    @Test
    void testAddFoodItem() {
        meals.addFoodItem(apple);
        assertTrue(meals.getEatenMeals().contains(apple));
    }

    @Test
    void testRemoveFoodItem() {
        meals.addFoodItem(apple);
        meals.addFoodItem(banana);
        meals.removeFoodItem("Apple");
        assertFalse(meals.getEatenMeals().contains(apple));
        assertTrue(meals.getEatenMeals().contains(banana));
    }

    @Test
    void testRemoveNonExistentFoodItem() {
        meals.addFoodItem(apple);
        assertThrows(IllegalArgumentException.class, () -> {
            meals.removeFoodItem("Banana");
        });
    }

    @Test
    void testSetCalorieGoal() {
        meals.setCalorieGoal(200);
        assertEquals(200, meals.getCalorieGoal());
    }

    @Test
    void testGetEatenMeals() {
        meals.addFoodItem(apple);
        meals.addFoodItem(banana);
        List<FoodItem> eatenMeals = meals.getEatenMeals();
        assertTrue(eatenMeals.contains(apple));
        assertTrue(eatenMeals.contains(banana));
        assertEquals(2, eatenMeals.size());
    }

    @Test
    void testInitialCalorieGoal() {
        assertEquals(0, meals.getCalorieGoal());
    }

    @Test
    void testInitialEatenMealsEmpty() {
        assertTrue(meals.getEatenMeals().isEmpty());
    }
}
