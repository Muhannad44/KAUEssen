/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kauessen;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mohd
 */
public class Order {
   private Student student;
    private List<OrderItem> orderItems;
    private String orderId;
    private String pickupTime;

    public Order(Student student) {
        this.student = student;
        this.orderItems = new ArrayList<>();
    }

    public void addItem(MenuItem item, int quantity) {
        orderItems.add(new OrderItem(item, quantity));
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public boolean isEmpty() { return orderItems.isEmpty(); }
    
    // Getters and Setters
    public Student getStudent() { return student; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getPickupTime() { return pickupTime; }
    public void setPickupTime(String pickupTime) { this.pickupTime = pickupTime; }
}
