package main;

import java.util.Date;

public class Client extends User {
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
        return ActionResult.SUCCESS;
    }

    ActionResult revokeOrder(int id) {
        return ActionResult.SUCCESS;
    }

}
