public abstract class Vehicle {


    protected String licencePlate;
    protected String vehicleMake;


    public Vehicle(String reg, String make) {
        this.licencePlate = reg;
        this.vehicleMake = make;

    }

    public abstract int calculateBasicTripCost();

    public void setLicencePlate(String p) {
        licencePlate = p;
    }

    public void setVehicleMake(String m) {
        vehicleMake = m;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }


    public String toString() {
        return "Licence Plate:" + licencePlate + " Vehicle Make:" + vehicleMake;
    }
}
