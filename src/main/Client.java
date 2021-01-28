package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatCodePointException;

public class Client extends User {
    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<Order> orders = new ArrayList<Order>();

    String address;
    Resault resault = new Resault();

    public Client(String firstName, String lastName, String phoneNumber, String username, String password,
            AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.registrationDate = registrationDate;
        this.lastLoginDate = lastLoginDate;
        this.address = address;
    }

    Resault makeOrder(Order order, Restaurant restaurant) {
        resault.restaurant = restaurant;
        for (int i = 0; i < restaurant.orders.size(); i++) {
            if (restaurant.orders.get(i).id.equals(order.id)) {
                resault.actionResult = ActionResult.ORDER_ALREADY_EXIST;
                return resault;
            }
        }
        for (int i = 0; i < restaurant.menu.size(); i++) {
            if (restaurant.menu.get(i).id == order.foodId) {
                restaurant.orders.add(order);
                resault.restaurant = restaurant;
                resault.actionResult = ActionResult.SUCCESS;
                return resault;
            }
        }
        resault.actionResult = ActionResult.FOOD_NOT_FOUND;
        return resault;
    }

    Resault revokeOrder(String id, Restaurant restaurant, String username) {
        resault.restaurant = restaurant;
        for (int i = 0; i < restaurant.orders.size(); i++) {
            if (restaurant.orders.get(i).id.equals(id) && restaurant.orders.get(i).username.equals(username)) {
                if (restaurant.orders.get(i).state != OrderState.COOKED) {
                    restaurant.orders.remove(i);
                    resault.restaurant = restaurant;
                    resault.actionResult = ActionResult.SUCCESS;
                    return resault;
                } else {
                    resault.actionResult = ActionResult.ORDER_COOKED;
                    return resault;
                }
            }
        }
        resault.actionResult = ActionResult.ORDER_NOT_FOUND;
        return resault;
    }
}
