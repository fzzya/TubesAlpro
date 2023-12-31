import java.util.ArrayList;
import java.util.Scanner;

class CateringSystem {
    private static String AdminUsername = "admin";
    private static String AdminPassword = "admin";

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static ArrayList<MenuItem> menuItems = new ArrayList<>();
    private static User currentUser;
    Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        initializeData();
        CateringSystem p = new CateringSystem();
        p.menu();
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        int pili;
        do{
            System.out.println("\t ===== MENU UTAMA ======");
            System.out.println("1. Admin");
            System.out.println("2. Pelanggan");
            System.out.println("3. Keluar");
            System.out.print("Pilihan : ");
            pili = input.nextInt();

            switch (pili) {
                case 1:
                    adminLogin(input);
                    break;
                case 2:
                    menuUser();
                    break;
                default:
                    break;
            }
        } while (pili != 3);
    }

    public static void adminLogin(Scanner input) {
        System.out.print("Masukkan username admin: ");
        String username = input.next();
        System.out.print("Masukkan password admin: ");
        String password = input.next();

        if (username.equals(AdminUsername) && password.equals(AdminPassword)) {
            currentUser = new User(username, password);
            System.out.println("Login admin berhasil. Selamat datang, " + username + "!");
            menuAdmin(input);
        } else {
            System.out.println("Login admin gagal. Silakan coba lagi.");
        }
    }

    private static void menuAdmin(Scanner input) {
        System.out.println("\n=== Menu Admin ===");
        System.out.println("1. Lihat Menu");
        System.out.println("2. Tambah Menu");
        System.out.println("3. Hapus Menu");
        System.out.println("4. Lihat Laporan");
        System.out.println("5. Kembali");
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
                menu();
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
        System.out.println("4. Kembali");
        System.out.println("5. Keluar");

        int choice = getChoice();

        switch (choice) {
            case 1:
                viewMenu();
                break;
            case 2:
                placeOrder();
                return;
            case 3:
                viewTransactions();
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
        Scanner input = new Scanner(System.in);
        viewMenu();
        System.out.print("Pilih nomor menu makanan yang ingin dihapus: ");
        int menuNumber = input.nextInt();

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
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama menu makanan berat: ");
        String nama = input.nextLine();
        System.out.print("Masukkan isi menu makanan berat: ");
        String isi = input.nextLine();
        System.out.print("Masukkan harga menu makanan berat: ");
        int harga = input.nextInt();

        menuItems.add(new HeavyMeal(nama, isi, harga));

        System.out.println("Menu makanan berat berhasil ditambahkan.");
    }

    private static void addLightMenuItem() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama menu makanan ringan: ");
        String nama = input.nextLine();
        System.out.print("Masukkan isi menu makanan ringan: ");
        String isi = input.nextLine();
        System.out.print("Masukkan harga menu makanan ringan: ");
        int harga = input.nextInt();

        menuItems.add(new LightMeal(nama, isi, harga));

        System.out.println("Menu makanan ringan berhasil ditambahkan.");
    }

    private static void initializeData() {
        users.add(new User(AdminUsername, AdminPassword));

        menuItems.add(new HeavyMeal("Paket Berat Lapar","Nasi, Ayam Lalapan", 30000));
        menuItems.add(new HeavyMeal("Paket Berat Kenyang","Nasi, Bebek Lalapan ", 35000));
        menuItems.add(new HeavyMeal("Paket Berat Begahh","Nasi, Rendang, & Sayur Singkong", 40000));
        
        menuItems.add(new LightMeal("Paket Ringan Kue", "Klepon & Kue Lapis", 15000));
        menuItems.add(new LightMeal("Paket Ringan Gorengan","Onde-onde & Pastel",  20000));
        menuItems.add(new LightMeal("Paket Ringan Campur",", Pie Buah", 25000));
    }

    // private static void login() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Masukkan username: ");
    //     String username = scanner.nextLine();
    //     System.out.print("Masukkan password: ");
    //     String password = scanner.nextLine();
    //     for (User user : users) {
    //         if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
    //             currentUser = user;
    //             System.out.println("Login berhasil." + "\nSelamat Datang di F2D Catering, " + username + "! ^^");
    //             return;
    //         }
    //     }
    //     System.out.println("Login gagal. Silakan coba lagi.");   
    // }

    private static int getChoice() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan pilihan Anda: ");
        return input.nextInt();
    }

    private static void placeOrder() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama Anda: ");
        String customerName = input.next();

        viewMenu();
        System.out.print("Pilih nomor menu yang ingin dipesan: ");
        int menuNumber = input.nextInt();

        if (menuNumber >= 1 && menuNumber <= menuItems.size()) {
            MenuItem selectedItem = menuItems.get(menuNumber - 1);

            System.out.print("Masukkan jumlah porsi yang ingin dipesan: ");
            int quantity = input.nextInt();

            Transaction transaction = new Transaction(new User(customerName, ""), selectedItem, quantity);
            transactions.add(transaction);

            System.out.println(transaction);
            System.out.println("Pesanan Anda telah berhasil disimpan.");
        } else {
            System.out.println("Nomor menu tidak valid.");
        }

        menuUser();
    }

    public static void viewTransactions() {
        System.out.println("\n=== Transaksi Catering ===");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
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