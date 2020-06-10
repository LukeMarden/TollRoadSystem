import java.util.ArrayList;


public class TollRoad {
    private ArrayList<CustomerAccount> CustomerAccount = new ArrayList<CustomerAccount>();
    private double moneyMade;

    public TollRoad(ArrayList<CustomerAccount> CA) {
        this.CustomerAccount = CA; //This sets the Customer Arraylist
        this.moneyMade = 0; //This sets the moneymade to a deafult of 0
    }

    public void addCustomer(CustomerAccount customerAccount) {
        CustomerAccount.add(customerAccount);
    } //this adds a customer account to the arraylist of customer accounts


    public CustomerAccount findCustomer(String regNo) throws CustomerNotFoundException { //this is used to find a customer based on their registration plate
        for (CustomerAccount CA : CustomerAccount) //This for loop searches through all the customer accounts for a customer account with a matching reg
            if (CA.getVehicle().getLicencePlate().equals(regNo))
                if (CA == null) {
                    throw new CustomerNotFoundException(regNo); //if the reg isnt found a CustomerNotFoundException is thrown
                }
                else {
                    return CA; //if it is found the whole customer account is returned
                }



        return null;


    }

    public void chargeCustomer(String regNo) throws InsufficientAccountBalanceException, CustomerNotFoundException { // this is used to charge the customer for a trip
            CustomerAccount found; // this stores the customer account that is returned in find customer
            double bBalance; // this stores the balance before the trip is made
            found = findCustomer(regNo); //this calls the findcustomer method with a registration plate and stores the result in the found variable
            if (found == null) { //throws CustomerNotFoundException if customer isn't found
                throw new CustomerNotFoundException(regNo);
            }
            else {
                bBalance = found.getAccountBalance(); //this works out the account balance before the transaction and stores it
                found.makeTrip(); //  this works out how much the trip is going to cost
                if (bBalance < found.makeTrip()) { // if the balance is less than the trip of the cost, then a InsufficientAccountBalanceException is thrown
                    throw new InsufficientAccountBalanceException(regNo);
                }
                else {
                    found.setAccountBalance(bBalance - found.makeTrip());//this deducts the trip cost from the account balance

                    moneyMade = moneyMade + found.makeTrip(); //this adds the cost of the trip to the moneymade
                }
            }


    }

    public double getMoneyMade() { return moneyMade; }
}
