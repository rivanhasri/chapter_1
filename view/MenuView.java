package application.chapter2.challenge.view;

import application.chapter2.challenge.model.MenuItem;

import java.util.List;

public interface MenuView {
    void showMenu();
    String repeatedChar(String chr, int num);
    int userChoice();
    int userQty();
    void orderSummary(List<MenuItem> menus, List<Integer> totalHarga);
    void orderConfirm(String menu, int harga);
    String generateReceipt(List<MenuItem> menus, List<Integer> totalHarga);
    void checkQty();
    void displayInvalidChoiceMessage();
}
