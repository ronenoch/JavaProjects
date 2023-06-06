import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter first number:");
            String first_big_number_string = sc.nextLine();
            System.out.println("Please enter second number:");
            String second_big_number_string = sc.nextLine();
            try {
                BigInt first_number = new BigInt(first_big_number_string);
                BigInt second_number = new BigInt(second_big_number_string);
                System.out.println("plus operation: ");
                System.out.println(first_number.plus(second_number));
                System.out.println("minus operation: ");
                System.out.println(first_number.minus(second_number));
                System.out.println("multiply() operation: ");
                System.out.println(first_number.multiply(second_number));
                System.out.println("divide() operation: ");
                System.out.println(first_number.divide(second_number));
            }
            catch (Exception e) {
                System.out.println("Please try again. \n");
            }

        }

    }
}