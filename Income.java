package com.pocketwatching.app;

import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

public class Income extends newAccount{

    private static Double income;
    
        public Income(String usr, String psw, Double inc){
            super(usr,psw);
            Income.income = inc;
        }
    
    
        public static Double getIncome(){
                return income;
        }
    
        public static void setIncome(Double value){
            Income.income = value;
        }
    
        public static void addIncome(){
            MongoCollection<Document> collection = User.getCollection();
    
            // Query the collection for a document where 'username' matches
            Document userDoc = collection.find(new Document("username", getUser())).first();
    
            if (userDoc != null) {
                // Add the "Goal" field using updateOne
                ObjectId objectID = userDoc.getObjectId("_id"); // Retrieve the document's _id
                //Double value = getIncome();
                collection.updateOne(
                    new Document("_id", objectID), // Filter to find the document by _id
                    new Document("$set", new Document("income", getIncome())) // Add/Update the Goal field
                );
            System.out.println("Goal successfully added/updated!");
        } else {
            System.out.println("User document not found!");
        }
    }

    public static void main(String[] args) {
        String usr = "rameez";
        String psw = "Pak1stan";

        // Create a newAccount object
        newAccount test = new newAccount(usr, psw);

        Income test0 = new Income(usr,psw,0.0);
    //    test0.setIncome();
        test0.addIncome();

        test.close();
    }
    
}
