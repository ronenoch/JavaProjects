import java.util.ArrayList;

class Item {
    private String name;
    private double price;

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
    private Item item;
    private int quantity;

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

public class CashRegister {
    private double cashInRegister;
    private ArrayList<ItemEntry> receiptItems;

    public CashRegister() {
        this.cashInRegister = 0;
        this.receiptItems = new ArrayList<>();
    }

    public CashRegister(double cashInRegister) {
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
        builder.append("\nTotal Amount: " + String.valueOf(getCurrentPurchaseAmount()));
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

    public double receivePayment(double payment) {
        double change = payment - getCurrentPurchaseAmount();
        if (change < 0) {
            return -1;
        }
        cashInRegister += getCurrentPurchaseAmount();
        receiptItems.clear();
        return change;
    }
}
