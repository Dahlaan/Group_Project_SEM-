package facade;

import java.util.ArrayList;
import java.util.List;


public class Controller {

    // -----------------------------COMMON METHODS----------------------------------

    //To change the number of decimal digits
    //How to use: 'value' is your original number input with all decimal digits,
    //and 'decimalPoint' is the number of decimal digits you would like to have.
    // e.g. if you write 'changeDecimal(199.999, 1) you'll get 199.9


    public double changeDecimal(double value, int decimalPoint) {

        value = value * Math.pow(10, decimalPoint);
        value = Math.floor(value);
        value = value / Math.pow(10, decimalPoint);

        return value;
    }

    //-----------------------------------FOR ITEMS-----------------------------------

    private static ArrayList<Item> itemList = new ArrayList<>();

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    //Create a new item and add it to itemList
    public String createItem(String itemID, String itemName, double price) {

        if (containsItem(itemID)) {
            return "Invalid data for item.";
        } else if (itemID.isEmpty()) {
            return "Invalid data for item.";
        } else if (itemName.isEmpty()) {
            return "Invalid data for item.";
        } else if (price == 0 || price < 0) {
            return "Invalid data for item.";
        } else {
            price = changeDecimal(price, 2);
            Item item = new Item(itemID, itemName, price);
            itemList.add(item);
            return "Item " + itemID + " was registered successfully.";
        }
    }

    //Check if item is already in the list by using ID
    public boolean containsItem(String itemID) {

        for (int i = 0; i < getItemList().size(); i++) {
            if (getItemList().get(i).getID().equals(itemID)) {
                return true;
            }
        }
        return false;
    }

    //Find item by using ID
    public Item findItem(String itemID) {

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getID().equals(itemID)) {
                return itemList.get(i);
            }
        }
        return null;
    }

    public String removeItem(String itemID) {

        if (containsItem(itemID)) {
            Item itemToRemove = findItem(itemID);
            itemList.remove(itemToRemove);
            return "Item " + itemID + " was successfully removed.";
        } else {
            return "Item " + itemID + " could not be removed.";
        }
    }

    public String updateItemName(String oldID, String newID) {

        if (!containsItem(oldID)){
            return "Item " +oldID + " was not registered yet.";
        } else if (oldID.isBlank() || !containsItem(oldID)) {
            return "Invalid data for item.";
        } else {
            Item foundItem = findItem(oldID);
            foundItem.setItemName(newID);
            return "Item " + oldID + " was updated successfully.";
        }
    }

    public String updateItemPrice(String itemID, double newPrice) {

        if (!containsItem(itemID)){
            return "Item " +itemID + " was not registered yet.";
        } else if (newPrice < 0 || newPrice == 0) {
            return "Invalid data for item.";
        } else {
        Item foundItem = findItem(itemID);
        foundItem.setItemPrice(newPrice);
        return "Item " + itemID + " was updated successfully.";
        }
    }


    public double buyItem(String itemID, int amount) {

        double totalPrice = 0.0;

        if (!containsItem(itemID)) {
            return -1;
        } else {
            double itemPrice = findItem(itemID).getItemPrice();
            if (amount < 4 || amount == 4) {
                totalPrice = itemPrice * amount;
            } else {
                totalPrice = 4 * itemPrice + ((amount - 4) * (itemPrice * (1.0 - 0.3)));
            }
            Transaction newTransaction = new Transaction(itemID, amount, totalPrice);
            transactionHistoryList.add(newTransaction);

            return changeDecimal(totalPrice, 2);

        }
    }

    public String printItem(String itemID) {

        if (containsItem(itemID)) {
            Item foundItem = findItem(itemID);
            return foundItem.toString();
        } else {
            return "Item " + itemID + " was not registered yet.";
        }
    }


    public String printAllItems() {

        if (itemList.size() == 0) {
            return "No items registered yet.";
        } else {
            String allItem = "All registered items:\n";

            for (Item item : itemList) {
                allItem += item + "\n";
            }
            return allItem;
        }

    }

    //Mijin: I don't really use this code. Was it one of you who put it here? Otherwise I'll remove it.
    public String getItemID (String itemID){
        String ID = findItem(itemID).getID();
        return itemID;
    }


    public boolean containsItemID(String itemID) {

        for (int i = 0; i < getItemList().size(); i++) {
            if (getItemList().get(i).getID().equals(itemID)) {
                return true;
            }
        }
        return false;
    }


    // ----------------------------------------------------------------------------------------

    public static Review findItemComment(String itemComment) {

        for (Review review : reviewList) {
            if (review.getID().equals(itemComment)) {
                return review;
            }
        }
        return null;
    }

    public boolean containsItemComment(String itemComment) {

        for (int i = 0; i < getReviewList().size(); i++) {
            if (getReviewList().get(i).getID().equals(itemComment)) {
                return true;
            }
        }
        return false;
    }

    public static String getItemGrade(String itemID){
        double itemGrade = findItemGrade(itemID).getItemGrade();
        return String.valueOf(itemGrade);
   }

    public static Review findItemGrade(String itemGrade) {

        for (Review review : reviewList) {
            if (review.getID().equals(itemGrade)) {
                return review;
            }
        }
        return null;
    }

    public boolean containsItemGrade(String itemGrade) {

        for (int i = 0; i < getReviewList().size(); i++) {
            if (getReviewList().get(i).getID().equals(itemGrade)) {
                return true;
            }
        }
        return false;
    }


    public static String getItemName(String itemID) {

        String itemName = findItemName(itemID).getItemName();

        return itemName;
    }


    public boolean containsItemName(String itemName) {

        for (int i = 0; i < getReviewList().size(); i++) {
            if (getReviewList().get(i).getID().equals(itemName)) {
                return true;
            }
        }
        return false;
    }



    public static Item findItemName(String itemName) {

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getID().equals(itemName)) {
                return itemList.get(i);
            }
        }
        return null;
    }

    public static String getItemPrice(String itemID) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getID().equals(itemID)) {
                return String.valueOf(itemList.get(i).getItemPrice()); //
            }
        }
        return null;


    }

    public boolean containsItemPrice(String itemPrice) {

        for (int i = 0; i < itemList.size(); i++) {
            if (getReviewList().get(i).getID().equals(itemPrice)) {
                return true;
            }
        }
        return false;
    }

    public Item findItemPrice(String itemPrice) {

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getID().equals(itemPrice)) {
                return itemList.get(i);
            }
        }
        return null;
    }

