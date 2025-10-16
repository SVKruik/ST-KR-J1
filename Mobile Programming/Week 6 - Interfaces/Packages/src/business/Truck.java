package business;

import personal.Vehicle;

public class Truck extends Vehicle {
    public Truck(String plate) {
        this.plate = plate;
    }

    public void extendWarranty(int months) {
        this.warranty += months;
    }
}
