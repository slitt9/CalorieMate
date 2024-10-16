package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User femaleUser0;
    private User maleUser1;
    private User maleUser2;
    private User maleUser3;
    private User maleUser4;
    private User femaleUser5;

    @BeforeEach
    void runBefore() {
        femaleUser0 = new User("Jill", 19, "female", 150.0, 55.0, 0);
        maleUser1 = new User("Bill", 30, "male", 160.0, 80.0, 1);
        maleUser2 = new User("John", 25, "male", 175.0, 70.0, 2);
        maleUser3 = new User("Steven", 45, "male", 182.0, 85.0, 3);
        maleUser4 = new User("John", 21, "male", 170.0, 75.0, 4);
        femaleUser5 = new User("Stacy", 33, "female", 150.0, 65.0, 5);
    }

    @Test
    void testConstructor() {
        assertEquals("John", maleUser2.getName());
        assertEquals(25, maleUser2.getAge());
        assertEquals("male", maleUser2.getSex());
        assertEquals(175.0, maleUser2.getHeight());
        assertEquals(70.0, maleUser2.getWeight());
        assertEquals(2, maleUser2.getActivityLevel());
    }

    @Test
    void testSetName() {
        maleUser2.setName("Jane");
        assertEquals("Jane", maleUser2.getName());
    }

    @Test
    void testSetAge() {
        maleUser2.setAge(30);
        assertEquals(30, maleUser2.getAge());
    }

    @Test
    void testSetSex() {
        maleUser2.setSex("female");
        assertEquals("female", maleUser2.getSex());
    }

    @Test
    void testSetHeight() {
        maleUser2.setHeight(165.0);
        assertEquals(165.0, maleUser2.getHeight());
    }

    @Test
    void testSetWeight() {
        maleUser2.setWeight(65.0);
        assertEquals(65.0, maleUser2.getWeight());
    }

    @Test
    void testSetActivityLevel() {
        maleUser2.setActivityLevel(3);
        assertEquals(3, maleUser2.getActivityLevel());
    }

    @Test
    void testCalculateBMR() {
        assertEquals(1231, femaleUser0.calculateBMR());
        assertEquals(1673, maleUser2.calculateBMR());
    }

    @Test
    void testCalculateMaintenanceCalories() {
        assertEquals(1354, femaleUser0.calculateMaintenanceCalories());
        assertEquals(2110, maleUser1.calculateMaintenanceCalories());
        assertEquals(2258, maleUser2.calculateMaintenanceCalories());
        assertEquals(2473, maleUser3.calculateMaintenanceCalories());
        assertEquals(2739, maleUser4.calculateMaintenanceCalories());
        assertEquals(2269, femaleUser5.calculateMaintenanceCalories());
    }

    @Test
    void testInvalidActivityLevel() {
        User maleUser6 = new User("Jacob", 33, "male", 165.0, 99.0, 6); // activity level 6
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            maleUser6.calculateMaintenanceCalories();
        });

        String expectedMessage = "Invalid activity level: 6";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}