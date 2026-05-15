/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kauessen;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Scheduler {
    // 06:00 to 10:30 is Breakfast. 11:00 onwards is Lunch.
    private List<String> timeSlots = Arrays.asList(
        "06:00", "07:00", "08:30", "09:00", "10:30", // Breakfast slots
        "11:00", "12:00", "13:30", "14:00", "15:00"  // Lunch slots
    );

    public String pickTimeSlot(Scanner scanner) {
        System.out.println("\n--- SELECT PICKUP TIME ---");
        System.out.println("(Breakfast: 06:00 - 10:30 | Lunch: 11:00+ )");
        for (int i = 0; i < timeSlots.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), timeSlots.get(i));
        }

        while (true) {
            System.out.print("Select a time slot number: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice > 0 && choice <= timeSlots.size()) {
                return timeSlots.get(choice - 1);
            }
            System.out.println("Invalid selection. Try again.");
        }
    }
}
