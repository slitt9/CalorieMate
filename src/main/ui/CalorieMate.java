package ui;

import model.Meals;
import model.FoodItem;
import persistence.JReader;
import persistence.JWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CalorieMate {
    private static final String JSON_STORE = "./project-j4e0x/data/meals.json";
    private Meals meals;
    private Scanner scanner;
    private JReader jsonReader;
    private JWriter jsonWriter;

    // Constructor to initialize daily log
    public CalorieMate() {
        meals = new Meals();
        this.scanner = new Scanner(System.in);
        jsonReader = new JReader(JSON_STORE); 
        jsonWriter = new JWriter(JSON_STORE); 
        run();
    }
    

    // Run the console-based application
// Run the console-based application
public void run() {
    boolean keepRunning = true;
    while (keepRunning) {
        printMenu();
        String command = scanner.nextLine();
        keepRunning = handleUserChoice(command);
    }
    System.out.println("Exiting program...");
    System.exit(0);  
}



    // Prints the available actions to the user
    private void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1 - Add a food item to todays list");
        System.out.println("2 - Set a calorie goal");
        System.out.println("3 - View calorie goal and todays list");
        System.out.println("4 - Remove a food item");
        System.out.println("5 - Save your data");
        System.out.println("6 - Load your saved data");
        System.out.println("7 - Exit");
        System.out.println("Enter your choice:");
    }

    // MODIFIES: this
    // EFFECTS: Calls on the method which corresponds with the user's choice
    private boolean handleUserChoice(String command) {
        switch (command) {
            case "1":
                addFoodItem(); // Add a food item
                break;
            case "2":
                setCalorieGoal(); // Set the calorie goal
                break;
            case "3":
                viewFoodAndGoal(); // View logged foods and calorie goal
                break;
            case "4":
                removeFood(); // Remove a food item
                break; // Add this break
            case "5":
                saveData(); // Save the user's data
                break; // Add this break
            case "6":
                loadData(); // Load the user's data
                break; // Add this break
            case "7":
                return false;
        }
        return true;
    }
    

    // REQUIRES: Name must have a non-zero length
    // Calories must be >= 0
    // MODIFIES: modifies the eatenMeals list in Meals
    // EFFECTS: adds a given food to the eatenMeals list
    public void addFoodItem() {
        System.out.println("Enter food item name: ");
        String foodName = scanner.nextLine();

        System.out.println("Enter calories for the item: ");
        String caloriesInput = scanner.nextLine(); 
        double calories = Double.parseDouble(caloriesInput); 

        // Create FoodItem
        FoodItem foodItem = new FoodItem(foodName, (int) calories);
        meals.addFoodItem(foodItem);
        System.out.println(foodName + " has been added to your list");
    }

    // REQUIRES: Calorie goal must be >0
    // MODIFIES: modifies the eatenMeals list in Meals
    // EFFECTS: adds a given food to the eatenMeals list
    private void setCalorieGoal() {
        System.out.println("Enter your calorie goal for the day: ");
        int calorieGoal = Integer.parseInt(scanner.nextLine());
        meals.setCalorieGoal(calorieGoal);
        System.out.println("Your calorie goal has been set to: " + calorieGoal);
    }

    // EFFECTS: prints out the list of foods added to the eatenMeals list and
    // calorie goal
    private void viewFoodAndGoal() {
        System.out.println("\nFood you've eaten today:");
        if (meals.getEatenMeals().isEmpty()) {
            System.out.println("You haven't added any foods yet.");
        } else {
            for (FoodItem foodItem : meals.getEatenMeals()) {
                System.out.println("- " + foodItem.getFoodName() + " : " + foodItem.getCalories()
                        + " Calories");
            }
        }

        System.out.println("Calorie goal: " + meals.getCalorieGoal());
    }

    // REQUIRES: String name must be the name of a food in the list
    // MODIFIES: This
    // EFFECTS: Removes a given food from the eatenMeals list in meals
    private void removeFood() {
        System.out.println("Enter the name of the food you want to remove: ");
        String name = scanner.nextLine();
        meals.removeFoodItem(name);
        System.out.println(name + " has been removed from your list");
    }

    // EFFECTS: saves the data to file
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(meals);
            jsonWriter.close();
            System.out.println("Your data has been saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads data from file
    private void loadData() {
        try {
            meals = jsonReader.read();
            System.out.println("Your data has been loaded!");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
