package ronenoch.maman13.restaurantmenu;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
//    private Map<ItemType, Item> items;
    private Map<String, ArrayList<Item>> items;
//    final private HashMap<String, ItemType> types;


    public Menu() {
        String item_name;
        String item_type;
        int item_price;
//        ItemType real_item_type;

//        this.types = new HashMap<>(4);
//        types.put("appetizer", ItemType.ITEM_TYPE_APPETIZER);
//        types.put("main course", ItemType.ITEM_TYPE_MAIN_COURSE);
//        types.put("dessert", ItemType.ITEM_TYPE_DESSERT);
//        types.put("drink", ItemType.ITEM_TYPE_DRINK);
        this.items = new HashMap<String, ArrayList<Item>>() {};
        this.items.put("appetizer", new ArrayList<Item>());
        this.items.put("main course", new ArrayList<Item>());
        this.items.put("dessert", new ArrayList<Item>());
        this.items.put("drink", new ArrayList<Item>());


        try {
            Scanner input = new Scanner(new File("menu.txt"));

            while (input.hasNextLine()){
                item_name = input.nextLine();
                item_type = input.nextLine();
                item_price = Integer.valueOf(input.nextLine());

//                real_item_type = types.get(item_type);
//
//                this.items.put(real_item_type, new Item(item_name, real_item_type, item_price));
                /* TODO should I check that the item-type is ok? */
                this.items.get(item_type).add(new Item(item_name, item_type, item_price));
//                this.items.put(item_type, new Item(item_name, item_type, item_price));
            }

            input.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null, "the item's price is invalid", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

//    public void print_menu() {
//
//    }

//    public Map<ItemType, Item> getItems() {
    public Map<String, ArrayList<Item>> getItems() {
        return items;
    }
}
