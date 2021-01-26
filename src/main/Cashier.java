package main;

import java.util.Date;
import java.util.ArrayList;

public class Cashier extends User {

    public Cashier(String firstName, String lastName, String phoneNumber, String username, String password,
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

    ActionResult ConfirmOrder(int id) {
        int foodId = 0;

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).id == id) {

                foodId = orders.get(i).foodId;

                for (int j = 0; j < menu.size(); j++) {
                    if (menu.get(j).id == foodId) {
                        if (orders.get(i).state == OrderState.MADE) {

                            orders.get(i).state = OrderState.CONFIRMED;

                            return ActionResult.SUCCESS;
                        } else
                            return ActionResult.ORDER_NOT_MADE;
                    }
                }
                return ActionResult.FOOD_NOT_FOUND;
            }
        }

        return ActionResult.ORDER_NOT_FOUND;
    }
}
