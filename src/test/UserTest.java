import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void runBefore() {
        user = new User("John", 25, "male", 180.0, 75.0, 2);
    }

    @Test
    void testConstructor() {
        assertEquals("John", user.getName());
        assertEquals(25, user.getAge());
        assertEquals("male", user.getSex());
        assertEquals(175.0, user.getHeight());
        assertEquals(70.0, user.getWeight());
        assertEquals(2, user.getActivityLevel());
    }

    @Test
    void testSetName() {
        user.setName("Jane");
        assertEquals("Jane", user.getName());
    }

    @Test
    void testSetAge() {
        user.setAge(30);
        assertEquals(30, user.getAge());
    }

    @Test
    void testSetSex() {
        user.setSex("female");
        assertEquals("female", user.getSex());
    }

    @Test
    void testSetHeight() {
        user.setHeight(165.0);
        assertEquals(165.0, user.getHeight());
    }

    @Test
    void testSetWeight() {
        user.setWeight(70.0);
        assertEquals(70.0, user.getWeight());
    }

    @Test
    void testSetActivityLevel() {
        user.setActivityLevel(3);
        assertEquals(3, user.getActivityLevel());
    }

    @Test
    void testCalculateBMR() {
        assertEquals(1673, user.calculateBMR());
    }

    @Test
    void testCalculateMaintenanceCalories() {
        assertEquals(2538, user.calculateMaintenanceCalories()); 
    }
}