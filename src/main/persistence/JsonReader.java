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
public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Meals from file and returns it;
    // throws IOExcetion if an error occurs reading data from file
    public Meals read() throws IOException {
        return null;
    }

     // EFFECTS: reads source file as string and returns it
     private String readFile(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses Meals from JSON object and returns it
    private Meals parseMeals(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: Meals
    // EFFECTS: parses foodItem from JSON object and adds them to Meals
    private void addFoodItems(Meals m, JSONObject jsonObject) {

    }

 

}