// -------------------------------------- FOR REVIEWS ---------------------------------------------------

    //does it have to be static? I know TA mentioned this but I didn't get why -Mijin
    private static ArrayList<Review> reviewList = new ArrayList<>();


    public static ArrayList<Review> getReviewList() {
        return reviewList;
    }

    private static ArrayList<String> commentsList = new ArrayList<>();

    public static ArrayList<String> getcommentsList() {
        return commentsList;
    }



//Create Review 3.1
    public String reviewItem(String ID, String reviewComment, int reviewGrade) {

        if (ID.isEmpty()) {
            return "ID needed to review item: ";
        } else if (!containsItem(ID)){
            return "Item " + ID + " was not registered yet.";

        } else if (reviewGrade < 1.0 || reviewGrade > 5.0){
            return "Grade values must be between 1 and 5.";
        } else {

            Review review = new Review(ID, reviewComment, reviewGrade);
            reviewList.add(review);
            return "Your item review was registered successfully."; //Testing issue
        }
    }

    public static List<String> getItemComments(String itemID) { //User Story 3.5


        if (containsReview(itemID)) {
            for (int i = 0; i < reviewList.size(); i++) {
                if (getReviewList().get(i).getID().equals(itemID)) {
                    commentsList.add(getReviewList().get(i).getItemComment());

                    /*}for (String review : commentsList) {
            System.out.println(itemID);
        }*/
                }
            }
        }
        return getcommentsList();
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        if (reviewList.size() == 0) {
            System.out.println("No reviews have been added: "+ System.lineSeparator());
        } else {
            System.out.println("Index ");
            for (Review review : reviewList) {
                System.out.print("____________________________" + System.lineSeparator()+ review + System.lineSeparator());

            }
        }
        return "";
    }


    public String printAllReviews() {
        if (reviewList.size() == 0) {
            System.out.println("No items registered yet.");
        } else {
            System.out.println("All registered items:");
            for (Review review : reviewList) {
                System.out.println(review);
            }
        }
        return "";


    }
//Temp
    public static String printAllcomments() {
        if (commentsList.size() == 0) {
            System.out.println("No items registered yet.");
        } else {
            System.out.println("All registered items:");
            for (String review : commentsList) {
                System.out.println(review);
            }
        }
        return "";


    }

    public static boolean containsReview(String itemID) {

        for (int i = 0; i < reviewList.size(); i++) {
            if (getReviewList().get(i).getID().equals(itemID)) {
                return true;
            }
        }
        return false;
    }
    public static Review findReview(String itemID) {

        for (int i = 0; i < reviewList.size(); i++) {
            if (reviewList.get(i).getID().equals(itemID)) {
                return reviewList.get(i);
            }
        }
        return null;
    }


  //_______________________________Mean Grade of Review____________________________________________

    /*public String getItemMeanGrade() {
          if (reviewList.size() == 0) {
              System.out.println("No reviews have been added:" + System.lineSeparator());
          } else {
              System.out.println("Grade of item: ");
          }
          for (Review reviewGrade : reviewList) {
              System.out.print( reviewGrade + System.lineSeparator());

          }
          return "";
      }*/

