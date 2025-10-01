import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vehicle car = new Vehicle("ABC-1234", 50);

//        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter distance to drive (km): ");
            double distance = scanner.nextDouble();
            car.drive(distance);
//        } while (car.getGas_liters() > 0);

        System.out.println("There are " + Vehicle.vehicle_count + " vehicles created.");
    }
}

class Vehicle {
    private String plate;
    private double gas_per_distance, gas_liters;
    static int vehicle_count = 0;

    /**
     * Default constructor for Vehicle class.
     */
    public Vehicle() {
        this.plate = "XXX-0000";
        this.gas_per_distance = 0.1;
        vehicle_count++;
    }

    /**
     * Constructor for Vehicle class.
     * @param plate The vehicle's license plate.
     * @param gas_liters The initial amount of gas in liters.
     */
    public Vehicle(String plate, double gas_liters) {
        this.plate = plate;
        this.gas_liters = gas_liters;
        this.gas_per_distance = 0.1;
        vehicle_count++;
    }

    /**
     * Constructor for Vehicle class with custom gas consumption rate.
     * @param plate The vehicle's license plate.
     * @param gas_per_distance The gas consumption rate in liters per kilometer.
     * @param gas_liters The initial amount of gas in liters.
     */
    public Vehicle(String plate, double gas_per_distance, double gas_liters) {
        this.plate = plate;
        this.gas_per_distance = gas_per_distance;
        this.gas_liters = gas_liters;
        vehicle_count++;
    }

    /**
     * Drive the vehicle a certain distance.
     * @param distance The distance to drive in kilometers.
     */
    public void drive(double distance) {
        if (this.gas_liters <= 0) {
            System.out.println("No gas in the tank.");
            return;
        };

        double gas_needed = distance * this.gas_per_distance;
        if (gas_needed > this.gas_liters) {
            System.out.println("Not enough gas to drive the distance. Gas left: " + this.gas_liters + " liters.");
            return;
        }

        this.gas_liters -= gas_needed;
        System.out.printf("Driving %.2f km. Gas left: %.2f liters.%n", distance, this.gas_liters);
    }

    public double getGas_liters() {
        return gas_liters;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public double getGas_per_distance() {
        return gas_per_distance;
    }

    public void setGas_per_distance(double gas_per_distance) {
        this.gas_per_distance = gas_per_distance;
    }

    public void setGas_liters(double gas_liters) {
        this.gas_liters = gas_liters;
    }
}