import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {
    private Map<Item, Integer> orderData;
    private Menu menu;

    private Item getItemByName(String name) {
        if (null == this.orderData) {
            return null;
        }
        for (Item item : this.orderData.keySet()) {
            if (Objects.equals(item.getDescription(), name)) {
                return item;
            }
        }
        return null;
    }
    public Order() {
        this.menu = new Menu();
        this.orderData = new HashMap<Item, Integer>();
    }

    public Map<Item, Integer> getOrderData() {
        return this.orderData;
    }

    public void add_item(Item item, int amount) {
        Item key_item = getItemByName(item.getDescription());

        if (null == key_item) {
            this.orderData.put(item, amount);
        } else {
            this.orderData.replace(key_item, amount);
        }
    }

    public void clear() {
        this.orderData.clear();
    }

    public int getTotalPrice() {
        int total_price = 0;

        for (Map.Entry<Item, Integer> entry : this.orderData.entrySet()) {
            total_price += entry.getValue() * entry.getKey().getPrice();
        }
        return total_price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your Order\n");
//        sb.append("Name     amount  total price\n");
        sb.append("Name\t\tamount\ttotal price\n");
        /* TODO check null ? PROBABLY NOT */
        for (Map.Entry<Item, Integer> entry : this.getOrderData().entrySet()) {
            sb.append(entry.getKey().getDescription());
//                    .append(entry.getKey().getPrice());
//            sb.append("           ").append(entry.getValue()).append("             ").append(entry.getValue() * entry.getKey().getPrice());
            sb.append("\t\t").append(entry.getValue()).append("\t\t").append(entry.getValue() * entry.getKey().getPrice());
            sb.append("\n");
        }
        sb.append("total price: ").append(this.getTotalPrice());
        return sb.toString();
    }

    public Menu getMenu() {
        return menu;
    }
}
