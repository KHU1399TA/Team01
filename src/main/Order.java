package main;

import java.util.Date;

public class Order {
    String id;
    String username;
    int foodId;
    int numbers;
    OrderState state;
    Date orderDate;
    String address;

    public Order(String id, String username, int foodId, int numbers, OrderState state, Date orderDate,
            String address) {

        this.id = id;
        this.username = username;
        this.foodId = foodId;
        this.numbers = numbers;
        this.state = state;
        this.orderDate = orderDate;
        this.address = address;
    }

    public String userOrders() {
        return id + " " + foodId + " " + numbers + " " + state + " (" + orderDate + ") " + address;
    }

    @Override
    public String toString() {
        return id + " " + username + " " + foodId + " " + numbers + " " + state + " (" + orderDate + ") " + address;
    }
}
