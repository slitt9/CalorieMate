package persistence;

import model.FoodItem;
import model.Meal;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Represents a reader that reads Meal, FoodItem, and User data from JSON data stored in a file
public class JSONReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JSONReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Meal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Meal readMeal() throws IOException {
        return new Meal("stubMealType"); // stub
    }

    // EFFECTS: reads FoodItems from file and returns them as a list;
    // throws IOException if an error occurs reading data from file
    public List<FoodItem> readFoodItems() throws IOException {
        return new ArrayList<>(); // stub
    }

    // EFFECTS: reads User from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User readUser() throws IOException {
        return new User("stubName", 0, "male", 0.0, 0.0, 0); // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return new String(Files.readAllBytes(Paths.get(source)), StandardCharsets.UTF_8); // stub
    }

    // EFFECTS: parses a Meal from JSON object and returns it
    private Meal parseMeal(JSONObject jsonObject) {
        return new Meal("stubMealType"); // stub
    }

    // EFFECTS: parses FoodItems from JSON object and returns them as a list
    private List<FoodItem> parseFoodItems(JSONObject jsonObject) {
        return new ArrayList<>(); // stub
    }

    // EFFECTS: parses a User from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        return new User("stubName", 0, "male", 0.0, 0.0, 0); // stub
    }

    // MODIFIES: meal
    // EFFECTS: parses FoodItems from JSON object and adds them to Meal
    private void addFoodItems(Meal meal, JSONObject jsonObject) {
        // stub
    }

    // EFFECTS: parses FoodItem from JSON object and returns it
    private FoodItem parseFoodItem(JSONObject jsonObject) {
        return new FoodItem("stubFoodName", 0, 0.0); // stub
    }
}
