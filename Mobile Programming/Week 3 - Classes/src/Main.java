import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vehicle car = new Vehicle("ABC-1234", 50);

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter distance to drive (km): ");
            double distance = scanner.nextDouble();
            car.drive(distance);
        } while (car.getGas_liters() > 0);
    }
}

class Vehicle {
    String plate;
    double gas_per_distance = 0.1, gas_liters = 0;

    /**
     * Constructor for Vehicle class.
     * @param plate The vehicle's license plate.
     * @param gas_liters The initial amount of gas in liters.
     */
    public Vehicle(String plate, double gas_liters) {
        this.plate = plate;
        this.gas_liters = gas_liters;
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
}