import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Menu {
    private Map<String, ArrayList<Item>> items;

    public Menu() {
        String itemName;
        String itemType;
        int itemPrice;

        this.items = new HashMap<String, ArrayList<Item>>() {};
        this.items.put("appetizer", new ArrayList<Item>());
        this.items.put("main course", new ArrayList<Item>());
        this.items.put("dessert", new ArrayList<Item>());
        this.items.put("drink", new ArrayList<Item>());


        try {
            Scanner input = new Scanner(new File("menu.txt"));

            while (input.hasNextLine()){
                itemName = input.nextLine();
                itemType = input.nextLine();
                itemPrice = Integer.valueOf(input.nextLine());

                /* Validating the item's type */
                if (null == this.items.get(itemType)) {
                    JOptionPane.showMessageDialog(null, itemType + " is not a valid type", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.items.get(itemType).add(new Item(itemName, itemType, itemPrice));
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
