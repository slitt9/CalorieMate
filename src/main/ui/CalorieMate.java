package ui;

import model.Meals;
import model.FoodItem;

import java.util.Scanner;

public class CalorieMate {
    private Meals meals;
    private Scanner scanner;

    // Constructor to initialize daily log
    public CalorieMate() {
        meals = new Meals();
        this.scanner = new Scanner(System.in);
        run();
    }

    // Run the console-based application
    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {
            printMenu();
            String command = scanner.nextLine();
            keepRunning = handleUserChoice(command);
        }
    }

    // Prints the available actions to the user
    private void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1 - Add a food item to todays list");
        System.out.println("2 - Set a calorie goal");
        System.out.println("3 - View calorie goal and todays list");
        System.out.println("4 - Remove a food item");
        System.out.println("5 - Exit");
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
            case "5":
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
        double calories = scanner.nextDouble();
        scanner.nextLine();

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
        double cGoal = Double.parseDouble(scanner.nextLine());
        meals.setCalorieGoal(cGoal);
        System.out.println("Your calorie goal has been set to: " + cGoal);
    }

    // EFFECTS: prints out the list of foods added to the eatenMeals list and calorie goal
    private void viewFoodAndGoal() {
        System.out.println("\nFood you've eaten today:");
        if(meals.getEatenMeals().isEmpty()) {
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
}
