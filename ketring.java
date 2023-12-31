import java.util.ArrayList;
import java.util.Scanner;

class CateringSystem {
    private static String AdminUsername = "admin";
    private static String AdminPassword = "admin";

    private static String Username = "user";
    private static String Password = "user";

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static ArrayList<MenuItem> menuItems = new ArrayList<>();
    
    private static User currentUser;

    public static void main(String[] args) {
        initializeData();
        login();

        while (true) {
            if (currentUser.getUsername().equals(AdminUsername)) {
                menuAdmin();
            } else {
                menuUser();
            }
        }
    }

    private static void menuAdmin() {
        System.out.println("\n=== Menu Admin ===");
        System.out.println("1. Lihat Menu");
        System.out.println("2. Tambah Menu");
        System.out.println("3. Hapus Menu");
        System.out.println("4. Lihat Laporan");
        System.out.println("5. Login");
        System.out.println("6. Keluar");

        int choice = getChoice();

        switch (choice) {
            case 1:
                viewMenu();
                break;
            case 2:
                addMenu();
                break;
            case 3:
                deleteMenu();
                break;
            case 4:
                generateReports();
                break;
            case 5 :
                login();
                break;
            case 6:
                System.out.println("Terima kasih telah menggunakan aplikasi.");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    private static void menuUser() {
        System.out.println("\n=== Menu User ===");
        System.out.println("1. Lihat Menu");
        System.out.println("2. Pesan Catering");
        System.out.println("3. Lihat Struk Pemesanan");
        System.out.println("4. Login");
        System.out.println("5. Keluar");

        int choice = getChoice();

        switch (choice) {
            case 1:
                viewMenu();
                break;
            case 2:
                placeOrder();
                break;
            case 3:
                viewTransactions();
                break;
            case 4:
                login();
            case 5:
                System.out.println("Terima kasih telah menggunakan aplikasi F2D Catering.");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    private static void viewMenu() {
        System.out.println("\n=== Menu Makanan Berat ===");
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i) instanceof HeavyMeal) {
                System.out.println((i + 1) + ". " + menuItems.get(i).getNama() + "\n   Isi : "
                        + menuItems.get(i).getIsi() + "\n   Harga : Rp " + menuItems.get(i).getHarga());
            }
        }

        System.out.println("\n=== Menu Makanan Ringan ===");
        for (int j = 0; j < menuItems.size(); j++) {
            if (menuItems.get(j) instanceof LightMeal) {
                System.out.println((j +1) + ". " + menuItems.get(j).getNama() + "\n   Isi : "
                        + menuItems.get(j).getIsi() + "\n   Harga : Rp " + menuItems.get(j).getHarga());
            }
        }
    }

    private static void addMenu() {
        System.out.println("\n=== Menu Catering ===");
        System.out.println("1. Makanan Berat");
        System.out.println("2. Makanan Ringan");
        int choice = getChoice();

        switch (choice) {
            case 1:
                addHeavyMenuItem();
                break;
            case 2:
                addLightMenuItem();
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    private static void deleteMenu() {
        Scanner scanner = new Scanner(System.in);
        viewMenu();
        System.out.print("Pilih nomor menu makanan yang ingin dihapus: ");
        int menuNumber = scanner.nextInt();

        if (menuNumber >= 1 && menuNumber <= menuItems.size()) {
            if (menuItems.get(menuNumber - 1) instanceof HeavyMeal) {
                menuItems.remove(menuNumber - 1);
                System.out.println("Menu makanan berhasil dihapus.");
            } else {
                System.out.println("Nomor menu tidak valid.");
            }
        } else {
            System.out.println("Nomor menu tidak valid.");
        }
    }



    private static void addHeavyMenuItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama menu makanan berat: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan isi menu makanan berat: ");
        String isi = scanner.nextLine();
        System.out.print("Masukkan harga menu makanan berat: ");
        int harga = scanner.nextInt();

        menuItems.add(new HeavyMeal(nama, isi, harga));

        System.out.println("Menu makanan berat berhasil ditambahkan.");
    }

    private static void addLightMenuItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama menu makanan ringan: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan isi menu makanan ringan: ");
        String isi = scanner.nextLine();
        System.out.print("Masukkan harga menu makanan ringan: ");
        int harga = scanner.nextInt();

        menuItems.add(new LightMeal(nama, isi, harga));

        System.out.println("Menu makanan ringan berhasil ditambahkan.");
    }

    private static void initializeData() {
        users.add(new User(AdminUsername, AdminPassword));
        users.add(new User(Username, Password));

        menuItems.add(new HeavyMeal("Paket Berat Lapar","Nasi, Ayam Lalapan", 30000));
        menuItems.add(new HeavyMeal("Paket Berat Kenyang","Nasi, Bebek Lalapan ", 35000));
        menuItems.add(new HeavyMeal("Paket Berat Begahh","Nasi, Rendang, & Sayur Singkong", 40000));
        
        menuItems.add(new LightMeal("Paket Ringan Kue", "Klepon & Kue Lapis", 15000));
        menuItems.add(new LightMeal("Paket Ringan Gorengan","Onde-onde & Pastel",  20000));
        menuItems.add(new LightMeal("Paket Ringan Campur",", Pie Buah", 25000));
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login berhasil." + "\nSelamat Datang di F2D Catering, " + username + "! ^^");
                return;
            }
        }

