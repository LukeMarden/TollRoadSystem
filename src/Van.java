public class Van extends Vehicle{
    private double payload;
    public Van(double weight, String reg, String make) {
        super(reg, make);
        this.payload = weight;
    }

    @Override
    public int calculateBasicTripCost() { //this calculates the cost of a trip based on the vehicles information
        if (payload <= 600) {
            return 500;
        }
        else if (payload > 600 && payload <= 800) {
            return 750;
        }
        else {
            return 1000;
        }
    }

    @Override
    public String toString() {
        return "Licence Plate:" + licencePlate + " Vehicle Make:" + vehicleMake + " Payload(Kg):" + payload;
    }

    public void setPayload(double weight) {
        payload = weight;
    }

    public double getPayload() {
        return payload;
    }

}
