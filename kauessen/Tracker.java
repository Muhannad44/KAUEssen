/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kauessen;

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Tracker {
    private static final String FILE_NAME = "orders.txt";
    private Set<String> usedIds;
    private Random random;

    public Tracker() {
        usedIds = new HashSet<>();
        random = new Random();
        loadExistingIds();
    }

    // Ensures IDs don't repeat even after program restarts
    private void loadExistingIds() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    usedIds.add(parts[0]); // Order ID is the first element
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading previous order IDs.");
        }
    }

    public void processOrder(Order order) {
        String uniqueId = generateUniqueId();
        order.setOrderId(uniqueId);
        saveOrderToFile(order);
        printReceipt(order);
    }

    private String generateUniqueId() {
        String newId;
        do {
            int num = 100000 + random.nextInt(900000); // 6-digit number
            newId = String.valueOf(num);
        } while (usedIds.contains(newId));
        
        usedIds.add(newId);
        return newId;
    }

    private void saveOrderToFile(Order order) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            // Format: OrderID|StudentName|UnivID|PickupTime|Total|Item1xQty,Item2xQty
            StringBuilder itemsStr = new StringBuilder();
            for (OrderItem oi : order.getOrderItems()) {
                itemsStr.append(oi.getItem().getName()).append("x").append(oi.getQuantity()).append(",");
            }
            
            out.printf("%s|%s|%s|%s|%.2f|%s\n",
                    order.getOrderId(),
                    order.getStudent().getName(),
                    order.getStudent().getUniversityId(),
                    order.getPickupTime(),
                    order.calculateTotal(),
                    itemsStr.toString()
            );
        } catch (IOException e) {
            System.out.println("Error saving order to file.");
        }
    }

    private void printReceipt(Order order) {
        System.out.println("\n===================================");
        System.out.println("          ORDER RECEIPT            ");
        System.out.println("===================================");
        System.out.println("Order ID   : " + order.getOrderId());
        System.out.println("Student    : " + order.getStudent().getName());
        System.out.println("Univ ID    : " + order.getStudent().getUniversityId());
        System.out.println("Pickup Time: " + order.getPickupTime());
        System.out.println("-----------------------------------");
        for (OrderItem oi : order.getOrderItems()) {
            System.out.printf("%-20s x%d  $%.2f\n", oi.getItem().getName(), oi.getQuantity(), oi.getTotalPrice());
        }
        System.out.println("-----------------------------------");
        System.out.printf("TOTAL PRICE:                  $%.2f\n", order.calculateTotal());
        System.out.println("===================================\n");
    }
}

