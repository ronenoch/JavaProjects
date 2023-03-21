import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CashDesk cashDesk = new CashDesk(300.0);
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("The Usage menu is:");
            System.out.println("Enter 1 to add an item to the current purchase");
            System.out.println("Enter 2 to receive cash from the customer");
            System.out.println("Enter 3 to get the current purchase details (receipt)");
            System.out.println("Enter 4 to get the total amount of cash in the cash register");
            System.out.println("Enter 5 to exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Please enter the item's name: ");
                    String name = sc.nextLine();
                    System.out.print("Please enter the item's price: ");
                    double price = sc.nextDouble();
                    System.out.print("Please enter the item's quantity: ");
                    int quantity = sc.nextInt();
                    cashDesk.addNewItem(new Item(name, price), quantity);
                    break;

                case 2:
                    System.out.print("Please enter the amount received from the customer: ");
                    double customerAmount = sc.nextDouble();
                    if (cashDesk.getCurrentPurchaseAmount() > customerAmount) {
                        System.out.println("You can't pay less then the price!");
                        break;
                    }
                    double change = cashDesk.receiveBillPayment(customerAmount);
                    System.out.println("The customer's change is: " + change);
                    break;

                case 3:
                    System.out.println("The current purchase details:");
                    System.out.println(cashDesk);
                    break;

                case 4:
                    System.out.println("The total amount of money in the register is: " + cashDesk.getTotalCash());
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

}