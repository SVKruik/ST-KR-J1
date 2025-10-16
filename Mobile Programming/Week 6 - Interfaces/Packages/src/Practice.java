import business.Truck;

public class Practice {
    public static void main(String[] args) {
        Truck truck = new Truck("Truck");

        truck.refuel();
        truck.setPlate("Truck 1");
        truck.extendWarranty(20);
    }
}
