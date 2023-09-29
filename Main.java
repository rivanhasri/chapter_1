package application.chapter2.challenge;

import application.chapter2.challenge.controller.impl.MenuControllerImpl;

public class Main {
    public static void main(String[] args) {
        MenuControllerImpl controller = new MenuControllerImpl();
        controller.order();
    }
}
