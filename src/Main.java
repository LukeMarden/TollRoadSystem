import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private String[] detail;
    private ArrayList<CustomerAccount> customerRecords = new ArrayList<>();
    private List<String> CR  = new ArrayList();
    private CustomerAccount found;
    private TollRoad road = new TollRoad(customerRecords);
    public Main() {
        customerRecords = this.initialiseTollRoadFromFile(); //this fills the customerrecords arraylist up with customer accounts
        simulateFromFile(road); //this processes all the transactions
        System.out.println("Money Made: " + road.getMoneyMade()); //this prints how much money was made
    }
    public static void main(String[] args) {
        Main main = new Main();

    }

    public ArrayList<CustomerAccount> initialiseTollRoadFromFile() {
        try {
            String record; //this stores the whole line of data from the customerdata txt file
            Scanner fileScan, recordScan; //filescan scans in all the text from the customerdata txt file, recordscan reads all the records after they have been seperated up

            fileScan = new Scanner(new File("customerData.txt"));
            while (fileScan.hasNext()) {
                record = fileScan.nextLine();

                recordScan = new Scanner(record);
                recordScan.useDelimiter("#"); //this splits the records up into an individual record by the #


                while (recordScan.hasNext()) {
                    detail = recordScan.next().split(","); //this splits the record up by the commas

                    String type = detail[0]; //takes each bit of data and stores it in the variable it corresponds to
                    String reg = detail[1];
                    String fName  = detail[2];
                    String lName  = detail[3];
                    String make  = detail[4];
                    int vDetail = Integer.valueOf(detail[5]);
                    double balance = Integer.valueOf(detail[6]);
                    String discount = detail[7];
                    int count = 0;


                    if ("Car".equals(type)) { // if type is equal to car this is run
                        Car tempV = new Car(vDetail, reg, make); //this makes a new car object and stores it in a temporary vehicle object
                        CustomerAccount tempCA = new CustomerAccount(fName, lName, tempV, balance); //this makes a new customeraccount object based on the previous data
                        customerRecords.add(tempCA); //this adds the customeraccount to the customerrecords arraylist
                    }
                    else if ("Van".equals(type)) { // if type is equal to van this is run
                        Van tempV = new Van(vDetail, reg, make); //this makes a new van object and stores it in a temporary vehicle object
                        CustomerAccount tempCA = new CustomerAccount(fName, lName, tempV, balance);
                        customerRecords.add(tempCA);
                    }
                    else if ("Truck".equals(type)){ // if type is equal to truck this is run
                        Truck tempV = new Truck(vDetail, reg, make); //this makes a new truck object and stores it in a temporary vehicle object
                        CustomerAccount tempCA = new CustomerAccount(fName, lName, tempV, balance);
                        customerRecords.add(tempCA);
                    }
                    if ("STAFF".equals(discount)) {
                        customerRecords.get(count).activateStaffDiscount();
                        count++;
                    }
                    else if ("FRIENDS_AND_FAMILY".equals(discount)) {
                        customerRecords.get(count).activateFriendsAndFamilyDiscount();
                        count++;
                    }






                }


                System.out.println();
            }

        }
        catch(IOException ie) {
            ie.printStackTrace();

        }


        return customerRecords; // this returns the finished arraylist
    }

    public void simulateFromFile(TollRoad road) { //this method does all the transactions
        try {
            String record;
            Scanner fileScan, recordScan;

            fileScan = new Scanner(new File("transactions.txt"));
            while (fileScan.hasNext()) {
                record = fileScan.nextLine();

                recordScan = new Scanner(record);
                recordScan.useDelimiter("\\$"); //this splits the records up into an individual record by the $

                while (recordScan.hasNext()) {
                    detail = recordScan.next().split(",");

                    String transactionType = detail[0];
                    String reg = detail[1];



                    if (transactionType.equals("addFunds")) { //if transactiontype is equal to addfunds it the code will follow this path
                        double amount = Integer.valueOf(detail[2]);
                        try {
                            found = road.findCustomer(reg);
                            found.setAccountBalance(amount); //this will add the amount to accounts balance
                            System.out.println(reg + ": " + amount + " added successfully."); //if it is successful this will be printed

                        }
                        catch (CustomerNotFoundException nFound) {
                            System.out.println(reg + ": addFunds failed. CustomerAccount does not exist."); //if the account doesn't exist the exception thrown from the method will be caught here
                        }

                    }
                    else if (transactionType.equals("makeTrip")) { //if the function is maketrip, then the reg will be added to this list and completed after all addfunds are comepleted
                        CR.add(reg);

                    }
                }
                System.out.println();
                for (String reg : CR) { //this goes through every reg in the list and charges the customer
                    try {
                        road.chargeCustomer(reg); //this calls the method chargecustomer on the reg
                        System.out.println(reg + ": Trip Completed Successfully."); //if they have enough funds it will output this

                    }
                    catch (CustomerNotFoundException nFound) { // if the customer isnt found it will be caught here and this will be outputted
                        System.out.println(reg + ": MakeTrip failed. CustomerAccount does not exist.");
                    }
                    catch (InsufficientAccountBalanceException nAmount) { //if the customer doesn't have enough funds the exception will be caught here, and this will be outputted
                        System.out.println(reg + ": MakeTrip failed. Insufficient funds.");
                    }
                }
            }
        }
        catch(IOException ie) {
            ie.printStackTrace();

        }


    }



}
