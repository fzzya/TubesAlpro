import java.util.ArrayList;
import java.util.Scanner;

class CateringSystem {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static ArrayList<MenuItem> menuItems = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        initializeData();
        login();

        while (true) {
            showMenu();
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
                    generateReports();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan aplikasi.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void initializeData() {
        users.add(new User(ADMIN_USERNAME, ADMIN_PASSWORD));

        menuItems.add(new HeavyMeal("Paket Berat A", 30000));
        menuItems.add(new HeavyMeal("Paket Berat B", 35000));
        menuItems.add(new HeavyMeal("Paket Berat C", 40000));
        menuItems.add(new LightMeal("Paket Ringan A", 15000));
        menuItems.add(new LightMeal("Paket Ringan B", 20000));
        menuItems.add(new LightMeal("Paket Ringan C", 25000));
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
                System.out.println("Login berhasil. Selamat datang, " + username + "!");
                return;
            }
        }

        System.out.println("Login gagal. Silakan coba lagi.");
        System.exit(0);
    }

    private static void showMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Lihat Menu");
        System.out.println("2. Pesan Catering");
        System.out.println("3. Lihat Transaksi");
        System.out.println("4. Lihat Laporan");
        System.out.println("5. Keluar");
    }

    private static int getChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan pilihan Anda: ");
        return scanner.nextInt();
    }

    private static void viewMenu() {
        System.out.println("\n=== Menu Catering ===");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i).getName() + " - Rp " + menuItems.get(i).getPrice());
        }
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
            System.out.println("Pesanan Anda telah berhasil ditempatkan.");
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
                        totalRevenue += transaction.getQuantity() * menuItem.getPrice();
                    }
                }

                System.out.println(menuItem.getName() + ": Terjual " + totalSold + " paket, Total Pendapatan: Rp " + totalRevenue);
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
                        totalRevenue += transaction.getQuantity() * menuItem.getPrice();
                    }
                }

                System.out.println(menuItem.getName() + ": Terjual " + totalSold + " paket, Total Pendapatan: Rp " + totalRevenue);
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
    private String name;
    private int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

class HeavyMeal extends MenuItem {
    public HeavyMeal(String name, int price) {
        super(name, price);
    }
}

class LightMeal extends MenuItem {
    public LightMeal(String name, int price) {
        super(name, price);
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
        return user.getUsername() + " - " + quantity + "x " + item.getName() + " - Rp " + item.getPrice() * quantity;
    }
}