// --------------------------------------- FOR TRANSACTION HISTORY ---------------------------------------
    //creating a transaction
    public static ArrayList<Transaction> transactionHistoryList = new ArrayList<Transaction>();


    public double getTotalProfit() {

        double totalProfit = 0.0;
        for (int i = 0; i < transactionHistoryList.size(); i++)
            totalProfit += transactionHistoryList.get(i).getProfit();

        return totalProfit;
    }

    public int getTotalUnitsSold() {

        int totalUnitsSold = 0;
        for (int i=0; i<transactionHistoryList.size(); i++) {
            totalUnitsSold += transactionHistoryList.get(i).getUnitsSold();
        }
        return totalUnitsSold;
    }

    public double getProfit(String itemID) {

        double sumProfit = 0.0;

        for (int i = 0; i < transactionHistoryList.size(); i++) {
            if (transactionHistoryList.get(i).getID().equals(itemID)) {
                sumProfit = sumProfit + transactionHistoryList.get(i).getProfit();
            } else {
                System.out.println("No transactions have been registered for item " + itemID + " yet.");
            }

        }
        return sumProfit;
    }

        //to contain transaction for specific item ... (4.3)
        public boolean containsTransaction (String itemID)
    {

        for (int i = 0; i < transactionHistoryList.size(); i++) {
            if (transactionHistoryList.get(i).getID().equals(itemID)) {
                return true;
            }
        }
            return false;
    }

    /*
        User Story 4.2 - Retrieve purchase data for a specific item
        I want to retrieve different data about transactions of a specific item in order to get an overview of how profitable the item is.
        For a registered item ID specified by the user, the system should print three different summary data:
        Sum of all the profit made by purchasing that specific item.
        Sum of all units sold of that specific item.
        Total number of transactions registered for the specific item.
        If the item ID has not been registered or if no transaction for that item has been made, the system should return the value zero (0) for all operations above.

    */
/*
    // US 4.2 -Retrieve purchase data for a specific item

    public String printItemTransactions(String itemID) {

        double sumProfit = 0.0;
        int sumUnitSold = 0;

        for (int i=0; i < transactionHistoryList.size(); i++) {
            if (transactionHistoryList.get(i).getID().equals(itemID)) {
                sumProfit = sumProfit + transactionHistoryList.get(i).getProfit();
                sumUnitSold = sumUnitSold + transactionHistoryList.get(i).getUnitsSold();
            } else {
                return "No transactions have been registered for item " + itemID + " yet.";
            }
            return "Transactions for item: " + itemID.toString() +

        }

        return "";
    }
*/

    /*
        //get specific item transactions
    public static String getPurchasePrice(String itemID) {
        for (int i = 0; i < transactionHistoryList.size(); i++);
        if (transactionHistoryList.get(i).getID().equals(itemID)) {
            return String.valueOf(transactionHistoryList.get(i).getItemPurchase());
        }
        return "Can't find";
    }
*/


    public static Transaction findItemTransactionHistory(String userID) {
        for (int i = 0; i < transactionHistoryList.size(); i++) {
            if (transactionHistoryList.get(i).getID().equals(userID)) {
                return transactionHistoryList.get(i);
            }
        }
        return null;
    }



    //4.4  3. print total number of transactions
    public int getTotalTransactions() {

        int totalTransactions = transactionHistoryList.size();
        return totalTransactions;

        /*if (totalTransactions == 0) {
            "Total purchases made: 0 transactions");
        } else {
            for (int i = 0; i < totalTransactions; i++); {
            System.out.println("Total purchases made: <" + totalTransactions +"> transactions\n");
            }
        }
        return -1;
   */ }




    //   4.5 - Print all transactions
    public String printAllTransactions() {

        if (transactionHistoryList.size() == 0) {
            System.out.println("All purchases made:\n" +
                    "Total profit: 0.00 SEK\n" +
                    "Total items sold: 0 units\n" +
                    "Total purchases made: 0 transactions\n" +
                    "----------------------------------------\n" +
                    "----------------------------------------");
        } else {


            System.out.println("All purchases made:\n" +
                    "Total profit: total profit SEK\n" +
                    "Total items sold: total units units\n" +
                    "Total purchases made: total transactions transactions");

            for (Transaction transaction : transactionHistoryList) {
                System.out.println(transaction.toString());
            }

            System.out.println("------------------------------------\n" + "\n");
        }
        return "";

    }
        /*

    //-----------------------------------FOR Employee-----------------------------------

    public static ArrayList<Item> employeeList = new ArrayList<>();

    public ArrayList<Item> getEmployeeList() {
        return employeeList;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {

        return "";
    }
*/
}

