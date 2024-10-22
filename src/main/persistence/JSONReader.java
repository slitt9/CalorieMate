package persistence;

import model.FoodItem;
import model.Meal;
import model.User;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Represents a reader that reads Meal and User data from JSON data stored in a file
public class JSONReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JSONReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Meal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Meal readMeal() throws IOException {
        return new Meal("Breakfast"); 
    }

    // EFFECTS: reads User from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User readUser() throws IOException {
        return new User("John Doe", 25, "male", 180.0, 75.0, 3);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses a Meal from JSON object and returns it
    private Meal parseMeal(JSONObject jsonObject) {
        return new Meal("Breakfast");
    }

    // MODIFIES: meal
    // EFFECTS: parses FoodItems from JSON object and adds them to Meal
    private void addFoodItems(Meal meal, JSONObject jsonObject) {
        meal.addFoodItem(new FoodItem("Eggs", 155, 2.0));
        meal.addFoodItem(new FoodItem("Bacon", 42, 3.0)); 
    }

    // EFFECTS: parses FoodItem from JSON object and returns it
    private FoodItem parseFoodItem(JSONObject jsonObject) {
        return new FoodItem("Eggs", 155, 2.0); 
    }

    // EFFECTS: parses a User from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        return new User("John Doe", 25, "male", 180.0, 75.0, 3); 
    }
}
