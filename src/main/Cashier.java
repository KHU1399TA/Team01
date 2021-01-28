package main;

import java.util.Date;

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

    Resault ConfirmOrder(String id, Restaurant restaurant) {
        Resault resault = new Resault();
        resault.restaurant = restaurant;

        int foodId;

        for (int i = 0; i < restaurant.orders.size(); i++) {
            if (restaurant.orders.get(i).id.equals(id)) {

                foodId = restaurant.orders.get(i).foodId;

                for (int j = 0; j < restaurant.menu.size(); j++) {
                    if (restaurant.menu.get(j).id == foodId) {
                        if (restaurant.menu.get(i).isAvailable) {

                            restaurant.orders.get(i).state = OrderState.CONFIRMED;

                            resault.restaurant = restaurant;
                            resault.actionResult = ActionResult.SUCCESS;

                            return resault;
                        } else {
                            resault.actionResult = ActionResult.FOOD_NOT_AVAILABLE;
                            return resault;
                        }
                    }
                }
            }
        }
        resault.actionResult = ActionResult.ORDER_NOT_FOUND;
        return resault;
    }
}
