package persistence;

import model.FoodItem;
import model.Meals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Meals from JSON data stored in file
public class JReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Meals from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Meals read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMeals(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Meals from JSON object and returns it
    private Meals parseMeals(JSONObject jsonObject) {
        Meals testMeal = new Meals();
        addCalorieGoal(testMeal, jsonObject);
        addFoodItems(testMeal, jsonObject);
        return testMeal;

    }

    // MODIFIES: Meals
    // EFFECTS: parses calorie goal from JSON object and sets it in Meals
    private void addCalorieGoal(Meals meal, JSONObject jsonObject) {
        int calorieGoal = jsonObject.getInt("calorieGoal");
        meal.setCalorieGoal(calorieGoal);

    }

    // MODIFIES: Meals
    // EFFECTS: parses foodItem from JSON object and adds them to Meals
    private void addFoodItems(Meals m, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("eatenMeals");
        for (Object item : jsonArray) {
            JSONObject foodObject = (JSONObject) item;
            addFood(m, foodObject);
        }
    }

    // MODIFIES: Meals
    // EFFECTS: parses a food item from JSON object and adds it to meals
    private void addFood(Meals m, JSONObject jsonObject) {
        String name = jsonObject.getString("foodName");
        int calories = jsonObject.getInt("calories");
        FoodItem food = new FoodItem(name, calories);
        m.addFoodItem(food);
    }

}
