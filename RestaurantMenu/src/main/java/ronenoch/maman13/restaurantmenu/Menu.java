package ronenoch.maman13.restaurantmenu;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Menu {
    private Map<String, ArrayList<Item>> items;

    public Menu() {
        String item_name;
        String item_type;
        int item_price;

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

                /* Validating the item's type */
                if (null == this.items.get(item_type)) {
                    JOptionPane.showMessageDialog(null, item_type + " is not a valid type", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.items.get(item_type).add(new Item(item_name, item_type, item_price));
                }
            }

            input.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null, "The item's price is invalid", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, "Missing line in the menu. trying anyway", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Map<String, ArrayList<Item>> getItems() {
        return items;
    }
}
