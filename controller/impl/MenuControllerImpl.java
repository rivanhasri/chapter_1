package application.chapter2.challenge.controller.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.chapter2.challenge.controller.MenuController;
import application.chapter2.challenge.model.MenuItem;
import application.chapter2.challenge.model.SpecialNoteMenuItem;
import application.chapter2.challenge.view.impl.MenuViewImpl;

public class MenuControllerImpl implements MenuController {
    final List<MenuItem> orderedMenus;
    final MenuViewImpl view;
    final String[] menus = {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"};
    final int[] prices = {15000, 13000, 18000, 3000, 5000};
    private BufferedWriter writer;

    public MenuControllerImpl() {
        orderedMenus = new ArrayList<>();
        view = new MenuViewImpl();
        try {
            writer = new BufferedWriter(new FileWriter("historyPemesanan.txt", true));
        } catch (IOException e) {
            System.out.println("Gagal menulis ke file history.txt");
        }
    }

    public void order() {
        boolean shouldExit = false;
        view.showMenu();

        do {
            int choice = view.userChoice();

            if (choice == 0) {
                shouldExit = true;
            } else if (choice == 99) {
                shouldExit = handleOrderConfirmation();
            } else if (choice >= 1 && choice <= 5) {
                handleMenuSelection(choice);
            } else {
                view.displayInvalidChoiceMessage();
            }
        } while (!shouldExit);
    }

    private boolean handleOrderConfirmation() {
        if (orderedMenus.isEmpty()) {
            view.checkQty();
            return false;
        } else {
            view.orderSummary(orderedMenus, calculateTotalPrices(orderedMenus));
            int choiceConfirm = view.userChoice();
            return handleConfirmationChoice(choiceConfirm);
        }
    }

    private boolean handleConfirmationChoice(int choiceConfirm) {
        switch (choiceConfirm) {
            case 0:
                return true;
            case 1:
                checkoutMenu();
                return true;
            case 2:
                view.showMenu();
                return false;
            default:
                view.displayInvalidChoiceMessage();
                return false;
        }
    }

    private void handleMenuSelection(int choice) {
        String menuName = menus[choice - 1];
        int menuPrice = prices[choice - 1];

        view.orderConfirm(menuName, menuPrice);

        int qty = view.userQty();

        if (qty > 0) {
            SpecialNoteMenuItem menu = createMenuItemWithSpecialNote(menuName, qty, menuPrice);

            orderedMenus.add(menu);
            view.showMenu();
        } else if (qty == 0) {
            view.showMenu();
        }
    }

    private SpecialNoteMenuItem createMenuItemWithSpecialNote(String menuName, int qty, int menuPrice) {
        String specialNote = view.userSpecialNote();
        return new SpecialNoteMenuItem(menuName, qty, menuPrice, specialNote);
    }

    public List<Integer> calculateTotalPrices(List<MenuItem> Menus) {
        int totalBayar = 0;
        int totalJumlah = 0;

        for (MenuItem Menu : Menus) {
            totalBayar += Menu.getTotalPrice();
            totalJumlah += Menu.getQty();
        }

        List<Integer> result = new ArrayList<>();
        result.add(totalBayar);
        result.add(totalJumlah);
        return result;
    }

    public void checkoutMenu() {
        writeHistoryFile(orderedMenus);
        System.out.println("Berhasil cetak struk!");
    }

    public void writeHistoryFile(List<MenuItem> Menus) {
        try {
            String receipt = view.generateReceipt(Menus, calculateTotalPrices(Menus));
            writer.write(receipt);
            writer.close();

        } catch (IOException e) {
            System.out.println("Gagal menulis ke file historyPemesanan.txt");
        }
    }
}
