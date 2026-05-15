/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kauessen;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mohd
 */
public class Dashboard {
    private static final String FILE_NAME = "orders.txt";

    public void displayAllOrders() {
        List<String[]> parsedOrders = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) {
            System.out.println("\n[No orders found in the system yet.]");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                parsedOrders.add(line.split("\\|"));
            }
        } catch (IOException e) {
            System.out.println("Error reading orders.");
            return;
        }

        // Sort orders by Pickup Time (Index 3 in the split array)
        parsedOrders.sort((o1, o2) -> {
            LocalTime t1 = LocalTime.parse(o1[3]);
            LocalTime t2 = LocalTime.parse(o2[3]);
            return t1.compareTo(t2);
        });

        System.out.println("\n============= DASHBOARD =========================================================");
        System.out.printf("%-10s | %-15s | %-10s | %-10s | %-7s | %s\n", 
                "Order ID", "Student Name", "Univ ID", "Pickup", "Total", "Items");
        System.out.println("---------------------------------------------------------------------------------");
        
        for (String[] o : parsedOrders) {
            System.out.printf("%-10s | %-15s | %-10s | %-10s | $%-6s | %s\n", 
                    o[0], o[1], o[2], o[3], o[4], o[5]);
        }
        System.out.println("=================================================================================\n");
    }
}
