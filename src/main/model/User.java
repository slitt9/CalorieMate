package model;

// Represents a user profile in the application
public class User {
    private String name;        // name of user
    private int age;            // age of user (in years)
    private String sex;         // sex of user (male or female)
    private double height;      // height of user (in cm) 
    private double weight;      // weight of user (in kg)
    private int activityLevel;  // physical activity level of user (ranging from 0-5)


    // REQUIRES: age must be positive, sex must be either "male" or "female",
    //           height and weight must be positive integers, activityLevel between 0 and 5
    // EFFECTS: constructs a User with given age, sex, height, weight, and activity level
    public User(String name, int age, String sex, double height, double weight, int activityLevel) {
        // stub 
    }
    
    // REQUIRES: age must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the user's age to the given age
    void setAge(int age) {
        // stub
    }

    int getAge() {
        return 0; // stub
    }

    // REQUIRES: sex must be either "male" or "female"
    // MODIFIES: this
    // EFFECTS: sets the user's sex to the given sex
    void setSex(String sex) {
        // stub
    }

    String getSex() {
        return ""; // stub
    }

    // REQUIRES: height must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the user's height to the given height
    void setHeight(double height) {
        // stub 
    }

    double getHeight() {
        return 0.0; // stub
    }

    // REQUIRES: weight must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the user's weight to the given weight
    void setWeight(double weight) {
        // stub
    }
    
    double getWeight() {
        return 0.0; // stub
    }

    // REQUIRES: activityLevel must be between 0 and 5 (inclusive)
    // MODIFIES: this
    // EFFECTS: sets the user's activity level to the given level
    void setActivityLevel(int activityLevel) {
        // stub
    }

    int getActivityLevel() {
        return 0; // stub
    }

    // EFFECTS: calculates and returns the user's BMR based on age, sex, height, and weight
    double calculateBMR() {
        return 0.0; // stub
    }

    // EFFECTS: calculates and returns the user's maintenance calories based on BMR and activity level
    double calculateMaintenanceCalories() {
        return 0.0; // stub
    }
}





