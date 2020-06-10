public class CustomerAccount implements Comparable<CustomerAccount>{
    private String firstName;
    private String secondName;
    private Vehicle Vehicle;
    private double accountBalance;
    private enum DiscountType {NONE, STAFF, FRIENDS_AND_FAMILY}
    private DiscountType discountType;



    public CustomerAccount(String fName, String sName, Vehicle v, double aBalance) {

        this.firstName = fName; //Sets the first name of the customer account
        this.secondName = sName; //Sets the surname name of the customer account
        this.Vehicle = v; //Sets the Vehicle of the customer account
        this.accountBalance = aBalance; //Sets the balance of the customer account
        this.discountType = DiscountType.NONE; //Sets the discount type to the default of NONE

    }

    public int compareTo(CustomerAccount other) {
        return this.Vehicle.getLicencePlate().compareTo(other.Vehicle.getLicencePlate());

    }

    public void activateStaffDiscount() {
        discountType = DiscountType.STAFF;
    } //This activates the STAFF discount


    public void activateFriendsAndFamilyDiscount() { //This activates the FRIENDS_AND_FAMILY discount but only if the discount type is NONE to begin with
        if (discountType == DiscountType.NONE) {
            discountType = DiscountType.FRIENDS_AND_FAMILY;
        }
        else {
            return;
        }
    }

    public void deactivateDiscount() {
        discountType = DiscountType.NONE;
    } //This sets the discount type to NONE

    public void addFunds(int add) {
        accountBalance = accountBalance + add;
    } //this adds funds to the balance variable of the object

    public double makeTrip() { //This works out the cost of the trip based on the discount type and vehicle info
        if (discountType == DiscountType.STAFF) {
            return this.Vehicle.calculateBasicTripCost() * 0.5;
        }
        else if (discountType == DiscountType.FRIENDS_AND_FAMILY) {
            return this.Vehicle.calculateBasicTripCost() * 0.9;
        }
        else {
            return this.Vehicle.calculateBasicTripCost();
        }
    }
    public String getFirstName() { return firstName; }

    public String getSecondName() { return secondName; }

    public Vehicle getVehicle() { return Vehicle; }

    public double getAccountBalance() { return accountBalance; }

    public void setAccountBalance (double add) {
        accountBalance = accountBalance + add;
    }

    public String toString() {
        return ("Name:" + firstName + " "  + secondName + " " + Vehicle.toString() + " Balance:" + accountBalance + " Discount Type:" + discountType);
    }

}
