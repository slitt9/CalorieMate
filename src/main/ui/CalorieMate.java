package ui;

import model.Meal;
import model.FoodItem;
import model.User;

import java.util.Scanner;

public class CalorieMate {
    private Meal dailyLog;
    private User user;
    private Scanner scanner;
    private Meal breakfast;
    private Meal lunch;
    private Meal dinner;
    private Meal snack;
    private int targetCalories;

    // Constructor to initialize daily log and user
    public CalorieMate() {
        this.dailyLog = new Meal("Daily Log");
        this.scanner = new Scanner(System.in);
        this.dailyLog = new Meal("Daily Log");
        this.breakfast = new Meal("Breakfast");
        this.lunch = new Meal("Lunch");
        this.dinner = new Meal("Dinner");
        this.snack = new Meal("Snack");
        this.scanner = new Scanner(System.in);
    }

    // Run the console-based application
    public void run() {
        getUserInfo(); // Get user information before showing the menu
        boolean keepRunning = true;
        while (keepRunning) {
            printMenu();
            String command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "1":
                    addFoodItem();
                    break;
                case "2":
                    viewFoodItems();
                    break;
                case "3":
                    calculateRemainingCalories();
                    break;
                case "4":
                    removeFoodItem();
                    break;
                case "5":
                    keepRunning = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid command. Please choose a valid option.");
            }
        }
    }

    // Method to get user information
    private void getUserInfo() {
        System.out.println(
                "Welcome to CalorieMate! We specialize in assisting you to reach your fitness and health goals. Please enter your information.");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your sex (male/female): ");
        String sex = scanner.next();

        System.out.print("Enter your height (cm): ");
        double height = scanner.nextDouble();

        System.out.print("Enter your weight (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Enter your activity level (0-5): ");
        int activityLevel = scanner.nextInt();
        scanner.nextLine(); // consume newline

        user = new User(name, age, sex, height, weight, activityLevel);

        // Calculate maintenance calories and display
        int maintenanceCalories = user.calculateMaintenanceCalories();
        System.out.println("Your estimated maintenance calories are: " + maintenanceCalories);

        // Set target calories
        System.out.print("Set your target calories for the day: ");
        this.targetCalories = scanner.nextInt(); // Save the target calories to the class variable
        scanner.nextLine(); // consume newline
        System.out.println("Your target calories for the day are set to: " + this.targetCalories);
    }

    // Prints the available actions to the user
    private void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1 - Add a food item");
        System.out.println("2 - View logged food items");
        System.out.println("3 - View remaining calories for the day");
        System.out.println("4 - Remove a food item");
        System.out.println("5 - Exit");
    }

    // Allows user to add a food item
    public void addFoodItem() {
        System.out.println("Enter food item name: ");
        String foodName = scanner.nextLine();

        System.out.println("Enter calories for the item: ");
        double calories = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        // Ask for portion size
        System.out.println("Enter portion size: ");
        double portionSize = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        // Ask for meal type
        String mealType;
        while (true) {
            System.out.println("Enter the meal type (breakfast, lunch, dinner, snack): ");
            mealType = scanner.nextLine().toLowerCase();

            if (mealType.equals("breakfast") || mealType.equals("lunch") || mealType.equals("dinner")
                    || mealType.equals("snack")) {
                break;
            } else {
                System.out.println("Invalid meal type. Please enter breakfast, lunch, dinner, or snack.");
            }
        }

        // Create FoodItem and add to the appropriate meal
        FoodItem foodItem = new FoodItem(foodName, (int) calories, portionSize);
        switch (mealType) {
            case "breakfast":
                breakfast.addFoodItem(foodItem);
                break;
            case "lunch":
                lunch.addFoodItem(foodItem);
                break;
            case "dinner":
                dinner.addFoodItem(foodItem);
                break;
            case "snack":
                snack.addFoodItem(foodItem);
                break;
        }
        dailyLog.addFoodItem(foodItem);
    }

    // Displays the list of logged food items
    public void viewFoodItems() {
        System.out.println("Food Log:");

        System.out.println("\nBreakfast:");
        for (FoodItem item : breakfast.getFoodItems()) {
            System.out.println("- " + item.getFoodName() + ": " + item.calculateTotalCalories()
                    + " calories (Portion Size: " + item.getPortionSize() + ")");
        }

        System.out.println("\nLunch:");
        for (FoodItem item : lunch.getFoodItems()) {
            System.out.println("- " + item.getFoodName() + ": " + item.calculateTotalCalories()
                    + " calories (Portion Size: " + item.getPortionSize() + ")");
        }

        System.out.println("\nDinner:");
        for (FoodItem item : dinner.getFoodItems()) {
            System.out.println("- " + item.getFoodName() + ": " + item.calculateTotalCalories()
                    + " calories (Portion Size: " + item.getPortionSize() + ")");
        }

        System.out.println("\nSnacks:");
        for (FoodItem item : snack.getFoodItems()) {
            System.out.println("- " + item.getFoodName() + ": " + item.calculateTotalCalories()
                    + " calories (Portion Size: " + item.getPortionSize() + ")");
        }
    }

    // Displays remaining calories based on user's target
    public void calculateRemainingCalories() {
        double totalCaloriesConsumed = dailyLog.calculateTotalCalories();
        double remainingCalories = targetCalories - totalCaloriesConsumed; // Use stored targetCalories

        System.out.println("Total calories consumed: " + totalCaloriesConsumed);
        System.out.println("Remaining calories for the day: " + remainingCalories);

        if (remainingCalories < 0) {
            System.out.println(
                    "Warning: You have exceeded your calorie goal by " + Math.abs(remainingCalories) + " calories.");
        } else {
            System.out.println("Remaining Calories: " + remainingCalories);
        }
    }

    // Allows user to remove a food item
    public void removeFoodItem() {
        System.out.println("Enter the name of the food item to remove: ");
        String foodName = scanner.nextLine();
        FoodItem itemToRemove = null;

        for (FoodItem item : dailyLog.getFoodItems()) {
            if (item.getFoodName().equalsIgnoreCase(foodName)) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            dailyLog.removeFoodItem(itemToRemove);
            System.out.println("Removed " + foodName + " from your daily log.");
        } else {
            System.out.println("Food item not found in your daily log.");
        }
    }

    public static void main(String[] args) {
        CalorieMate app = new CalorieMate();
        app.run();
    }
}
