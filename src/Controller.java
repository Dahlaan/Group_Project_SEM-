import java.util.ArrayList;

public class Controller {

    // -----------------------------COMMON METHODS----------------------------------

    //To change the number of decimal digits
    //How to use: 'value' is your original number input with all decimal digits,
    //and 'decimalPoint' is the number of decimal digits you would like to have.
    // e.g. if you write 'changeDecimal(199.999, 1) you'll get 199.9
    public double changeDecimal(double value, int decimalPoint)
    {
        // Using the pow() method
        value = value * Math.pow(10, decimalPoint);
        value = Math.floor(value);
        value = value / Math.pow(10, decimalPoint);

        return value;
    }

    //-----------------------------------FOR ITEMS-----------------------------------

    private ArrayList<Item> itemList = new ArrayList<>();

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    //Create a new item and add it to itemList
    public String createItem(String itemID, String itemName, double unitPrice) {

        unitPrice = changeDecimal(unitPrice, 2);
        Item item = new Item(itemID, itemName, unitPrice);
        itemList.add(item);
        return "";
    }

    public String updateItemName(String IDInput, String newNameInput) {

        Item foundItem = findItem(IDInput);
        foundItem.setItemName(newNameInput);
        return "";
    }

    public String updateItemPrice(String IDInput, double newPriceInput) {

        Item foundItem = findItem(IDInput);
        foundItem.setItemPrice(newPriceInput);
        return "";
    }

    //Remove item
    public String removeItem(String itemID) {

        if (this.containsItem(itemID)) {
            Item itemToRemove = this.findItem(itemID);
            itemList.remove(itemToRemove);
            return "Item <" + itemID + "> was successfully removed.";
        } else {
            return "Item <" + itemID + "> could not be removed.";
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

    //Find index for user typed ID
    public Item findItem(String userID) {

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getID().equals(userID)) {
                return itemList.get(i);
            }
        }
        return null;
    }


    public double buyItem(String itemID, int amount) {

        double totalPrice;

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

            return totalPrice;

        }
    }

    public String printItem(String itemID) {

        if (containsItem(itemID)) {
            Item foundItem = findItem(itemID);
            System.out.println(foundItem);
        } else {
            System.out.println("Item <" + itemID + " > was not registered yet.");
        }
        return "";
    }


    public String printAllItems() {

        if (itemList.size() == 0) {
            System.out.println("No items registered yet.");
        } else {
            System.out.println("All registered items:");
            for (Item item : itemList) {
                System.out.println(item);
            }
        }
        return "";
    }

// -------------------------------------- FOR REVIEWS ---------------------------------------------------

    //does it have to be static? I know TA mentioned about this but I didn't get why -M
    static ArrayList<Review> reviewList = new ArrayList<>();

    public ArrayList<Review> getReviewList() {
        return reviewList;
    }

    public String createReview(String ID, String reviewComment, double reviewGrade) {
        Review review = new Review(ID, reviewComment, reviewGrade);
        reviewList.add(review);
        return "";

        // No need to check for duplicate review since different reviewers can enter same values.
    }



    public static String printAllReview() {
        if (reviewList.size() == 0) {
            System.out.println("No reviews have been added: "+ System.lineSeparator());
        } else {
            System.out.println("All registered reviews: ");
            for (Review review : reviewList) {
                System.out.print("____________________________" + System.lineSeparator()+ review + System.lineSeparator());

            }
        }return "";
    }


    public boolean containsReview(String reviewID) {

        for (int i = 0; i < reviewList.size(); i++) {
            if (getReviewList().get(i).getID().equals(reviewID)) {
                return true;
            }
        }
        return false;
    }
    public static Review findReview(String reviewID) {

        for (int i = 0; i < reviewList.size(); i++) {
            if (reviewList.get(i).getID().equals(reviewID)) {
                return reviewList.get(i);
            }
        }
        return null;
    }


    public String printAnReview() { // User Story 3.3
        {
            if (reviewList.size() == 0) {
                System.out.println("No reviews have been added: " + System.lineSeparator());
            } else {
                System.out.println("Reviews of item: ");
                for (Review review : reviewList) {
                    System.out.print("____________________________" + System.lineSeparator() + review + System.lineSeparator());
                }
            }
            return "";
        }
    }

  /*_______________________________Mean Grade of Review____________________________________________
/*    public boolean containsGrade(String reviewGrade) {

        for (int i = 0; i < reviewList.size(); i++) {
            if (reviewList.get(i).getGrade(double).equals(reviewGrade)) {
                return true;
            }
        }
        return false;
    }
    public Review findGrade(String reviewGrade) {

        for (int i = 0; i < reviewList.size(); i++) {
            if (reviewList.get(i).getGrade().equals(reviewGrade)) {
                return reviewList.get(i);
            }
        }
        return null;
    }

      public String meanReview() {
          if (reviewList.size() == 0) {
              System.out.println("No reviews have been added:" + System.lineSeparator());
          } else {
              System.out.println("Reviews of item: ");
          }
          for (Review reviewGrade : reviewList) {
              System.out.print("____________________________" + System.lineSeparator() + reviewGrade + System.lineSeparator());

          }
          return "";
      } */

// --------------------------------------- FOR TRANSACTION HISTORY ---------------------------------------
    //creating a transaction but I still didn't figure out how to link it, so that when an item is bought it would be automatically created...
    private ArrayList<Transaction> transactionHistoryList = new ArrayList<Transaction>();



    public void createTransaction( String ID, int amount, double purchasePrice){

        Transaction transaction = new Transaction(ID, amount, purchasePrice);
        transactionHistoryList.add(transaction);
    }



    //to contain transaction for specific item ... (4.3)
    public boolean containsTransaction(String itemID) {
        for (int i = 0; i < transactionHistoryList.size(); i++) {
            if (transactionHistoryList.get(i).getID().equals(itemID)) {
                return true;
            }
        }
        return false;
    }


    //   4.5 - Print all transactions
    public String printAllTransactions() {

        if (transactionHistoryList.size() ==0) {
            System.out.println("No registered transactions.");
        } else {
            System.out.println("All registered transactions:");
            for (Transaction transaction : transactionHistoryList) {
                System.out.println(transaction);
            }
        }
        return "";

    }

}

