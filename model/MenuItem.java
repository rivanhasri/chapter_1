package application.chapter2.challenge.model;

public class MenuItem {
    final String menu;
    final int qty;
    final int totalPrice;
    final int price;

    public MenuItem(String menu, int qty, int price) {
        this.menu = menu;
        this.qty = qty;
        this.price = price;
        this.totalPrice = qty * price;
    }

    public String getMenu() {
        return menu;
    }

    public int getQty() {
        return qty;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getSpecialNote() {
        return "";
    }

    public void setSpecialNote(String specialNote) {
        /*
         This method intentionally left empty in the abstract class.
         Subclasses may choose to implement it or not.
         */
    }
}
