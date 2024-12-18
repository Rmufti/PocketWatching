package com.pocketwatching.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Expenses extends newAccount {

    private static HashMap<String, Double> expenses;
    private static Double totalExpense;

    public Expenses(String username, String psw, HashMap<String, Double> mp) {
        super(username, psw);
        Expenses.expenses = mp;
        Expenses.totalExpense = 0.0;
    }

    public static Double getTotalExp() {
        Double total = 0.0;
        // Document expensesDocument = new Document();
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            total += entry.getValue();
        }
        Expenses.totalExpense = total;
        return total;
    }

    public static void setTotalExp(Double exp) {
        Expenses.totalExpense = exp;
    }

    public static HashMap getList() {
        return Expenses.expenses;
    }

    // Allows user to add 5 items to the list from the get go.
    public static void setList(HashMap<String, Double> exp) {
        Expenses.expenses = exp;
    }

    public static void addListToDoc() {
        MongoCollection<Document> collection = User.getCollection();

        // Query the collection for a document where 'username' is the given username
        Document userDoc = collection.find(new Document("username", getUser())).first();

        if (userDoc != null) {
            // Step 3: Convert the HashMap into a Document
            Document expensesDocument = new Document();
            for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                expensesDocument.append(entry.getKey(), entry.getValue());
            }
            // Step 4: Use $set to add the entire HashMap into the document
            collection.updateOne(userDoc, new Document("$set", new Document("Expenses", expensesDocument)));

            Double exp = getTotalExp();
            setTotalExp(exp);
            Document update = new Document("$set", new Document("TotalExpense", getTotalExp()));
            collection.updateOne(userDoc, update);
        } else {
            System.out.println("No document found with the given _id.");
        }

    }


    // update document

    ////// mvn exec:java -Dexec.mainClass="com.pocketwatching.app.Expenses"
    public static void main(String[] args) {
      /*   String usr = "carman";
        String psw = "Batm@n020303";

        // Create a newAccount object
        newAccount test = new newAccount(usr, psw);

        Expenses test0 = new Expenses(usr, psw, 0.0);
        test0.addToExpenseList();
        System.out.println(test0.expenses.toString());
        test0.updateList();
        System.out.println(test0.expenses.toString());

        test.close();*/

    }
}
