
package kauessen;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class KAUEssen {

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();
        Tracker tracker = new Tracker();
        Dashboard dashboard = new Dashboard();

        // --- 1. SETUP BREAKFAST MENU ---
        Menu breakfastMenu = new Menu("Breakfast Menu (6 AM - 11 AM)");
        breakfastMenu.addItem(new MenuItem("Pancakes & Syrup", 8.00));
        breakfastMenu.addItem(new MenuItem("Cheese Omelet", 10.00));
        breakfastMenu.addItem(new MenuItem("Hash Browns", 4.00));
        breakfastMenu.addItem(new MenuItem("Hot Black Coffee", 5.00));

        // --- 2. SETUP LUNCH MENU ---
        Menu lunchMenu = new Menu("Lunch Menu (11 AM onwards)");
        lunchMenu.addItem(new MenuItem("Beef Burger", 15.00));
        lunchMenu.addItem(new MenuItem("Chicken Shawarma", 12.00));
        lunchMenu.addItem(new MenuItem("French Fries", 5.00));
        lunchMenu.addItem(new MenuItem("Cold Cola", 3.00));

        System.out.println("Welcome to the University Cafeteria System!");
        
        while (true) {
            System.out.println("\n1. Place a new order");
            System.out.println("2. View Dashboard (All Orders)");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            if (mainChoice == 1) {
                // Step A: Identify Student
                System.out.print("Enter your Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter your University ID: ");
                String uniId = scanner.nextLine();
                
                Student student = new Student(name, uniId);
                Order order = new Order(student);

                // Step B: DETECT REAL CURRENT TIME
                LocalTime currentTime = LocalTime.now();
                int currentHour = currentTime.getHour();
                int currentMinute = currentTime.getMinute();
                
                System.out.printf("\n[System] Current Time Detected: %02d:%02d\n", currentHour, currentMinute);

                // Step C: Automatically pick the menu based on the real time
                Menu activeMenu;
                if (currentHour >= 6 && currentHour < 11) {
                    System.out.println("--> Good morning! Serving the Breakfast Menu.");
                    activeMenu = breakfastMenu;
                } else if (currentHour >= 11 && currentHour <= 23) {
                    System.out.println("--> Good day! Serving the Lunch Menu.");
                    activeMenu = lunchMenu;
                } else {
                    // If they order between midnight and 6 AM
                    System.out.println("--> Sorry, the cafeteria is currently closed (Midnight to 6 AM).");
                    continue; // Send them back to the main menu
                }

                // Step D: Show the correct menu and take order
                while (true) {
                    activeMenu.displayMenu();
                    MenuItem selectedItem = activeMenu.selectItem(scanner);
                    
                    if (selectedItem == null) break; // User entered 0
                    
                    System.out.print("Enter quantity for " + selectedItem.getName() + ": ");
                    int qty = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (qty > 0) {
                        order.addItem(selectedItem, qty);
                        System.out.println("Added to order.");
                    }
                }

                if (order.isEmpty()) {
                    System.out.println("Order cancelled. No items selected.");
                    continue; // Send them back to the main menu
                }

                // Step E: Pick a pickup time
                String pickupTime = scheduler.pickTimeSlot(scanner);
                order.setPickupTime(pickupTime);

                // Step F: Save and Print Receipt
                tracker.processOrder(order);

            } else if (mainChoice == 2) {
                dashboard.displayAllOrders();
            } else if (mainChoice == 3) {
                System.out.println("Exiting system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}