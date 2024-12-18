package com.pocketwatching.app;

//import static com.pocketwatching.app.User.collection;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class newAccount extends User {

    private static String username;
    private Object objectID;

    public newAccount(String user, String pass) {
        super(user, pass);
        this.username = user;
        this.objectID = null;

    }

    // Method to find a document by username
    public boolean findDocument(String username) {
        // Reuse the MongoDB collection from the User superclass
        MongoCollection<Document> collection = User.getCollection();

        // Query the collection for a document where 'username' matches
        Document userDoc = collection.find(new Document("username", username)).first();

        // Return true if document exists, otherwise false
        if (userDoc != null) {
            this.objectID = userDoc.get("_id");
            System.out.println("Document found: " + userDoc.toJson());
            return true; // Document found
        } else {
            System.out.println("User with username " + username + " not found.");
            return false; // Document not found
        }
    }

    // mvn exec:java -Dexec.mainClass="com.pocketwatching.app.newAccount"
    public static void main(String[] args) {
        String usr = "rameez";
        String psw = "Pak1stan";

        // Create a newAccount object
        newAccount test = new newAccount(usr, psw);

        // Find and print a specific document
        test.findDocument(usr);

        // Print all documents in the collection
       // test.printDoc();

        test.close();
    }

}