public class Practice {
    public static void main(String[] args) {
        Truck truck = new Truck("Truck");
        E_Truck e_truck = new E_Truck("E_Truck");

        truck.refuel();
        e_truck.refuel();
        e_truck.charge();
    }
}

interface Vehicle {
    public abstract void refuel();
}

interface Electric {
    public abstract void charge();
}

class Truck implements Vehicle {
    private String plate;

    public Truck(String plate) {
        this.plate = plate;
    }

    @Override
    public void refuel() {
        System.out.println("* Plate: " + this.plate + "\n- [FUEL] Diesel");
    }
}

class E_Truck implements Vehicle, Electric {
    private String plate;

    E_Truck(String plate) {
        this.plate = plate;
    }

    @Override
    public void refuel() {
        System.out.println("* Plate: " + this.plate + "\n- [FUEL] Electric");
    }

    @Override
    public void charge() {
        System.out.println("* Plate: " + this.plate + "\n- [STATUS] Charging...");
    }
}