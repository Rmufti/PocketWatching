package com.pocketwatching.app;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


//mvn exec:java -Dexec.mainClass="com.pocketwatching.app.tester"
public class tester {
    public static void main(String[] args) {
        // MongoDB URI (replace this with your actual MongoDB URI if needed)
        String uri = "mongodb+srv://rameezmufti:admin@cluster0.wfdzu.mongodb.net/\n" + //
                        "";  // Default localhost URI

        // Create a MongoDB client and connect to the server
        MongoClientURI clientUri = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientUri);

        // Get a database (in this case, we're using a test database)
        MongoDatabase database = mongoClient.getDatabase("test0");

        // Print the list of databases
        System.out.println("Databases available: ");
        for (String dbName : mongoClient.listDatabaseNames()) {
            System.out.println(dbName);
        }

        // Close the connection
        mongoClient.close();
    }
} 
