package com.pocketwatching.app;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.jfree.chart.ChartFactory;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import javafx.application.Platform;

public class HelloJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PocketWatching");
        primaryStage.setScene(createFirstScene(primaryStage));
        primaryStage.show();
    }

    // Intro Page
    private Scene createFirstScene(Stage primaryStage) {
        // Title Section
        Label title = new Label("PocketWatching");
        title.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");
    
        // Tagline
        Label tagline = new Label("Your journey to smarter spending starts here.");
        tagline.setStyle("-fx-font-size: 18px; -fx-text-fill: #f39c12; -fx-padding: 5 0 20 0;");
    
        // Buttons with Enhanced Styling
        Button button1 = new Button("Sign In");
        Button button2 = new Button("Create A New Account");
    
        button1.setStyle(
                "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20;");
        button2.setStyle(
                "-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20;");
    
        // Button Hover Effects
        button1.setOnMouseEntered(e -> button1.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20;"));
        button1.setOnMouseExited(e -> button1.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20;"));
    
        button2.setOnMouseEntered(e -> button2.setStyle("-fx-background-color: #1e88e5; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20;"));
        button2.setOnMouseExited(e -> button2.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20;"));
    
        button1.setOnAction(e -> primaryStage.setScene(createSecondScene(primaryStage))); // Switch to Sign In page
        button2.setOnAction(e -> primaryStage.setScene(createThirdScene(primaryStage))); // Switch to Create Account page
    
        // Decorative Footer Slogan
        Label footer = new Label("Start managing your finances today!");
        footer.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff; -fx-padding: 30 0 0 0; -fx-font-style: italic;");
    
        // Layout
        VBox layout = new VBox(20, title, tagline, button1, button2, footer);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50, 50, 50, 50));
        layout.setStyle("-fx-background-color: #2c3e50;");
    
        return new Scene(layout, 1024, 768);
    }
    

    // Sign In
    private Scene createSecondScene(Stage primaryStage) {
        // Title
        Label title = new Label("Sign In");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        // Username Input
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        // Password Input
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // Result Message Label
        Label resultLabel = new Label(""); // Initially empty
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");

        // Sign In Button
        Button signInButton = new Button("Sign In");
        signInButton
                .setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10;");
        signInButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            newAccount acc = new newAccount(username, password);

            // Check if the username exists
            if (acc.findDocument(username)) {
                // If username is found, show "Found!" and transition to an empty page
                resultLabel.setText("Found!");

                // Transition to an empty page
                primaryStage.setScene(createEmptyPage(primaryStage, username, password));
            } else {
                // If username is not found, show error message
                resultLabel.setText("Incorrect input of username or password. Retry");
            }
        });

        // Back Button
        Button backButton = new Button("Go Back");
        backButton
                .setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10;");
        backButton.setOnAction(e -> primaryStage.setScene(createFirstScene(primaryStage))); // Back to Main page

        // Layout
        VBox layout = new VBox(20, title, usernameLabel, usernameField, passwordLabel, passwordField, signInButton,
                resultLabel, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: #34495e;");

        return new Scene(layout, 1024, 768);
    }

    // Information from Database
    private Scene createEmptyPage(Stage primaryStage, String username, String password) {
        Transactions.getInformation();

        // Create a label for the username with large font and matching text color
        Label usernameLabel = new Label("Account Name: " + username);
        usernameLabel
                .setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 10 0;");

        // Create a background container for the username label
        HBox usernameContainer = new HBox(usernameLabel);
        usernameContainer.setAlignment(Pos.CENTER_LEFT);
        usernameContainer.setStyle("-fx-background-color: #2c3e50; -fx-padding: 15 30; -fx-border-radius: 5;");

        // Financial overview section title
        Label financialTitle = new Label("Financial Overview");
        financialTitle.setStyle("-fx-font-size: 24px; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 10;");

        // Create labels for the income, balance, and goal
        Label incomeLabel = new Label("Income: ");
        Label incomeValue = new Label("$" + Transactions.getIncome());
        incomeLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        incomeValue.setStyle("-fx-font-size: 20px; -fx-text-fill: #f39c12;");

        Label balanceLabel = new Label("Balance: ");
        Label balanceValue = new Label("$" + Transactions.getBalance());
        balanceLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        balanceValue.setStyle("-fx-font-size: 20px; -fx-text-fill: #f39c12;");

        Label goalLabel = new Label("Goal: ");
        Label goalValue = new Label("$" + Transactions.getGoal());
        goalLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        goalValue.setStyle("-fx-font-size: 20px; -fx-text-fill: #f39c12;");

        // Create HBoxes for each financial detail
        HBox incomeHBox = new HBox(15, incomeLabel, incomeValue);
        incomeHBox.setAlignment(Pos.CENTER_LEFT);

        HBox balanceHBox = new HBox(15, balanceLabel, balanceValue);
        balanceHBox.setAlignment(Pos.CENTER_LEFT);

        HBox goalHBox = new HBox(15, goalLabel, goalValue);
        goalHBox.setAlignment(Pos.CENTER_LEFT);

        // Create a VBox to hold the financial details
        VBox financialDetails = new VBox(10, financialTitle, incomeHBox, balanceHBox, goalHBox);
        financialDetails.setAlignment(Pos.TOP_LEFT);
        financialDetails.setStyle("-fx-padding: 30px 40px; -fx-background-color: #2c3e50; -fx-background-radius: 10;");

        // Expenses section title
        Label expensesTitle = new Label("Expense Breakdown");
        expensesTitle.setStyle("-fx-font-size: 24px; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 10;");

        // Create a GridPane for the expenses section
        GridPane expenseGrid = new GridPane();
        expenseGrid.setHgap(30);
        expenseGrid.setVgap(15);
        expenseGrid.setStyle("-fx-padding: 20;");

        // Add expense categories and values to the grid
        HashMap<String, Double> hash = Transactions.getList();
        int row = 0;
        for (Map.Entry<String, Double> entry : hash.entrySet()) {
            Label expenseNameLabel = new Label(entry.getKey() + ":");
            expenseNameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

            Label expenseValueLabel = new Label("$" + entry.getValue());
            expenseValueLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #f39c12;");

            expenseGrid.add(expenseNameLabel, 0, row);
            expenseGrid.add(expenseValueLabel, 1, row);
            row++;
        }

        Label totalExpenseLabel = new Label("Total Expense: $" + Transactions.getTotalExpense());
        totalExpenseLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        expenseGrid.add(totalExpenseLabel, 0, row, 2, 1);

        VBox expensesSection = new VBox(10, expensesTitle, expenseGrid);
        expensesSection.setStyle(
                "-fx-padding: 20px;" +
                        "-fx-background-color: #2c3e50;" + // Match background color with Balance/Goal/Income
                        "-fx-border-width: 2px;" + // Border thickness
                        "-fx-border-radius: 10px;" + // Rounded corners
                        "-fx-background-radius: 10px;" // Rounded corners for background
        );

        // Create a HBox for financial and expense details
        HBox mainLayout = new HBox(40, financialDetails, expensesSection);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-padding: 40px;");

        Button nextPageButton = new Button("Next Page");
        nextPageButton.setStyle("-fx-font-size: 18px; -fx-background-color: #f39c12; -fx-text-fill: #ffffff;");
        nextPageButton.setOnAction(e -> {
            // Call the method to create the next page and set the scene
            Scene nextScene = createPiePage(primaryStage, username, password);
            primaryStage.setScene(nextScene);
        });

        // Add the button to the layout
        VBox buttonContainer = new VBox(nextPageButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setStyle("-fx-padding: 20px;");

        // Add the button container to the bottom of the BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(usernameContainer);
        BorderPane.setAlignment(usernameContainer, Pos.TOP_LEFT);
        borderPane.setCenter(mainLayout);
        borderPane.setBottom(buttonContainer);
        borderPane.setStyle("-fx-background-color: #34495e;");

        // Return the scene
        return new Scene(borderPane, 1024, 768);
    }

    // PieChart
    private Scene createPiePage(Stage primaryStage, String username, String password) {
        // Create a label to display the next page title
        Label nextPageLabel = new Label("Expense Pie Chart, " + username + "!");
        nextPageLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        // Use the methods in the Report class to generate the pie chart
        PieDataset dataset = Report.createDataset(); // Call the method from the Report class
        JFreeChart pieChart = Report.createPieChart(dataset); // Create the chart using Report's method

        // Convert the JFreeChart to BufferedImage
        BufferedImage chartImage = pieChart.createBufferedImage(600, 400);

        // Convert the BufferedImage to JavaFX Image
        Image fxImage = SwingFXUtils.toFXImage(chartImage, null);

        // Create an ImageView to display the chart
        ImageView chartImageView = new ImageView(fxImage);
        chartImageView.setPreserveRatio(true);
        chartImageView.setFitWidth(600); // Optional: Adjust width
        chartImageView.setFitHeight(400); // Optional: Adjust height

        // Create a "Next" button in the bottom-right corner
        Button nextButton = new Button("Next");
        nextButton.setStyle("-fx-font-size: 18px; -fx-background-color: #27ae60; -fx-text-fill: #ffffff;");
        nextButton.setOnAction(e -> {
            // Navigate to the createBarGraph page
            Scene barGraphScene = createBarPage(primaryStage, username, password);
            primaryStage.setScene(barGraphScene);
        });

        // Layout for the Next button
        VBox chartLayout = new VBox(20, nextPageLabel, chartImageView);
        chartLayout.setAlignment(Pos.CENTER);

        // Bottom-right alignment for the Next button
        BorderPane layout = new BorderPane();
        layout.setCenter(chartLayout);
        layout.setBottom(nextButton);
        BorderPane.setAlignment(nextButton, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(nextButton, new Insets(10)); // Add padding around the button
        layout.setStyle("-fx-background-color: #34495e;");

        // Return the scene
        return new Scene(layout, 1024, 768);
    }

    // BarGraph
    private Scene createBarPage(Stage primaryStage, String username, String password) {
        // Create a label to display the next page title
        Label nextPageLabel = new Label("Expense Bar Chart, " + username + "!");
        nextPageLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        // Use the methods in the Report class to generate the bar graph
        DefaultCategoryDataset dataset = Report.createBarGraphDataset(); // Call the method from the Report class
        JFreeChart barGraph = Report.createBarGraph(dataset); // Create the chart using Report's method

        // Convert the JFreeChart to BufferedImage
        BufferedImage chartImage = barGraph.createBufferedImage(600, 400);

        // Convert the BufferedImage to JavaFX Image
        Image fxImage = SwingFXUtils.toFXImage(chartImage, null);

        // Create an ImageView to display the chart
        ImageView chartImageView = new ImageView(fxImage);
        chartImageView.setPreserveRatio(true);
        chartImageView.setFitWidth(600); // Optional: Adjust width
        chartImageView.setFitHeight(400); // Optional: Adjust height

        // Create a "Sign Out" button
        Button signOutButton = new Button("Sign Out");
        signOutButton.setStyle("-fx-font-size: 18px; -fx-background-color: #e74c3c; -fx-text-fill: #ffffff;");
        signOutButton.setOnAction(e -> {
            // Close the application
            User usr = new User(username, password);
            usr.close();
            primaryStage.close();
        });

        // Create a VBox for the chart and label
        VBox chartLayout = new VBox(20, nextPageLabel, chartImageView);
        chartLayout.setAlignment(Pos.CENTER);

        // Use a BorderPane for the overall layout
        BorderPane layout = new BorderPane();
        layout.setCenter(chartLayout); // Center the chart and label
        layout.setBottom(signOutButton); // Add the Sign Out button at the bottom
        BorderPane.setAlignment(signOutButton, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(signOutButton, new Insets(10)); // Add padding around the button
        layout.setStyle("-fx-background-color: #34495e;");

        // Return the scene
        return new Scene(layout, 1024, 768);
    }

    // Create a New Account
    private Scene createThirdScene(Stage primaryStage) {
        // Title
        Label title = new Label("Create Account");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        // Username Input
        Label usernameLabel = new Label("Enter Username:");
        usernameLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        // Password Input
        Label passwordLabel = new Label("Enter Password  (must contain at least one Special letter and one Number):");
        passwordLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // Result Message Label
        Label resultLabel = new Label(""); // Initially empty
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");

        // Sign In Button
        Button signInButton = new Button("Sign In");
        signInButton
                .setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10;");
        signInButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Create User instance and set username and password
            User usr = new User(username, password);
            usr.setPassword(password);
            usr.setUserName(username);
            usr.createUserDocument(); // Assuming this method saves the user data

            // Set the result label
            resultLabel.setText("Database has been established!");

            // Pass the username and password to the next page
            primaryStage.setScene(inputPosPage(primaryStage, usr.getUser(), usr.getPassword()));
        });

        // Back Button (bottom left)
        Button backButton = new Button("Go Back");
        backButton
                .setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10;");
        backButton.setOnAction(e -> primaryStage.setScene(createFirstScene(primaryStage))); // Back to the Main page

        // Next Button (bottom right)
        Button nextButton = new Button("Next");
        nextButton
                .setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10;");
        nextButton.setOnAction(e -> {
            // Fetch username and password from fields
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Pass them to the next page
            primaryStage.setScene(inputPosPage(primaryStage, username, password));
        });

        // Layout for inputs and buttons
        VBox inputLayout = new VBox(10, title, usernameLabel, usernameField, passwordLabel, passwordField, signInButton,
                resultLabel);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.setStyle("-fx-background-color: #34495e; -fx-padding: 30px;");

        // Bottom Buttons Layout (Back Button and Next Button)
        HBox bottomLayout = new HBox(10, backButton, nextButton);
        bottomLayout.setAlignment(Pos.CENTER_LEFT); // Align buttons to the left
        bottomLayout.setStyle("-fx-padding: 20px;");
        HBox.setHgrow(backButton, Priority.ALWAYS); // Allow "Go Back" button to be left-aligned
        nextButton.setAlignment(Pos.CENTER_RIGHT); // Align "Next" button to the right

        // Overall layout
        VBox layout = new VBox(20, inputLayout, bottomLayout);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: #34495e;");

        return new Scene(layout, 1024, 768);
    }

    // Input for Income, Balance, and Goal
    // Input of Expenses Page
    private Scene inputPosPage(Stage primaryStage, String username, String password) {
        // Title (Centered at the top)
        Label title = new Label("Input Your Financial Information");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        // Bank Balance Input
        Label balanceLabel = new Label("Input Bank Balance:");
        balanceLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");
        TextField balanceField = new TextField();
        balanceField.setPromptText("Enter Amount");
        balanceField.setPrefWidth(200); // Smaller input width
        Button balanceButton = new Button("Set Value");
        balanceButton
                .setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 5;");

        // Monthly Income Input
        Label incomeLabel = new Label("Input Monthly Income:");
        incomeLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");
        TextField incomeField = new TextField();
        incomeField.setPromptText("Enter Amount");
        incomeField.setPrefWidth(200); // Smaller input width
        Button incomeButton = new Button("Set Value");
        incomeButton
                .setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 5;");

        // Goal Input
        Label goalLabel = new Label("Input Financial Goal:");
        goalLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");
        TextField goalField = new TextField();
        goalField.setPromptText("Enter Amount");
        goalField.setPrefWidth(200); // Smaller input width
        Button goalButton = new Button("Set Value");
        goalButton
                .setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 5;");

        // Error message for invalid input
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff0000;");

        // Action for Bank Balance "Set Value" button
        balanceButton.setOnAction(e -> {
            try {
                double bankBalance = Double.parseDouble(balanceField.getText());
                Balance bal = new Balance(username, password, bankBalance);
                bal.setBalance(bankBalance);
                bal.addBalance();
                errorLabel.setText("Bank Balance saved.");
                errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #28a745;"); // Success message in green
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid Bank Balance input! Please enter a valid number.");
                errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff0000;");
            }
        });

        // Action for Monthly Income "Set Value" button
        incomeButton.setOnAction(e -> {
            try {
                double monthlyIncome = Double.parseDouble(incomeField.getText());
                Income inc = new Income(username, password, monthlyIncome);
                inc.setIncome(monthlyIncome);
                inc.addIncome();
                errorLabel.setText("Monthly Income saved.");
                errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #28a745;");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid Monthly Income input! Please enter a valid number.");
                errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff0000;");
            }
        });

        // Action for Financial Goal "Set Value" button
        goalButton.setOnAction(e -> {
            try {
                double goalAmount = Double.parseDouble(goalField.getText());
                Goal goal = new Goal(username, password, goalAmount);
                goal.setGoal(goalAmount);
                goal.addGoal();
                errorLabel.setText("Financial Goal saved.");
                errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #28a745;");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid Financial Goal input! Please enter a valid number.");
                errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff0000;");
            }
        });

        // Create horizontal layouts for each input section
        HBox balanceHBox = new HBox(20, balanceLabel, balanceField, balanceButton);
        balanceHBox.setAlignment(Pos.CENTER);

        HBox incomeHBox = new HBox(20, incomeLabel, incomeField, incomeButton);
        incomeHBox.setAlignment(Pos.CENTER);

        HBox goalHBox = new HBox(20, goalLabel, goalField, goalButton);
        goalHBox.setAlignment(Pos.CENTER);

        // Add 'Next' Button (Centered at the bottom)
        Button nextButton = new Button("Next");
        nextButton.setStyle(
                "-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px;");
        nextButton.setOnAction(e -> {
            // Logic to go to the next page or method
            primaryStage.setScene(createExpensesPage(primaryStage, username, password));
        });

        // Layout for the new page
        VBox newPageLayout = new VBox(20, title, balanceHBox, incomeHBox, goalHBox, errorLabel);
        newPageLayout.setAlignment(Pos.TOP_CENTER); // Align the content to the top center
        newPageLayout.setStyle("-fx-background-color: #2c3e50; -fx-padding: 50px;");
        newPageLayout.setSpacing(20); // Spacing between components

        // Add Next Button at the bottom-center
        VBox mainLayout = new VBox(20, newPageLayout, nextButton);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: #2c3e50; -fx-padding: 50px;");
        mainLayout.setSpacing(20);

        return new Scene(mainLayout, 1024, 768);
    }

    private Scene createExpensesPage(Stage primaryStage, String username, String password) {
        // Title (Centered at the top)
        Label title = new Label("Input Your Expenses Financial Information");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        // Create GridPane for expense inputs (two columns, five rows)
        GridPane expenseGrid = new GridPane();
        expenseGrid.setVgap(10); // Vertical gap between rows
        expenseGrid.setHgap(20); // Horizontal gap between columns
        expenseGrid.setAlignment(Pos.CENTER);

        // Arrays for labels and text fields
        TextField[] valueFields = new TextField[5];
        TextField[] labelFields = new TextField[5];

        // Create 5 rows for the inputs: one for the label and one for the value
        for (int i = 0; i < 5; i++) {
            // Expense label field (column 1)
            labelFields[i] = new TextField();
            labelFields[i].setPromptText("Enter Expense Label " + (i + 1));
            labelFields[i].setPrefWidth(200);

            // Expense value field (column 2)
            valueFields[i] = new TextField();
            valueFields[i].setPromptText("Enter Amount");
            valueFields[i].setPrefWidth(200);

            // Add the fields to the grid (labels in column 0, values in column 1)
            expenseGrid.add(labelFields[i], 0, i); // Column 0 (Label)
            expenseGrid.add(valueFields[i], 1, i); // Column 1 (Value)
        }

        Label resultLabel = new Label(""); // Initially empty
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff;");

        // Button to submit and set the HashMap
        Button setExpensesButton = new Button("Set Expenses");
        setExpensesButton.setStyle(
                "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px;");

        setExpensesButton.setOnAction(e -> {
            HashMap<String, Double> expensesMap = new HashMap<>();

            // Populate HashMap with user inputs
            for (int i = 0; i < 5; i++) {
                try {
                    String label = labelFields[i].getText().trim();
                    double value = Double.parseDouble(valueFields[i].getText());

                    // Add to HashMap (expense label and its corresponding value)
                    expensesMap.put(label, value);
                } catch (NumberFormatException ex) {
                    // Handle invalid number input
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid number for all expenses.");
                    alert.showAndWait();
                    return; // Stop further processing if input is invalid
                }
            }

            // Set the expenses map in the Expenses class
            Expenses spent = new Expenses(username, password, expensesMap);
            spent.setList(expensesMap);
            spent.setTotalExp(spent.getTotalExp());
            spent.addListToDoc();
            // Expenses.setList(expensesMap);
            resultLabel.setText("Expenses have been Established");

            // Provide feedback to the user
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Expenses have been set successfully!");
            successAlert.showAndWait();
        });

        // Button to navigate to the next page
        Button nextButton = new Button("Next");
        nextButton.setStyle(
                "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px;");

        nextButton.setOnAction(e -> {
            // Navigate to the createEmptyPage method (this is the next page)
            Scene nextScene = DisplayValuesPage(primaryStage, username, password);
            primaryStage.setScene(nextScene);
        });

        // Layout for the Expenses page
        VBox expensesPageLayout = new VBox(20, title, expenseGrid, setExpensesButton, nextButton);
        expensesPageLayout.setAlignment(Pos.CENTER);
        expensesPageLayout.setStyle("-fx-background-color: #2c3e50; -fx-padding: 50px;");
        expensesPageLayout.setSpacing(20);

        return new Scene(expensesPageLayout, 1024, 768);
    }

    private Scene DisplayValuesPage(Stage primaryStage, String username, String password) {
        Transactions.getInformation();

        // Create a label for the username with large font and matching text color
        Label usernameLabel = new Label("Account Name: " + username);
        usernameLabel
                .setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 10 0;");

        // Create a background container for the username label
        HBox usernameContainer = new HBox(usernameLabel);
        usernameContainer.setAlignment(Pos.CENTER_LEFT);
        usernameContainer.setStyle("-fx-background-color: #2c3e50; -fx-padding: 15 30; -fx-border-radius: 5;");

        // Financial overview section title
        Label financialTitle = new Label("Financial Overview");
        financialTitle.setStyle("-fx-font-size: 24px; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 10;");

        // Create labels for the income, balance, and goal
        Label incomeLabel = new Label("Income: ");
        Label incomeValue = new Label("$" + Transactions.getIncome());
        incomeLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        incomeValue.setStyle("-fx-font-size: 20px; -fx-text-fill: #f39c12;");

        Label balanceLabel = new Label("Balance: ");
        Label balanceValue = new Label("$" + Transactions.getBalance());
        balanceLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        balanceValue.setStyle("-fx-font-size: 20px; -fx-text-fill: #f39c12;");

        Label goalLabel = new Label("Goal: ");
        Label goalValue = new Label("$" + Transactions.getGoal());
        goalLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        goalValue.setStyle("-fx-font-size: 20px; -fx-text-fill: #f39c12;");

        // Create HBoxes for each financial detail
        HBox incomeHBox = new HBox(15, incomeLabel, incomeValue);
        incomeHBox.setAlignment(Pos.CENTER_LEFT);

        HBox balanceHBox = new HBox(15, balanceLabel, balanceValue);
        balanceHBox.setAlignment(Pos.CENTER_LEFT);

        HBox goalHBox = new HBox(15, goalLabel, goalValue);
        goalHBox.setAlignment(Pos.CENTER_LEFT);

        // Create a VBox to hold the financial details
        VBox financialDetails = new VBox(10, financialTitle, incomeHBox, balanceHBox, goalHBox);
        financialDetails.setAlignment(Pos.TOP_LEFT);
        financialDetails.setStyle("-fx-padding: 30px 40px; -fx-background-color: #2c3e50; -fx-background-radius: 10;");

        // Expenses section title
        Label expensesTitle = new Label("Expense Breakdown");
        expensesTitle.setStyle("-fx-font-size: 24px; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 10;");

        // Create a GridPane for the expenses section
        GridPane expenseGrid = new GridPane();
        expenseGrid.setHgap(30);
        expenseGrid.setVgap(15);
        expenseGrid.setStyle("-fx-padding: 20;");

        // Add expense categories and values to the grid
        HashMap<String, Double> hash = Transactions.getList();
        int row = 0;
        for (Map.Entry<String, Double> entry : hash.entrySet()) {
            Label expenseNameLabel = new Label(entry.getKey() + ":");
            expenseNameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

            Label expenseValueLabel = new Label("$" + entry.getValue());
            expenseValueLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #f39c12;");

            expenseGrid.add(expenseNameLabel, 0, row);
            expenseGrid.add(expenseValueLabel, 1, row);
            row++;
        }

        Label totalExpenseLabel = new Label("Total Expense: $" + Transactions.getTotalExpense());
        totalExpenseLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        expenseGrid.add(totalExpenseLabel, 0, row, 2, 1);

        // Create a VBox for expenses
        VBox expensesSection = new VBox(10, expensesTitle, expenseGrid);
        expensesSection.setStyle("-fx-padding: 20px; -fx-background-color: #34495e; -fx-background-radius: 10;");

        // Create a HBox for financial and expense details
        HBox mainLayout = new HBox(40, financialDetails, expensesSection);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-padding: 40px;");

        Button nextPageButton = new Button("Next Page");
        nextPageButton.setStyle("-fx-font-size: 18px; -fx-background-color: #f39c12; -fx-text-fill: #ffffff;");
        nextPageButton.setOnAction(e -> {
            // Call the method to create the next page and set the scene
            Scene nextScene = createPiePage2(primaryStage, username, password);
            primaryStage.setScene(nextScene);
        });

        // Add the button to the layout
        VBox buttonContainer = new VBox(nextPageButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setStyle("-fx-padding: 20px;");

        // Add the button container to the bottom of the BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(usernameContainer);
        BorderPane.setAlignment(usernameContainer, Pos.TOP_LEFT);
        borderPane.setCenter(mainLayout);
        borderPane.setBottom(buttonContainer);
        borderPane.setStyle("-fx-background-color: #34495e;");

        // Return the scene
        return new Scene(borderPane, 1024, 768);
    }

    // PieChart
    private Scene createPiePage2(Stage primaryStage, String username, String password) {
        // Create a label to display the next page title
        Label nextPageLabel = new Label("Expense Pie Chart, " + username + "!");
        nextPageLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        // Use the methods in the Report class to generate the pie chart
        PieDataset dataset = Report.createDataset(); // Call the method from the Report class
        JFreeChart pieChart = Report.createPieChart(dataset); // Create the chart using Report's method

        // Convert the JFreeChart to BufferedImage
        BufferedImage chartImage = pieChart.createBufferedImage(600, 400);

        // Convert the BufferedImage to JavaFX Image
        Image fxImage = SwingFXUtils.toFXImage(chartImage, null);

        // Create an ImageView to display the chart
        ImageView chartImageView = new ImageView(fxImage);
        chartImageView.setPreserveRatio(true);
        chartImageView.setFitWidth(600); // Optional: Adjust width
        chartImageView.setFitHeight(400); // Optional: Adjust height

        // Create a "Next" button in the bottom-right corner
        Button nextButton = new Button("Next");
        nextButton.setStyle("-fx-font-size: 18px; -fx-background-color: #27ae60; -fx-text-fill: #ffffff;");
        nextButton.setOnAction(e -> {
            // Navigate to the createBarGraph page
            Scene barGraphScene = createBarPage2(primaryStage, username, password);
            primaryStage.setScene(barGraphScene);
        });

        // Layout for the Next button
        VBox chartLayout = new VBox(20, nextPageLabel, chartImageView);
        chartLayout.setAlignment(Pos.CENTER);

        // Bottom-right alignment for the Next button
        BorderPane layout = new BorderPane();
        layout.setCenter(chartLayout);
        layout.setBottom(nextButton);
        BorderPane.setAlignment(nextButton, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(nextButton, new Insets(10)); // Add padding around the button
        layout.setStyle("-fx-background-color: #34495e;");

        // Return the scene
        return new Scene(layout, 1024, 768);
    }

    private Scene createBarPage2(Stage primaryStage, String username, String password) {
        // Create a label to display the next page title
        Label nextPageLabel = new Label("Expense Bar Chart, " + username + "!");
        nextPageLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        // Use the methods in the Report class to generate the bar graph
        DefaultCategoryDataset dataset = Report.createBarGraphDataset(); // Call the method from the Report class
        JFreeChart barGraph = Report.createBarGraph(dataset); // Create the chart using Report's method

        // Convert the JFreeChart to BufferedImage
        BufferedImage chartImage = barGraph.createBufferedImage(600, 400);

        // Convert the BufferedImage to JavaFX Image
        Image fxImage = SwingFXUtils.toFXImage(chartImage, null);

        // Create an ImageView to display the chart
        ImageView chartImageView = new ImageView(fxImage);
        chartImageView.setPreserveRatio(true);
        chartImageView.setFitWidth(600); // Optional: Adjust width
        chartImageView.setFitHeight(400); // Optional: Adjust height

        // Create a "Sign Out" button
        Button signOutButton = new Button("Sign Out");
        signOutButton.setStyle("-fx-font-size: 18px; -fx-background-color: #e74c3c; -fx-text-fill: #ffffff;");
        signOutButton.setOnAction(e -> {
            // Close the application
            User usr = new User(username, password);
            usr.close();
            primaryStage.close();
        });

        // Create a VBox for the chart and label
        VBox chartLayout = new VBox(20, nextPageLabel, chartImageView);
        chartLayout.setAlignment(Pos.CENTER);

        // Use a BorderPane for the overall layout
        BorderPane layout = new BorderPane();
        layout.setCenter(chartLayout); // Center the chart and label
        layout.setBottom(signOutButton); // Add the Sign Out button at the bottom
        BorderPane.setAlignment(signOutButton, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(signOutButton, new Insets(10)); // Add padding around the button
        layout.setStyle("-fx-background-color: #34495e;");

        // Return the scene
        return new Scene(layout, 1024, 768);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// mvn exec:java -Dexec.mainClass="com.pocketwatching.app.HelloJavaFX"

/*
 * I want to create a hashmap in javaFX method, that will input values of
 * <String,Double>
 * in the hashmap. Each input and key value will have its own input bar, and it
 * will be inputted my user I want
 * 5 of these boxes.
 * 
 * 
 * 
 * Expenses
 * Key Value
 * Enter Exp Label Enter value
 * Enter Exp Label Enter value
 * Enter Exp Label Enter value
 * Enter Exp Label Enter value
 * Enter Exp Label Enter value
 * 
 * 
 * 
 */
