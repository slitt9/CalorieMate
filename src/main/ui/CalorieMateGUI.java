package ui;

import model.FoodItem;
import model.Meals;
import persistence.JReader;
import persistence.JWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class CalorieMateGUI extends JFrame {
    private static final String JSON_STORE = "./data/meals.json";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Color THEME_COLOR = new Color(70, 130, 180);

    private Meals meals;
    private JReader jsonReader;
    private JWriter jsonWriter;
    private JList<String> foodList;
    private DefaultListModel<String> listModel;
    private JPanel graphPanel;
    private JLabel totalCaloriesLabel;
    private JLabel goalLabel;

    // EFFECTS: Creates new CalorieMate GUI window
    public CalorieMateGUI() {
        super("CalorieMate");
        showSplashScreen();
        initializeFields();
        initializeGraphics();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Initializes the main fields
    private void initializeFields() {
        meals = new Meals();
        jsonReader = new JReader(JSON_STORE);
        jsonWriter = new JWriter(JSON_STORE);
        listModel = new DefaultListModel<>();
    }

    // MODIFIES: this
    // EFFECTS: Initializes the graphics and layout of the GUI
    private void initializeGraphics() {
        setLayout(new BorderLayout(10, 10));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        createFoodListPanel(mainPanel);
        createButtonPanel(mainPanel);
        createGraphPanel(mainPanel);

        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates the food list panel with header
    private void createFoodListPanel(JPanel mainPanel) {
        JPanel listPanel = new JPanel(new BorderLayout(5, 5));
        listPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Food Items", TitledBorder.LEFT, TitledBorder.TOP));

        JPanel headerPanel = new JPanel(new BorderLayout());
        totalCaloriesLabel = new JLabel("Total Calories: 0");
        totalCaloriesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(totalCaloriesLabel, BorderLayout.WEST);

        JButton sortButton = new JButton("Sort by Calories");
        sortButton.setPreferredSize(new Dimension(150, 25));
        sortButton.addActionListener(e -> sortByCalories());
        headerPanel.add(sortButton, BorderLayout.EAST);

        listPanel.add(headerPanel, BorderLayout.NORTH);

        foodList = new JList<>(listModel);
        foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(foodList);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(listPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Creates the button panel
    private void createButtonPanel(JPanel mainPanel) {
        JPanel buttonPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("CalorieMate: Your Personalized Calorie Tracker");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addStyledButton(buttons, "Add Food", e -> addFood());
        addStyledButton(buttons, "Remove", e -> removeSelectedFood());
        addStyledButton(buttons, "Save", e -> saveData());
        addStyledButton(buttons, "Load", e -> loadData());

        buttonPanel.add(titleLabel, BorderLayout.WEST);
        buttonPanel.add(buttons, BorderLayout.EAST);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: Creates a styled button
    private void addStyledButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(THEME_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Creates the graph panel
    private void createGraphPanel(JPanel mainPanel) {
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCalorieGraph((Graphics2D) g);
            }
        };

        JPanel wrapperPanel = new JPanel(new BorderLayout());

        JPanel goalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        goalLabel = new JLabel("Current Goal: " + meals.getCalorieGoal() + " calories");
        JButton setGoalButton = new JButton("Set Goal");
        setGoalButton.addActionListener(e -> setCalorieGoal());
        goalPanel.add(goalLabel);
        goalPanel.add(setGoalButton);

        graphPanel.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT / 4));
        graphPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Calorie Progress Graph", TitledBorder.LEFT, TitledBorder.TOP));

        wrapperPanel.add(goalPanel, BorderLayout.NORTH);
        wrapperPanel.add(graphPanel, BorderLayout.CENTER);
        mainPanel.add(wrapperPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: Draws the basic axes for the graph
    private void drawAxes(Graphics2D g, int w, int h, int x0, int y0) {
        g.setColor(Color.BLACK);
        g.drawLine(x0, y0, w, y0);
        g.drawLine(x0, 10, x0, y0);
        g.drawString("Time →", w - 50, y0 + 15);
        g.rotate(-Math.PI / 2);
        g.drawString("Calories →", -h + 20, x0 - 5);
        g.rotate(Math.PI / 2);
    }

    // EFFECTS: Draws the calorie graph
    private void drawCalorieGraph(Graphics2D g) {
        int w = graphPanel.getWidth() - 40;
        int h = graphPanel.getHeight() - 40;
        int x0 = 30;
        int y0 = h - 10;

        drawAxes(g, w, h, x0, y0);
        int goal = meals.getCalorieGoal();

        if (goal > 0) {
            g.setColor(Color.RED);
            int goalY = y0 - (int) (y0 * goal / (goal * 1.2));
            g.drawLine(x0, goalY, w, goalY);

            g.setColor(THEME_COLOR);
            int spacing = (w - x0) / (meals.getEatenMeals().size() + 1);
            int x = x0 + spacing;
            int total = 0;

            for (FoodItem food : meals.getEatenMeals()) {
                total += food.getCalories();
                int y = y0 - (int) (y0 * total / (goal * 1.2));
                g.fillOval(x - 4, y - 4, 8, 8);
                x += spacing;
            }
        } else {
            g.drawString("Set a calorie goal", x0 + 10, h / 2);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles adding a new food item
    private void addFood() {
        String name = JOptionPane.showInputDialog(this,
                "Enter food name:",
                "Add Food",
                JOptionPane.PLAIN_MESSAGE);

        if (name != null && !name.trim().isEmpty()) {
            String caloriesStr = JOptionPane.showInputDialog(this,
                    "Enter calories:",
                    "Add Food",
                    JOptionPane.PLAIN_MESSAGE);

            try {
                int calories = Integer.parseInt(caloriesStr);
                FoodItem food = new FoodItem(name, calories);
                meals.addFoodItem(food);
                updateFoodList();
                graphPanel.repaint();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number for calories",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the calorie goal
    private void setCalorieGoal() {
        String input = JOptionPane.showInputDialog(this,
                "Enter calorie goal:",
                "Set Goal",
                JOptionPane.PLAIN_MESSAGE);
        try {
            int goal = Integer.parseInt(input);
            meals.setCalorieGoal(goal);
            goalLabel.setText("Current Goal: " + goal + " calories");
            graphPanel.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the selected food item
    private void removeSelectedFood() {
        int index = foodList.getSelectedIndex();
        if (index != -1) {
            String foodName = listModel.get(index).split(":")[0].trim();
            meals.removeFoodItem(foodName);
            updateFoodList();
            graphPanel.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: Sorts the food items by calories
    private void sortByCalories() {
        meals.sortByCaloriesDescending();
        updateFoodList();
    }

    // MODIFIES: this
    // EFFECTS: Updates the food list display
    private void updateFoodList() {
        listModel.clear();
        for (FoodItem food : meals.getEatenMeals()) {
            listModel.addElement(food.getFoodName() + ": " + food.getCalories() + " calories");
        }
        updateTotalCalories();
    }

    // EFFECTS: Handles application closing
    private void handleClosing() {
        int result = JOptionPane.showConfirmDialog(this,
                "Would you like to save before exiting?",
                "Save Data",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            saveData();
            dispose();
        } else if (result == JOptionPane.NO_OPTION) {
            dispose();
        }
    }

    // EFFECTS: Saves data to file
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(meals);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error: Could not save data");
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads data from file
    private void loadData() {
        try {
            meals = jsonReader.read();
            updateFoodList();
            goalLabel.setText("Current Goal: " + meals.getCalorieGoal() + " calories");
            totalCaloriesLabel.setText("Total Calories: " + meals.calculateTotalCalories());
            graphPanel.repaint();
            JOptionPane.showMessageDialog(this, "Data loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: Could not load data");
        }
    }

    // EFFECTS: Shows splash screen for 2 seconds before initializing main window
    private void showSplashScreen() {
        JWindow splashScreen = new JWindow();
        try {
            ImageIcon splashImage = new ImageIcon(System.getProperty("user.dir")
                    + "/src/main/resources/splash.png");

            splashScreen.getContentPane().add(
                    new JLabel("", splashImage, SwingConstants.CENTER));
            splashScreen.setBounds(0, 0, splashImage.getIconWidth(), splashImage.getIconHeight());
            splashScreen.setLocationRelativeTo(null);
            splashScreen.setVisible(true);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error loading splash screen: " + e.getMessage());
        } finally {
            splashScreen.dispose();
        }
    }

    private void updateTotalCalories() {
        totalCaloriesLabel.setText("Total Calories: " + meals.calculateTotalCalories());
    }
}