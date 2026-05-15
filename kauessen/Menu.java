/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kauessen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private String menuName;
    private List<MenuItem> items;

    // The constructor now takes a name so we can label it Breakfast or Lunch
    public Menu(String menuName) {
        this.menuName = menuName;
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void displayMenu() {
        System.out.println("\n--- " + menuName.toUpperCase() + " ---");
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.printf("%d. %s - $%.2f\n", (i + 1), item.getName(), item.getPrice());
        }
        System.out.println("0. Finish Ordering");
    }

    public MenuItem selectItem(Scanner scanner) {
        while (true) {
            System.out.print("Select an item number (or 0 to finish): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) return null;
            if (choice > 0 && choice <= items.size()) {
                return items.get(choice - 1);
            }
            System.out.println("Invalid selection. Please try again.");
        }
    }
}
