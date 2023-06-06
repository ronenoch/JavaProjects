import java.util.ArrayList;

public class CashDesk {
    private double cashInRegister;
    private final ArrayList<ItemEntry> receiptItems;

    public CashDesk() {
        this.cashInRegister = 0;
        this.receiptItems = new ArrayList<>();
    }

    public CashDesk(double cashInRegister) {
        this.cashInRegister = cashInRegister;
        this.receiptItems = new ArrayList<>();
    }

    public void addNewItem(Item item, int quantity) {
        if (quantity >= 0) {
            this.receiptItems.add(new ItemEntry(item, quantity));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Item's Name\tQuantity\tPrice\n");
        for (ItemEntry itemEntry : this.receiptItems) {
            builder.append(itemEntry.getItem().getName()).append("\t\t");
            builder.append(itemEntry.getQuantity()).append("\t\t");
            builder.append(itemEntry.getItemTotalPrice()).append("\n");
        }
        builder.append("\nTotal Amount: ").append(getCurrentPurchaseAmount());
        return builder.toString();
    }

    /**
     * Function that returns the total price of the current purchase
     */
    public double getCurrentPurchaseAmount() {
        double totalPurchase = 0;
        for (ItemEntry itemEntry : receiptItems) {
            totalPurchase += itemEntry.getItemTotalPrice();
        }
        return totalPurchase;
    }

    /**
     * Gets the total cash in the register
     * @return the total cash.
     */
    public double getTotalCash() {
        return this.cashInRegister;
    }

    /**
     * Receives a payment from the user.
     * @param payment the payment of the user
     * @return the change
     */
    public double receiveBillPayment(double payment) {
        double change = payment - getCurrentPurchaseAmount();
        if (change < 0) {
            return -1;
        }
        cashInRegister += getCurrentPurchaseAmount();
        receiptItems.clear();
        return change;
    }
}
