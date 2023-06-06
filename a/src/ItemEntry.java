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