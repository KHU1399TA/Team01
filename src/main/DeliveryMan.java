package main;

import java.util.Date;
import java.util.ArrayList;

public class DeliveryMan extends User {

    public DeliveryMan(String firstName, String lastName, String phoneNumber, String username, String password,
            AccessLevel accessLevel, Date registrationDate, Date lastLoginDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.registrationDate = registrationDate;
        this.lastLoginDate = lastLoginDate;
    }

    ActionResult deliver(int id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).id == id) {

                if (orders.get(i).state == OrderState.COOKED) {

                    orders.get(i).state = OrderState.DELIVERED;

                    return ActionResult.SUCCESS;
                } else
                    return ActionResult.ORDER_NOT_COOKED;
            }
        }

        return ActionResult.ORDER_NOT_FOUND;
    }
}
