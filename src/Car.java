public class Car extends Vehicle{
    private int numberOfSeats;
    public Car(int nSeats, String reg, String make) {
        super(reg, make);
        this.numberOfSeats = nSeats;

    }

    @Override //this calculates the cost of a trip based on the vehicles information
    public int calculateBasicTripCost() {
        if (numberOfSeats < 6) {
            return 500;
        }
        else {
            return 600;
        }
    }

    @Override
    public String toString() {
        return "Licence Plate:" + licencePlate + " Vehicle Make:" + vehicleMake + " Number Of Seats:" + numberOfSeats;
    }

    public void setNumberOfSeats(int seats) {
        numberOfSeats = seats;
    }

    public int getNumberOfSeats() { return numberOfSeats; }
}
