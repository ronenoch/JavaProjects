package ronenoch.maman13.restaurantmenu;

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

    public Menu getMenu() {
        return menu;
    }
}
