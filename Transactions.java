package com.pocketwatching.app;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class Transactions extends newAccount{

    private static Double Income;
    private static Double Balance;
    private static HashMap<String, Double> list = new HashMap<>();
    private static Double Goal;
    public static Double totalExpense;

    public Transactions(String user, String pass){
        super(user, pass);
    }

    public static Double getIncome(){
        return Transactions.Income;
    }
    public static Double getBalance(){
        return Transactions.Balance;
    }

    public static Double getGoal(){
        return Transactions.Goal;
    }

    public static Double getTotalExpense(){
        return Transactions.totalExpense;
    }





    public static void getInformation(){
        MongoCollection<Document> collection = User.getCollection();

        // Query the collection for a document where 'username' matches
        Document userDoc = collection.find(new Document("username", getUser())).first();

        if(userDoc != null){
            Transactions.Income = (Double) userDoc.get("income");
            Transactions.Balance = (Double) userDoc.get("balance");
            Transactions.totalExpense = (Double) userDoc.get("TotalExpense");
            Transactions.Goal = (Double) userDoc.get("Goal");

            Document expenses = (Document) userDoc.get("Expenses");

            for (String category : expenses.keySet()) {
                Object value = expenses.get(category);

                if (value instanceof Number) {
                    Transactions.list.put(category, ((Number) value).doubleValue());
                } else {
                    System.out.println("Skipping non-numeric value for category: " + category);
                }
            }
        }
      //  close();
    }

    public static HashMap getList() {
        return Transactions.list;
    }


    public String toString(){
        System.out.println(Transactions.Income);
        System.out.println(Transactions.Balance);
        System.out.println(list.toString());
        System.out.println(Transactions.Goal);

        return "";
    }

    public static Double getSavingRatePercentage(){
        Double netSaving = Transactions.Income - Transactions.totalExpense;
        Double savingRate = (netSaving/Income)*100;
        return savingRate;
    }

    public static Double getNetSavings(){
        Double netSaving = (Transactions.Balance + Transactions.Income) - Transactions.totalExpense;
        return netSaving;
    }


    public static int RemaingAmount(){
        int remain = (int) (Balance - Goal);
        return remain;
    }

    public static int RemaingAmountPercentage(){
        int ActualSavings = (int) ((Income + Balance) - totalExpense);
        int per = (int) (ActualSavings/Goal)*100;
        return per;
    }
//mvn exec:java -Dexec.mainClass="com.pocketwatching.app.Transactions"
    public static void main(String[] args) {
        newAccount test = new newAccount("rameez", "Pak1$tan");

        Transactions test0 = new Transactions(test.getUser(), getPassword());



       // test0.setTotalIncome();
        test0.getInformation();
      //  test0.toString();
      //  System.out.println("Remaining Amount: " + test0.RemaingAmount());
       // System.out.println("Remain AMount Percentage: " + test0.RemaingAmountPercentage()+ "%");
        System.out.println("Net Savings: " + test0.getNetSavings());
       //System.out.println("This is the balancee: " + test0.Balance);
       //System.out.println("This is the total income (Monthly): " + test0.Income);
       System.out.println("Saving Percentage: " + test0.getSavingRatePercentage() + "%");
       
    }



    
}
