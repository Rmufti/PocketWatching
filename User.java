package com.pocketwatching.app;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Scanner;

public class User {

    private static String username;
    private static String password;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;
    private static MongoClient client;

    // Static block to initialize the MongoDB connection
    static {
        String uri = "mongodb+srv://rameezmufti:admin@cluster0.wfdzu.mongodb.net/";
        MongoClientURI clientUri = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientUri);

        database = mongoClient.getDatabase("pocket");
        collection = database.getCollection("usrs");
        client = mongoClient;
    }

    // Constructor
    public User(String user, String pass) {
        User.username = user;
        User.password = pass;
    }

    // Getters
    public static String getUser() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static MongoDatabase getDatabase(){
        return database;
    }

    public static MongoCollection getCollection(){
        return collection;
    }

    public static MongoClient getClient(){
        return client;
    }

    // Setters
    public static void setUserName(String user) {
        User.username = user;
    }

    public static void setPassword(String pass) {
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Enter password (must contain at least one letter and one number): \n");
            User.password = pass;

            if (password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*")) {
                isValid = true;
                System.out.println("Password set successfully.");
            } else {
                System.out.println("Password must contain at least one letter and one number. Please try again.");
            }
        }
    }

    // Method to create a new user document in the MongoDB collection
    public static void createUserDocument() {
        // Check if a document with the same username already exists
        Document existingUser = collection.find(new Document("username", username)).first();

        if (existingUser != null) {
            System.out.println("Username already exists. Please try a different username.");
            return;
        }

        // Create a document with username and password
        Document userDoc = new Document("username", username)
                .append("password", password);

        // Insert the document into the 'usrs' collection
        collection.insertOne(userDoc);

        System.out.println("User document created with username: " + username);
    }

    // Method to retrieve the user's document by username
    public static Document getUserDocument() {
        Document userDoc = collection.find(new Document("username", username)).first();

        if (userDoc != null) {
            System.out.println("Document retrieved: " + userDoc.toJson());
        } else {
            System.out.println("No document found for username: " + username);
        }

        return userDoc;
    }
    public static void close() {
        if (client != null) {
            client.close();
            System.out.println("MongoDB connection closed.");
        }
    }
//mvn exec:java -Dexec.mainClass="com.pocketwatching.app.User"
    // Main method for testing
    public static void main(String[] args) {
        User test = new User(null, null);

        // Set username and password
      //  test.setUserName();
     //   test.setPassword();

        // Create a user document
        test.createUserDocument();

        // Retrieve and print the created user document
        test.getUserDocument();

        close();
    }
}
