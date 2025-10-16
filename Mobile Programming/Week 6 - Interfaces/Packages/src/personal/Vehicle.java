package personal;

public class Vehicle {
    protected String plate;
    protected int warranty;


    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void refuel() {
        System.out.println("* Plate: " + this.plate + "\n- [FUEL] Diesel");
    }
}
