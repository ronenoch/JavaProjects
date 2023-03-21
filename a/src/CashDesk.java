import java.util.ArrayList;

class Item {
    private final String name;
    private final double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ItemEntry {
    private final Item item;
    private final int quantity;

    public ItemEntry(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemTotalPrice() {
        return quantity * item.getPrice();
    }
}

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
        builder.append("\nTotal Amount: " + getCurrentPurchaseAmount());
        return builder.toString();
    }

    public double getCurrentPurchaseAmount() {
        double totalPurchase = 0;
        for (ItemEntry itemEntry : receiptItems) {
            totalPurchase += itemEntry.getItemTotalPrice();
        }
        return totalPurchase;
    }

    public double getTotalCash() {
        return this.cashInRegister;
    }

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
