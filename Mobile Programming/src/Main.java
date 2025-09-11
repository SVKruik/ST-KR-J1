import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter how many Fibonacci series to be shown:");
        count = input.nextInt();

        if (count < 0) {
            System.out.println("Error: Enter a positive integer.");
        } else {
            for (int i = 0; i < count; i++) {
                    System.out.print(fibonacciLoop(i) + " ");
            }
        }
    }

    static int fibonacciLoop(int number) {
        int f_1 = 0, f_2 = 1, f_n = 0;

        if (number < 0) {
            System.out.println("Error: Enter a positive integer.");
            return -1;
        } else if (number == 0) {
            return f_1;
        } else if (number == 1) {
            return f_2;
        } else {
            for (int i = 2; i <= number; i++) {
                f_n = f_1 + f_2;
                f_1 = f_2;
                f_2 = f_n;
            }
            return f_n;
        }
    }
}