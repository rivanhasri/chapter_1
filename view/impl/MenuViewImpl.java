package application.chapter2.challenge.view.impl;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import application.chapter2.challenge.model.MenuItem;
import application.chapter2.challenge.view.MenuView;

public class MenuViewImpl implements MenuView {

    public void showMenu() {
        String separatorLine = repeatedChar("=", 26);

        System.out.println(separatorLine);
        System.out.println("Selamat datang di BinarFud");
        System.out.println(separatorLine + "\n");

        System.out.println("Silahkan pilih makanan :");
        System.out.println("1. Nasi Goreng  | 15.000");
        System.out.println("2. Mie Goreng   | 13.000");
        System.out.println("3. Nasi + Ayam  | 18.000");
        System.out.println("4. Es Teh Manis | 3.000");
        System.out.println("5. Es Jeruk     | 5.000");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi\n");
    }

    public String repeatedChar(String chr, int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= num; i++) {
            builder.append(chr);
        }
        return builder.toString();
    }

    public int userChoice() {
        Scanner input = new Scanner(System.in);
        System.out.print("=> ");
        return input.nextInt();
    }

    public int userQty() {
        Scanner input = new Scanner(System.in);
        System.out.print("qty => ");
        return input.nextInt();
    }

    public void orderSummary(List<MenuItem> menus, List<Integer> totalHarga) {
        String separatorLine = repeatedChar("=", 26);
        String dashedLine = repeatedChar("-", 31);

        System.out.println(separatorLine);
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println(separatorLine + "\n");

        for (MenuItem menu : menus) {
            // Cek jika menu ETM maka tab hanya 1
            if (Objects.equals(menu.getMenu(), "Es Teh Manis")) {
                System.out.printf("%s\t%d\t\t%d%n", menu.getMenu(), menu.getQty(), menu.getTotalPrice());
                if (menu.getSpecialNote() != null && !menu.getSpecialNote().isEmpty()) {
                    System.out.println("Note: " + menu.getSpecialNote());
                }
                continue;
            }
            System.out.printf("%s\t\t%d\t\t%d%n", menu.getMenu(), menu.getQty(), menu.getTotalPrice());
            if (menu.getSpecialNote() != null && !menu.getSpecialNote().isEmpty()) {
                System.out.println("Note: " + menu.getSpecialNote());
            }
        }

        System.out.println(dashedLine + "+");
        System.out.printf("Total\t\t\t%d\t\t%d%n%n", totalHarga.get(1), totalHarga.get(0));
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi\n");
    }


    public void orderConfirm(String menu, int harga) {
        String separatorLine = repeatedChar("=", 26);

        System.out.println(separatorLine);
        System.out.println("Berapa pesanan anda");
        System.out.println(separatorLine + "\n");
        System.out.printf("%s\t| %d%n", menu, harga);
        System.out.println("(input 0 untuk kembali)\n");

    }

    public String generateReceipt(List<MenuItem> menus, List<Integer> totalHarga) {
        StringBuilder strukBuilder = new StringBuilder();

        String separatorLine = repeatedChar("=", 26);
        String dashedLine = repeatedChar("-", 31);

        strukBuilder
                .append(separatorLine).append("\n")
                .append("BinarFud\n")
                .append(separatorLine).append("\n\n")
                .append("Terima kasih sudah memesan\ndi BinarFud\n\n")
                .append("Di bawah ini adalah pesanan kamu\n\n");

        for (MenuItem menu : menus) {
            if (Objects.equals(menu.getMenu(), "Es Teh Manis")) {
                strukBuilder
                        .append(menu.getMenu()).append("\t")
                        .append(menu.getQty()).append("\t\t")
                        .append(menu.getTotalPrice()).append("\n");

                if (menu.getSpecialNote() != null && !menu.getSpecialNote().isEmpty()) {
                    strukBuilder
                            .append("Note: ")
                            .append(menu.getSpecialNote()).append("\n");
                }
                continue;
            }

            strukBuilder
                    .append(menu.getMenu()).append("\t\t")
                    .append(menu.getQty()).append("\t\t")
                    .append(menu.getTotalPrice()).append("\n");

            if (menu.getSpecialNote() != null && !menu.getSpecialNote().isEmpty()) {
                strukBuilder
                        .append("Note: ")
                        .append(menu.getSpecialNote()).append("\n");
            }
        }

        strukBuilder
                .append(dashedLine).append("+\n")
                .append("Total\t\t\t")
                .append(totalHarga.get(1)).append("\t\t")
                .append(totalHarga.get(0)).append("\n\n")
                .append("Pembayaran : BinarCash\n\n")
                .append(separatorLine).append("\n")
                .append("Simpan struk ini sebagai\nbukti pembayaran\n")
                .append(separatorLine).append("\n");

        return strukBuilder.toString();
    }

    public void checkQty() {
        String separatorLine = repeatedChar("=", 26);

        System.out.println(separatorLine);
        System.out.println("Minimal 1 jumlah pesanan!");
        System.out.println(separatorLine);
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Pilihan tidak valid. Coba lagi!");
    }

    public String userSpecialNote() {
        Scanner input = new Scanner(System.in);
        System.out.print("Special Note (kosongkan jika tidak ada): ");
        return input.nextLine();
    }
}
