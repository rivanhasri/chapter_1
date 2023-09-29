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
        view.showMenu();

        do {
            int choice = view.userChoice();

            if (choice == 0) {
                break;

            } else if (choice == 99) {
                if (orderedMenus.isEmpty()) { view.checkQty(); continue; }

                view.orderSummary(orderedMenus, calculateTotalPrices(orderedMenus));
                int choiceConfirm = view.userChoice();

                if (choiceConfirm == 0) break;
                else if (choiceConfirm == 1) { checkoutMenu(); break; }
                else if (choiceConfirm == 2) view.showMenu();
                else view.displayInvalidChoiceMessage();

            } else if (choice >= 1 && choice <= 5) {
                String menuName = menus[choice - 1];
                int menuPrice = prices[choice - 1];

                view.orderConfirm(menuName, menuPrice);

                int qty = view.userQty();

                if (qty > 0) {
                    SpecialNoteMenuItem Menu = new SpecialNoteMenuItem(menuName, qty, menuPrice, "");

                    String specialNote = view.userSpecialNote();
                    Menu.setSpecialNote(specialNote);

                    orderedMenus.add(Menu);
                    view.showMenu();

                } else if (qty == 0) {
                    view.showMenu();
                }

            } else {
                view.displayInvalidChoiceMessage();
            }
        } while (true);
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
