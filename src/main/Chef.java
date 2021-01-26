package main;

public class Chef extends User {

    public Chef(String firstName, String lastName, String phoneNumber, String username, String password,
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

    ActionResult addFood(Food food) {
        menu.add(food);
        return ActionResult.SUCCESS;
    }

    ActionResult editFood(int id, Food newFood) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).id == id) {
                menu.set(i, newFood);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }

    ActionResult removeFood(int id) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).id == id) {
                menu.remove(i);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }

    ActionResult changeFoodStatus(int id, boolean isAvailable) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).id == id) {
                menu.get(i).isAvailable = isAvailable;
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }

    ActionResult cook(int id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).id == id) {
                if (orders.get(i).state == OrderState.CONFIRMED) {
                    orders.get(i).state = OrderState.COOKED;
                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.ORDER_NOT_CONFIRMED;
                }
            }
        }
        return ActionResult.ORDER_NOT_FOUND;
    }

}
