package main;

import java.util.Date;

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

    Resault deliver(String id, Restaurant restaurant) {
        Resault resault = new Resault();
        resault.restaurant = restaurant;
        for (int i = 0; i < restaurant.orders.size(); i++) {
            if (restaurant.orders.get(i).id.equals(id)) {

                if (restaurant.orders.get(i).state == OrderState.COOKED) {
                    restaurant.orders.get(i).state = OrderState.DELIVERED;
                    resault.restaurant = restaurant;
                    resault.actionResult = ActionResult.SUCCESS;
                    return resault;
                } else {
                    resault.actionResult = ActionResult.ORDER_NOT_COOKED;
                    return resault;
                }
            }
        }
        resault.actionResult = ActionResult.ORDER_NOT_FOUND;
        return resault;
    }
}
