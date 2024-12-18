package com.pocketwatching.app;

import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Goal extends newAccount{

    private static Double goal;


    public Goal(String username, String pass, Double goal){
        super(username, pass);
        Goal.goal = goal;
    }

    public static void setGoal(Double value){
        Goal.goal = value;
    }

    public static Double getGoal(){
        return Goal.goal;
    }

    public static void addGoal(){
        MongoCollection<Document> collection = User.getCollection();

        // Query the collection for a document where 'username' matches
        Document userDoc = collection.find(new Document("username", getUser())).first();

        if (userDoc != null) {
            // Add the "Goal" field using updateOne
            ObjectId objectID = userDoc.getObjectId("_id"); // Retrieve the document's _id
            getGoal();
            collection.updateOne(
                new Document("_id", objectID), // Filter to find the document by _id
                new Document("$set", new Document("Goal", Goal.goal)) // Add/Update the Goal field
            );
            System.out.println("Goal successfully added/updated!");
        } else {
            System.out.println("User document not found!");
        }
    }

// mvn exec:java -Dexec.mainClass="com.pocketwatching.app.Goal"
    public static void main(String[] args) {
        String usr = "rameez";
        String psw = "Pak1stan";

        // Create a newAccount object
        newAccount test = new newAccount(usr, psw);

        Goal test0 = new Goal(usr,psw,0.0);
    //    test0.setGoal();
        test0.addGoal();

        test.close();
    }
    
}
