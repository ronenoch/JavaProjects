package ronenoch.maman13.restaurantmenu;

public class Item {
    private String description;
//    private ItemType type;
    private String type;
    private int price;

//    public Item(String description, ItemType type, int price) {
    public Item(String description, String type, int price) {
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

//    public ItemType getType() {
    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}
