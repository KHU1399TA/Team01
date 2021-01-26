package main;

import java.util.Date;

public class Order {
    int id;
    String username;
    int foodId;
    OrderState state;
    Date orderDate;

    public Order(int id, String username, int foodId, OrderState state, Date orderDate) {
        this.id = id;
        this.username = username;
        this.foodId = foodId;
        this.state = state;
        this.orderDate = orderDate;
    }
}
