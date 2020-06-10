public class Truck extends Vehicle{
    private int numTrailers;
    public Truck(int nTrailers, String reg, String make) {
        super(reg, make);
        this.numTrailers = nTrailers;
    }

    @Override
    public int calculateBasicTripCost() { //this calculates the cost of a trip based on the vehicles information
        if ( numTrailers == 0 || numTrailers == 1) {
            return 1250;
        }
        else {
            return 1500;

        }
    }

    @Override
    public String toString() {
        return "Licence Plate:" + licencePlate + " Vehicle Make:" + vehicleMake + " Number Of Trailers: " + numTrailers;
    }

    public void setNumTrailers(int nTrailers) {
        numTrailers = nTrailers;
    }


    public int getNumTrailers() {
        return numTrailers;
    }

    
}
