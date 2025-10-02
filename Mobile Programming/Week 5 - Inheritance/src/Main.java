import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("Vehicle");
        Vehicle truck = new Truck("Truck");
        Vehicle e_truck = new E_Truck("E-Truck");

        vehicle.refuel();
        truck.refuel();
        e_truck.refuel();
        ((E_Truck) e_truck).charge();

        System.out.println(vehicle);
        System.out.println(truck);
        System.out.println(e_truck);
    }
}

class Vehicle {
    private String plate;

    public Vehicle(String plate) {
        this.plate = plate;
    }

    public void refuel() {
        System.out.println("* Plate " + plate + "\n- [FUEL] Gasoline");
    }

    public String getPlate() {
        return plate;
    }

    @Override
    public String toString() {
        return this.getPlate();
    }
}

class Truck extends Vehicle {
    public Truck(String plate) {
        super(plate);
    }

    @Override
    public void refuel() {
        System.out.println("* Plate " + this.getPlate() + "\n- [FUEL] Diesel");
    }
}

class E_Truck extends Truck {
    public E_Truck(String plate) {
        super(plate);
    }

    @Override
    public void refuel() {
        System.out.println("* Plate " + this.getPlate() + "\n- [FUEL] Electric");
    }

    public void charge() {
        System.out.println("* Plate " + this.getPlate() + "\n- [STATUS] Charging...");
    }
}