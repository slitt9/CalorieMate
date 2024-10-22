package persistence;

import model.Meal;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of Meal to file
public class JSONWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JSONWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        // stub
        return;
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Meal to file
    public void write(Meal meal) {
        // stub
        return;
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        // stub
        return;
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        // stub
        return;
    }
}