        System.out.println("Login gagal. Silakan coba lagi.");
        
    }

    private static int getChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan pilihan Anda: ");
        return scanner.nextInt();
    }

    private static void placeOrder() {
        Scanner scanner = new Scanner(System.in);
        viewMenu();
        System.out.print("Pilih nomor menu yang ingin dipesan: ");
        int menuNumber = scanner.nextInt();

        if (menuNumber >= 1 && menuNumber <= menuItems.size()) {
            MenuItem selectedItem = menuItems.get(menuNumber - 1);

            System.out.print("Masukkan jumlah porsi yang ingin dipesan: ");
            int quantity = scanner.nextInt();

            Transaction transaction = new Transaction(currentUser, selectedItem, quantity);
            transactions.add(transaction);

            //TRAMBAHKAAN TOTAL HARGA
            System.out.println(transaction);
            System.out.println("Pesanan Berhasil Dibuat.");
        } else {
            System.out.println("Nomor menu tidak valid.");
        }
    }

    private static void viewTransactions() {
        System.out.println("\n=== Transaksi Catering ===");
        for (Transaction transaction : transactions) {
            if (transaction.getUser().equals(currentUser)) {
                System.out.println(transaction);
            }
        }
    }

    private static void generateReports() {
        System.out.println("\n=== Laporan Catering ===");
        System.out.println("Laporan Penjualan Paket Makanan Berat:");

        for (MenuItem menuItem : menuItems) {
            if (menuItem instanceof HeavyMeal) {
                int totalSold = 0;
                int totalRevenue = 0;

                for (Transaction transaction : transactions) {
                    if (transaction.getItem() == menuItem) {
                        totalSold += transaction.getQuantity();
                        totalRevenue += transaction.getQuantity() * menuItem.getHarga();
                    }
                }
                System.out.println(menuItem.getNama() + ": Terjual " + totalSold + " paket, Total Pendapatan: Rp " + totalRevenue);
            }
        }

        System.out.println("\nLaporan Penjualan Paket Makanan Ringan:");

        for (MenuItem menuItem : menuItems) {
            if (menuItem instanceof LightMeal) {
                int totalSold = 0;
                int totalRevenue = 0;

                for (Transaction transaction : transactions) {
                    if (transaction.getItem() == menuItem) {
                        totalSold += transaction.getQuantity();
                        totalRevenue += transaction.getQuantity() * menuItem.getHarga();
                    }
                }

                System.out.println(menuItem.getNama() + ": Terjual " + totalSold + " paket, Total Pendapatan: Rp " + totalRevenue);
            }
        }
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

abstract class MenuItem {
    private String nama, isi;
    private int harga;
    

    public MenuItem(String nama, String isi, int harga) {
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

class HeavyMeal extends MenuItem {
    public HeavyMeal(String nama,String isi, int harga) {
        super(nama,isi, harga);
    }
}

class LightMeal extends MenuItem {
    public LightMeal(String nama, String isi, int harga) {
        super(nama, isi,  harga);
    }
}

class Transaction {
    private User user;
    private MenuItem item;
    private int quantity;
    private int total;

    public Transaction(User user, MenuItem item, int quantity) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return user.getUsername() + " - " + quantity + "x " + item.getNama() + item.getIsi() + " - Rp " + item.getHarga() * quantity;
    }
}
