package aplikasi.pesanMakanan.controller.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import aplikasi.pesanMakanan.controller.MenuController;
import aplikasi.pesanMakanan.model.MenuItem;
import aplikasi.pesanMakanan.model.SpecialNoteMenuItem;
import aplikasi.pesanMakanan.view.impl.MenuViewImpl;

public class MenuControllerImpl implements MenuController {
    public final List<MenuItem> orderedMenus = new ArrayList<>();
    private MenuViewImpl view = new MenuViewImpl();
    public final Map<Integer, MenuItem> menuMap = new HashMap<>();
    private final BufferedWriter writer;
    private final int[] prices = {15000, 13000, 18000, 3000, 5000};

    public MenuControllerImpl() {
        List<String> menusList = Arrays.asList("Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk");
        IntStream.range(0, menusList.size()).forEach(index -> {
            String menu = menusList.get(index);
            int price = prices[index];
            menuMap.put(index + 1, new MenuItem(menu, 0, price));
        });

        try {
            writer = new BufferedWriter(new FileWriter("historyPemesanan.txt", true));
        } catch (IOException e) {
            throw new RuntimeException("Gagal menulis ke file history.txt", e);
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
            } else if (menuMap.containsKey(choice)) {
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

    public boolean handleConfirmationChoice(int choiceConfirm) {
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

    public void handleMenuSelection(int choice) {
        MenuItem menuItem = menuMap.get(choice);
        view.orderConfirm(menuItem.getMenu(), menuItem.getPrice());

        int qty = view.userQty();

        if (qty > 0) {
            SpecialNoteMenuItem menu = createMenuItemWithSpecialNote(menuItem, qty);

            orderedMenus.add(menu);
            view.showMenu();
        } else if (qty == 0) {
            view.showMenu();
        }
    }

    private SpecialNoteMenuItem createMenuItemWithSpecialNote(MenuItem menuItem, int qty) {
        String specialNote = view.userSpecialNote();
        return new SpecialNoteMenuItem(menuItem.getMenu(), qty, menuItem.getPrice(), specialNote);
    }

    public List<Integer> calculateTotalPrices(List<MenuItem> menus) {
        Optional<List<MenuItem>> menusOptional = Optional.ofNullable(menus);

        int totalBayar = menusOptional.map(menuList ->
                menuList.stream()
                .map(MenuItem::getTotalPrice)
                .reduce(0, Integer::sum)
        ).orElse(0);

        int totalJumlah = menusOptional.map(menuList ->
                menuList.stream()
                .map(MenuItem::getQty)
                .reduce(0, Integer::sum)
        ).orElse(0);

        return Arrays.asList(totalBayar, totalJumlah);
    }

    public void checkoutMenu() {
        writeHistoryFile(orderedMenus);
        System.out.println("Berhasil cetak struk!");
    }

    public void writeHistoryFile(List<MenuItem> menus) {
        Optional<List<MenuItem>> menusOptional = Optional.ofNullable(menus);

        try {
            if (menusOptional.isPresent()) {
                String receipt = view.generateReceipt(menus, calculateTotalPrices(menus));
                writer.write(receipt);
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Gagal menulis ke file historyPemesanan.txt", e);
        }
    }

    public void setView(MenuViewImpl view) {
        this.view = view;
    }
}

