package aplikasi.pesanMakanan;

import aplikasi.pesanMakanan.controller.impl.MenuControllerImpl;

public class Main {
    public static void main(String[] args) {
        MenuControllerImpl controller = new MenuControllerImpl();
        controller.order();
    }
}
