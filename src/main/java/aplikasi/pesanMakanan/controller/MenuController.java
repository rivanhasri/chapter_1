package aplikasi.pesanMakanan.controller;

import aplikasi.pesanMakanan.model.MenuItem;

import java.util.List;

public interface MenuController {
    void order();

    List<Integer> calculateTotalPrices(List<MenuItem> Menus);

    void checkoutMenu();

    void writeHistoryFile(List<MenuItem> Menus);
}
