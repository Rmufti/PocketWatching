package com.pocketwatching.app;

import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

public class Balance extends newAccount {
    private static Double balance;
    
        public Balance(String usr, String psw, Double bal){
            super(usr,psw);
            Balance.balance = bal;
        }
    
    
        public static Double getBalance(){
                return balance;
        }
    
        public static void setBalance(Double value){
            Balance.balance = value;
        }
    
        public static void addBalance(){
            MongoCollection<Document> collection = User.getCollection();
    
            // Query the collection for a document where 'username' matches
            Document userDoc = collection.find(new Document("username", getUser())).first();
    
            if (userDoc != null) {
                // Add the "Goal" field using updateOne
                ObjectId objectID = userDoc.getObjectId("_id"); // Retrieve the document's _id
                //Double value = getIncome();
                collection.updateOne(
                    new Document("_id", objectID), // Filter to find the document by _id
                    new Document("$set", new Document("balance", getBalance())) // Add/Update the Goal field
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

        Balance test0 = new Balance(usr,psw,0.0);
  //      test0.setBalance();
        test0.addBalance();

        test.close();
    }
    
}
