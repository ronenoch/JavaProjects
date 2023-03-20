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

class LineItem {
    private Item item;
    private int quantity;

    public LineItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }
}

public class CashRegister {
    private ArrayList<LineItem> receiptItems;
    private double cashInRegister;

    public CashRegister() {
        this.cashInRegister = 0;
        this.receiptItems = new ArrayList<>();
    }

    public CashRegister(double cashInRegister) {
        this.cashInRegister = cashInRegister;
        this.receiptItems = new ArrayList<>();
    }

    public void addItem(Item item, int quantity) {
        LineItem lineItem = new LineItem(item, quantity);
        this.receiptItems.add(lineItem);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item\tQuantity\tPrice\n");
        for (LineItem lineItem : this.receiptItems) {
            sb.append(lineItem.getItem().getName()).append("\t")
                    .append(lineItem.getQuantity()).append("\t\t")
                    .append(lineItem.getTotalPrice()).append("\n");
        }
        sb.append("\nTotal: ").append(getCurrentPurchaseAmount());
        return sb.toString();
    }

    public double getCurrentPurchaseAmount() {
        double totalPurchase = 0;
        for (LineItem lineItem : receiptItems) {
            totalPurchase += lineItem.getTotalPrice();
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
