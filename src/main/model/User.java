package model;

// Represents a user profile in the application
public class User {
    public String name; // name of user
    public int age; // age of user (in years)
    public String sex; // sex of user (male or female)
    public double height; // height of user (in cm)
    public double weight; // weight of user (in kg)
    public int activityLevel; // physical activity level of user (ranging from 0-5)

    // REQUIRES: age must be positive, sex must be either "male" or "female",
    // height and weight must be positive integers, activityLevel between 0 and 5
    // EFFECTS: constructs a User with given age, sex, height, weight, and activity
    // level
    public User(String name, int age, String sex, double height, double weight, int activityLevel) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
    }

    // MODIFIES: this
    // EFFECTS: sets the user's name to the given name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name; // stub
    }

    // REQUIRES: age must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the user's age to the given age
    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    // REQUIRES: sex must be either "male" or "female"
    // MODIFIES: this
    // EFFECTS: sets the user's sex to the given sex
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    // REQUIRES: height must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the user's height to the given height
    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    // REQUIRES: weight must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the user's weight to the given weight
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    // REQUIRES: activityLevel must be between 0 and 5 (inclusive)
    // MODIFIES: this
    // EFFECTS: sets the user's activity level to the given level
    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    // EFFECTS: calculates and returns the user's BMR based on age, sex, height, and
    // weight
    public int calculateBMR() {
        if (sex.equalsIgnoreCase("male")) {
            return (int) ((10 * weight) + (6.25 * height) - (5 * age) + 5);
        } else {
            return (int) ((10 * weight) + (6.25 * height) - (5 * age) - 161);
        }
    }

    // EFFECTS: calculates and returns the user's maintenance calories based on BMR
    // and activity level
    public int calculateMaintenanceCalories() {
        int bmr = calculateBMR();
        if (activityLevel == 0) {
            return (int) (bmr * 1.2); // Not Active
        } else if (activityLevel == 1) {
            return (int) (bmr * 1.375); // Lightly Active
        } else if (activityLevel == 2) {
            return (int) (bmr * 1.55); // Moderately Active
        } else if (activityLevel == 3) {
            return (int) (bmr * 1.725); // Very Active
        } else if (activityLevel == 4) {
            return (int) (bmr * 1.9); // Extra Active
        } else if (activityLevel == 5) {
            return (int) (bmr * 2.0); // Extremely Active
        } else {
            throw new IllegalArgumentException("Invalid activity level: " + activityLevel);
        }
    }

}
