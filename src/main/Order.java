package main;

import java.util.Date;

public class Order {
    int id;
    String username;
    int foodId;
    OrderState state = OrderState.MADE;
    Date orderedAt;

   
}
