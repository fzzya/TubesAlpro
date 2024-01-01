package p;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

class SistemCatering {
    private static String Username = "admin";
    private static String Password = "admin";

    private static ArrayList<Pelanggan> pelanggan = new ArrayList<>();
    private static ArrayList<Transaksi> transaksiList = new ArrayList<>();
    private static ArrayList<ItemMenu> daftarMenu = new ArrayList<>();
    private static Pelanggan pelangganSaatIni;
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiData();
        SistemCatering run = new SistemCatering();
        run.menu();
    }

    private static void urutanMenu() {
        Collections.sort(daftarMenu, Comparator.comparingInt(ItemMenu::getHarga));
    }

    private static void urutTransaksiTotalHarga() {
        Collections.sort(transaksiList, Comparator.comparingInt(transaksi -> transaksi.getItem().getHarga() * transaksi.getJumlah()));
    }

    private static void lihatMenuTerurut() {
        urutanMenu();
        System.out.println("\n\t === Menu Makanan Urutan Berdasarkan Harga ===");
        for (ItemMenu itemMenu : daftarMenu) {
            System.out.println(itemMenu.getNama() + "\n   Isi : " + itemMenu.getIsi() + "\n   Harga : Rp " + itemMenu.getHarga());
        }
    }

    private static void lihatTransaksiTerurut() {
        urutTransaksiTotalHarga();
        System.out.println("\n=== Struk Transaksi Belanjaan ===");
        for (Transaksi transaksi : transaksiList) {
            System.out.println(transaksi);
        }
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n\n" + "\t >>> MENU UTAMA <<<");
            System.out.println("1. Admin");
            System.out.println("2. Pelanggan");
            System.out.println("3. Keluar");
            pilihan = dapatkanPilihan();

            switch (pilihan) {
                case 1:
                    LoginAdmin(input);
                    break;
                case 2:
                    menuPelanggan();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan aplikasi.");
                    System.exit(0);
                default:
                    break;
            }
        } while (pilihan != 3);
    }

    public static void LoginAdmin(Scanner input) {
        System.out.print("Masukkan username admin: ");
        String namaPengguna = input.next();
        System.out.print("Masukkan password admin: ");
        String sandi = input.next();

        if (namaPengguna.equals(Username) && sandi.equals(Password)) {
            pelangganSaatIni = new Pelanggan(namaPengguna, sandi);
            System.out.println("Berhasil masuk sebagai admin. Selamat datang, " + namaPengguna + "!");
            menuAdmin(input);
        } else {
            System.out.println("Gagal masuk sebagai admin. Silakan coba lagi.");
        }
    }

    private static void menuAdmin(Scanner input) {
        System.out.println("\n\t >>> Menu Admin <<<");
        System.out.println("1. Lihat Menu");
        System.out.println("2. Tambah Menu");
        System.out.println("3. Lihat Laporan");
        System.out.println("4. Kembali");
        System.out.println("5. Keluar");

        int pilihan = dapatkanPilihan();

        switch (pilihan) {
            case 1:
                lihatMenuTerurut();
                break;
            case 2:
                tambahMenu();
                break;
            case 3:
                buatLaporan();
                break;
            case 4:
                menu();
                break;
            case 5:
                System.out.println("Terima kasih telah menggunakan aplikasi.");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    private static void menuPelanggan() {
        System.out.println("\n\t >>> Menu Pelanggan <<<");
        System.out.println("1. Lihat Menu");
        System.out.println("2. Pesan Catering");
        System.out.println("3. Lihat Struk Pemesanan");
        System.out.println("4. Kembali");
        System.out.println("5. Keluar");

        int pilihan = dapatkanPilihan();

        switch (pilihan) {
            case 1:
                lihatMenu();
                break;
            case 2:
                pesanCatering();
                return;
            case 3:
                lihatTransaksiTerurut();
                break;
            case 4:
                menu();
            case 5:
                System.out.println("Terima kasih telah menggunakan aplikasi F2D Catering.");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    private static void lihatMenu() {
        System.out.println("\n\t ===== Menu Makanan Berat =====");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i) instanceof MakananBerat) {
                System.out.println((i + 1) + ". " + daftarMenu.get(i).getNama() + "\n   Isi : "
                        + daftarMenu.get(i).getIsi() + "\n   Harga : Rp " + daftarMenu.get(i).getHarga());
            }
        }

        System.out.println("\n\t ===== Menu Makanan Ringan =====");
        for (int j = 0; j < daftarMenu.size(); j++) {
            if (daftarMenu.get(j) instanceof MakananRingan) {
                System.out.println((j + 1) + ". " + daftarMenu.get(j).getNama() + "\n   Isi : "
                        + daftarMenu.get(j).getIsi() + "\n   Harga : Rp " + daftarMenu.get(j).getHarga());
            }
        }
    }

    private static void tambahMenu() {
        System.out.println("\n\t ===== Menu Catering =====");
        System.out.println("1. Makanan Berat");
        System.out.println("2. Makanan Ringan");
        int pilihan = dapatkanPilihan();

        switch (pilihan) {
            case 1:
                tambahItemMenuBerat();
                break;
            case 2:
                tambahItemMenuRingan();
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    private static void tambahItemMenuBerat() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama menu makanan berat: ");
        String nama = input.nextLine();
        System.out.print("Masukkan isi menu makanan berat: ");
        String isi = input.nextLine();
        System.out.print("Masukkan harga menu makanan berat: ");
        int harga = input.nextInt();

        daftarMenu.add(new MakananBerat(nama, isi, harga));

        System.out.println("Menu makanan berat berhasil ditambahkan.");
    }

    private static void tambahItemMenuRingan() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama menu makanan ringan: ");
        String nama = input.nextLine();
        System.out.print("Masukkan isi menu makanan ringan: ");
        String isi = input.nextLine();
        System.out.print("Masukkan harga menu makanan ringan: ");
        int harga = input.nextInt();

        daftarMenu.add(new MakananRingan(nama, isi, harga));

        System.out.println("Menu makanan ringan berhasil ditambahkan.");
    }

    private static void inisialisasiData() {
        pelanggan.add(new Pelanggan(Username, Password));

        daftarMenu.add(new MakananBerat("Paket Utama Lapar", "Nasi, Ayam Lalapan", 30000));
        daftarMenu.add(new MakananBerat("Paket Utama Kenyang", "Nasi, Bebek Lalapan ", 35000));
        daftarMenu.add(new MakananBerat("Paket Utama Begahh", "Nasi, Rendang, & Sayur Singkong", 40000));

        daftarMenu.add(new MakananRingan("Paket Camilan Kue", "Klepon & Kue Lapis", 15000));
        daftarMenu.add(new MakananRingan("Paket Camilan Gorengan", "Onde-onde & Pastel", 20000));
        daftarMenu.add(new MakananRingan("Paket Camilan Campur", "Pie Buah", 25000));
    }

    private static int dapatkanPilihan() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan pilihan Anda: ");
        return input.nextInt();
    }

    private static void pesanCatering() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama Anda: ");
        String namaPelanggan = input.next();

        lihatMenu();
        System.out.print("Pilih nomor menu yang ingin dipesan: ");
        int nomorMenu = input.nextInt();

        if (nomorMenu >= 1 && nomorMenu <= daftarMenu.size()) {
            ItemMenu itemDipesan = daftarMenu.get(nomorMenu - 1);

            System.out.print("Masukkan jumlah porsi yang ingin dipesan: ");
            int jumlah = input.nextInt();

            Transaksi transaksi = new Transaksi(new Pelanggan(namaPelanggan, ""), itemDipesan, jumlah);
            transaksiList.add(transaksi);

            System.out.println(transaksi);
            System.out.println("Pesanan Anda telah berhasil disimpan.");
        } else {
            System.out.println("Nomor menu tidak valid.");
        }
    }

    private static void buatLaporan() {
        System.out.println("\n=== Laporan Catering ===");
        System.out.println("Laporan Penjualan Paket Makanan Berat:");

        for (ItemMenu itemMenu : daftarMenu) {
            if (itemMenu instanceof MakananBerat) {
                int totalTerjual = 0;
                int totalPendapatan = 0;

                for (Transaksi transaksi : transaksiList) {
                    if (transaksi.getItem() == itemMenu) {
                        totalTerjual += transaksi.getJumlah();
                        totalPendapatan += transaksi.getJumlah() * itemMenu.getHarga();
                    }
                }
                System.out.println(itemMenu.getNama() + ": Terjual " + totalTerjual + " paket, Total Pendapatan: Rp " + totalPendapatan);
            }
        }

        System.out.println("\nLaporan Penjualan Paket Makanan Ringan:");

        for (ItemMenu itemMenu : daftarMenu) {
            if (itemMenu instanceof MakananRingan) {
                int totalTerjual = 0;
                int totalPendapatan = 0;

                for (Transaksi transaksi : transaksiList) {
                    if (transaksi.getItem() == itemMenu) {
                        totalTerjual += transaksi.getJumlah();
                        totalPendapatan += transaksi.getJumlah() * itemMenu.getHarga();
                    }
                }

                System.out.println(itemMenu.getNama() + ": Terjual " + totalTerjual + " paket, Total Pendapatan: Rp " + totalPendapatan);
            }
        }
    }
}

