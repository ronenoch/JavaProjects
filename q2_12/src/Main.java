import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        BigInt big_int_1 = new BigInt("+3647423");
        BigInt big_int_2 = new BigInt("96946549");
        BigInt big_int_3 = new BigInt("+3647423");
        BigInt big_int_4 = new BigInt("-1234");
        BigInt big_int_5 = new BigInt("+1234");
//        BigInt big_int_6 = new BigInt("-1234567");
        BigInt big_int_empty = new BigInt("0");

//        System.out.println(big_int_empty.plus(big_int_4));
//        System.out.println(big_int_4.multiply(big_int_empty));
//        System.out.println(big_int_2.divide(big_int_4));
//        System.out.println(big_int_1.plus(big_int_2));
//        System.out.println(big_int_1.minus(big_int_2));
//        System.out.println(big_int_2.minus(big_int_1));
//        System.out.println(big_int_4.minus(big_int_1));
//        System.out.println(big_int_4.minus(big_int_4));
//        System.out.println(big_int_4.minus(big_int_5));
//        System.out.println(big_int_1.multiply(big_int_4));
//        System.out.println(big_int_2.equals(big_int_1));
//        System.out.println(big_int_3.equals(big_int_1));
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
            /* TODO check exception type */
            catch (Exception e) {
                continue;
            }

        }

//        System.out.println(big_int_3 == (big_int_1));
//        System.out.println(big_int_3 > (big_int_1));
    }
}