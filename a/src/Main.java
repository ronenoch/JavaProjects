import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CashRegister cashRegister = new CashRegister(1000.0);
        Scanner sc= new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Enter 1 to add an item to the current purchase");
            System.out.println("Enter 2 to receive payment from the customer");
            System.out.println("Enter 3 to get the current purchase details");
            System.out.println("Enter 4 to get the total amount of all purchases");
            System.out.println("Enter 5 to exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Please enter the item's name: ");
                    String itemName = sc.nextLine();
                    System.out.print("Please enter the item's price: ");
                    double itemPrice = sc.nextDouble();
                    System.out.print("Please enter the item's quantity: ");
                    int itemQuantity = sc.nextInt();
                    Item item = new Item(itemName, itemPrice);
                    cashRegister.addItem(item, itemQuantity);
                    break;

                case 2:
                    System.out.print("Please enter the amount received from the customer: ");
                    double customerAmount = sc.nextDouble();
                    if (cashRegister.getCurrentPurchaseAmount() > customerAmount) {
                        System.out.println("You can't pay less then the price!");
                        break;
                    }
                    double change = cashRegister.receivePayment(customerAmount);
                    System.out.println("The customer's change is: " + change);
                    break;

                case 3:
                    System.out.println("The current purchase details:");
                    System.out.println(cashRegister);
                    break;

                case 4:
                    System.out.println("The total amount of money is: " + cashRegister.getTotalCash());
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