class Pelanggan {
    private String namaPengguna;
    private String sandi;

    public Pelanggan(String namaPengguna, String sandi) {
        this.namaPengguna = namaPengguna;
        this.sandi = sandi;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getSandi() {
        return sandi;
    }
}

abstract class ItemMenu {
    private String nama, isi;
    private int harga;

    public ItemMenu(String nama, String isi, int harga) {
        this.nama = nama;
        this.isi = isi;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public String getIsi() {
        return isi;
    }

    public int getHarga() {
        return harga;
    }
}

class MakananBerat extends ItemMenu {
    public MakananBerat(String nama, String isi, int harga) {
        super(nama, isi, harga);
    }
}

class MakananRingan extends ItemMenu {
    public MakananRingan(String nama, String isi, int harga) {
        super(nama, isi, harga);
    }
}

class Transaksi {
    private Pelanggan pelanggan;
    private ItemMenu item;
    private int jumlah;

    public Transaksi(Pelanggan pelanggan, ItemMenu item, int jumlah) {
        this.pelanggan = pelanggan;
        this.item = item;
        this.jumlah = jumlah;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public ItemMenu getItem() {
        return item;
    }

    public int getJumlah() {
        return jumlah;
    }

    @Override
    public String toString() {
        return "Nama: " + pelanggan.getNamaPengguna() + "\n" 
                + "Jumlah: " + jumlah + "x " + item.getNama()
                + "\nHarga: " + " Rp " + item.getHarga()
                + "\nTotal Harga: " + item.getHarga() * jumlah;
    }
}
