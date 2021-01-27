package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatCodePointException;

public class Client extends User {
    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<Order> orders = new ArrayList<Order>();

    String address;

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

    ActionResult makeOrder(Order order) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(order.username)) {

                    orders.add(order);
                    order.state = OrderState.MADE;
                    order.orderDate = new Date();

                    return ActionResult.SUCCESS;
                }
            }

        return ActionResult.USERNAME_NOT_FOUND;
    }

    ActionResult revokeOrder(int id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).id == id ) {
                if (orders.get(i).state != OrderState.COOKED) {
                    orders.remove(id);
                    return ActionResult.SUCCESS;
                } else
                    return ActionResult.ORDER_COOKED;
            }
        }
       return ActionResult.ORDER_NOT_FOUND;
    }
}
