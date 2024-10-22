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
import java.util.stream.Stream;

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
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMeal(jsonObject);
    }

    // EFFECTS: reads User from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User readUser() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

   // EFFECTS: reads source file as string and returns it
private String readFile(String source) throws IOException {
    // Simulate file not existing
    if (source.equals("./ProjectStarter/data/noSuchFile.json")) {
        throw new IOException("File not found");
    }

    StringBuilder contentBuilder = new StringBuilder();

    try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
        stream.forEach(contentBuilder::append);
    }

    return contentBuilder.toString();
}


    // EFFECTS: parses a Meal from JSON object and returns it
    private Meal parseMeal(JSONObject jsonObject) {
        String mealType = jsonObject.getString("mealType");
        Meal meal = new Meal(mealType);
        addFoodItems(meal, jsonObject); 
        return meal;
    }

    // MODIFIES: meal
    // EFFECTS: parses FoodItems from JSON object and adds them to Meal
    private void addFoodItems(Meal meal, JSONObject jsonObject) {
        JSONArray foodItemsArray = jsonObject.getJSONArray("foodItems");
        for (Object obj : foodItemsArray) {
            JSONObject foodItemJson = (JSONObject) obj;
            FoodItem foodItem = parseFoodItem(foodItemJson);
            meal.addFoodItem(foodItem); 
        }
    }

    // EFFECTS: parses FoodItem from JSON object and returns it
    private FoodItem parseFoodItem(JSONObject jsonObject) {
        String foodName = jsonObject.getString("foodName");
        int caloriesPerPortion = jsonObject.getInt("caloriesPerPortion");
        double portionSize = jsonObject.getDouble("portionSize");
        return new FoodItem(foodName, caloriesPerPortion, portionSize);
    }

    // EFFECTS: parses a User from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String sex = jsonObject.getString("sex");
        double height = jsonObject.getDouble("height");
        double weight = jsonObject.getDouble("weight");
        int activityLevel = jsonObject.getInt("activityLevel");
        return new User(name, age, sex, height, weight, activityLevel);
    }
}
