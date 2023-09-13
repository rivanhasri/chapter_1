package application.chapter1.challenge;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class menuPesanMakanan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String menu;
        String teks;
        int harga;
        List<Integer> hasil;
        int qty;
        // Dibedakan karena untuk:
        // historyPemesanan : Berguna untuk menampilkan ke terminal
        // historyPemesananSemua : Berguna untuk menyimpan semua history pembayaran ke dalam file
        List<String> historyPemesanan = new ArrayList<>();
        List<String> historyPemesananSemua;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("historyPemesanan.txt", true))) {
            historyPemesananSemua = bacaRiwayatDariFile("history.txt");

            do {
                System.out.println(repeatedChar("=", 26));
                System.out.println("Selamat datang di BinarFud");
                System.out.println(repeatedChar("=", 26) + "\n");
                System.out.println("Silahkan pilih makanan :");
                System.out.println("1. Nasi Goreng  | 15.000");
                System.out.println("2. Mie Goreng   | 13.000");
                System.out.println("3. Nasi + Ayam  | 18.000");
                System.out.println("4. Es Teh Manis | 3.000");
                System.out.println("5. Es Jeruk     | 5.000");
                System.out.println("99. Pesan dan Bayar");
                System.out.println("0. Keluar Aplikasi\n");
                System.out.println("=> ");
                int pilihan = input.nextInt();

                if (pilihan == 0) {
                    break;
                }

                if (pilihan < 0 || (pilihan > 5 && pilihan != 99)) {
                    System.out.println("Pilihan tidak valid. Coba lagi!");
                    continue;
                }

                if (pilihan == 99) {
                    System.out.println(repeatedChar("=", 26));
                    System.out.println("Konfirmasi & Pembayaran");
                    System.out.println(repeatedChar("=", 26) + "\n");
                    tampilkanRiwayat(historyPemesanan);
                    hasil = totalHarga(historyPemesanan);
                    System.out.println(repeatedChar("-", 31) + "+");
                    String totalJumlahHarga = "Total          " + hasil.get(1) + "       " + hasil.get(0);
                    System.out.println(totalJumlahHarga);
                    System.out.println("\n1. Konfirmasi dan Bayar");
                    System.out.println("2. Kembali ke menu utama");
                    System.out.println("0. Keluar aplikasi\n");
                    System.out.println("=> ");
                    int pilihanKonfirmasi = input.nextInt();

                    if (pilihanKonfirmasi == 1) {
                        historyPemesanan.add(repeatedChar("-", 31) + "+");
                        historyPemesanan.add(totalJumlahHarga);
                        historyPemesananSemua.addAll(historyPemesanan); // menambahkan semua elemen historyPemesanan ke historyPemesananSemua
                        cetakRiwayatFile(struk(historyPemesananSemua), writer);
                        System.out.println(struk(historyPemesanan));
                        break;
                    } else if (pilihanKonfirmasi == 2) {
                        continue;
                    } else if (pilihanKonfirmasi == 0) {
                        break;
                    }
                }

                switch (pilihan) {
                    case 1:
                        konfirmasiPesanan("Nasi Goreng", "15.000");
                        qty = input.nextInt();

                        if (qty == 0) {
                            continue;
                        } else if (qty > 0) {
                            harga = makanan(qty, 15000);
                            menu = "Nasi Goreng";
                            teks = menu + "    " + qty + "       " + harga;
                            historyPemesanan.add(teks);
                            continue;
                        }

                    case 2:
                        konfirmasiPesanan("Mie Goreng", "13.000");
                        qty = input.nextInt();

                        if (qty == 0) {
                            continue;
                        } else if (qty > 0) {
                            harga = makanan(qty, 13000);
                            menu = "Mie Goreng";
                            teks = menu + "     " + qty + "       " + harga;
                            historyPemesanan.add(teks);
                            continue;
                        }

                    case 3:
                        konfirmasiPesanan("Nasi + Ayam", "18.000");
                        qty = input.nextInt();

                        if (qty == 0) {
                            continue;
                        } else if (qty > 0) {
                            harga = makanan(qty, 18000);
                            menu = "Nasi + Ayam";
                            teks = menu + "    " + qty + "       " + harga;
                            historyPemesanan.add(teks);
                            continue;
                        }

                    case 4:
                        konfirmasiPesanan("Es Teh Manis", "3.000");
                        qty = input.nextInt();

                        if (qty == 0) {
                            continue;
                        } else if (qty > 0) {
                            harga = makanan(qty, 3000);
                            menu = "Es Teh Manis";
                            teks = menu + "   " + qty + "       " + harga;
                            historyPemesanan.add(teks);
                            continue;
                        }

                    case 5:
                        konfirmasiPesanan("Es Jeruk", "5.000");
                        qty = input.nextInt();

                        if (qty == 0) {
                            continue;
                        } else if (qty > 0) {
                            harga = makanan(qty, 5000);
                            menu = "Es Jeruk";
                            teks = menu + "       " + qty + "       " + harga;
                            historyPemesanan.add(teks);
                            continue;
                        }
                }
            } while (true);
        } catch (IOException e) {
            System.out.println("Gagal menulis ke file history.txt.");
        }
    }

    public static String repeatedChar(String chr, int num) {
        // Penggunaan StringBuilder lebih efektif dibandingkan String
        // Karena bersifat mutable, apabila kita menggunakan String
        // setiap kali kita mengubah, kita menciptakan objek string baru
        // yang mana akan menghabiskan memori dan komputasi
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= num; i++) {
            builder.append(chr);
        }
        return builder.toString();
    }

    public static void konfirmasiPesanan(String menu, String harga) {
        System.out.println(repeatedChar("=", 26));
        System.out.println("Berapa pesanan anda");
        System.out.println(repeatedChar("=", 26) + "\n");
        System.out.println(menu + "     | " + harga);
        System.out.println("(input 0 untuk kembali)\n");
        System.out.println("qty => ");
    }

    public static int makanan(int qty, int harga) {
        return qty * harga;
    }

    public static void cetakRiwayatFile(String struk, BufferedWriter writer) {
        try {
            writer.write(struk); // menulis konten ke text
        }

        catch (IOException e){
            System.out.println("Gagal mencetak riwayat ke dalam file history.txt");
        }
    }

    public static List<String> bacaRiwayatDariFile(String fileName) {
        List<String> historyPemesananSemua = new ArrayList<>(); // D//:temp/folder
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null){
                historyPemesananSemua.add(line);
            }
        } catch (IOException e){
            System.out.println("Gagal membaca file " + fileName);
        }
        return historyPemesananSemua;
    }

    public static void tampilkanRiwayat(List<String> historyPemesanan) {
        for (String menu:historyPemesanan){
            System.out.println(menu);
        }
    }

    public static List<Integer> totalHarga(List<String> historyPemesanan) {
        int total = 0;
        int jumlah = 0;
        for (String menu : historyPemesanan) {
            String[] parts = menu.split("\\s+");

            if (parts.length != 0) {
                try {
                    int hargaperMenu = Integer.parseInt(parts[parts.length - 1]);
                    int jumlahMenu = Integer.parseInt(parts[parts.length - 2]);
                    total += hargaperMenu;
                    jumlah += jumlahMenu;
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        result.add(total);
        result.add(jumlah);
        return result;
    }

    public static String struk(List<String> historyPemesanan) {
        StringBuilder strukBuilder = new StringBuilder();

        strukBuilder.append(repeatedChar("=", 26)+"\n");
        strukBuilder.append("BinarFud\n");
        strukBuilder.append(repeatedChar("=", 26) + "\n\n");
        strukBuilder.append("Terima kasih sudah memesan\ndi BinarFud\n\n");
        strukBuilder.append("Di bawah ini adalah pesanan kamu\n\n");
        for (String menu : historyPemesanan) {
            strukBuilder.append(menu).append("\n");
        }
        strukBuilder.append("\nPembayaran : BinarCash\n\n");
        strukBuilder.append(repeatedChar("=", 26) + "\n");
        strukBuilder.append("Simpan struk ini sebagai\nbukti pembayaran\n");
        strukBuilder.append(repeatedChar("=", 26) + "\n");

        return strukBuilder.toString();
    }

}
