package main;

import java.util.Date;

public class Manager extends User {

    public Manager(String firstName, String lastName, String phoneNumber, String username, String password,
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

    ActionResult register(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username == user.username) {
                return ActionResult.USERNAME_ALREADY_EXIST;
            }
        }
        for (int i = 0; i < user.username.length(); i++) {
            if (user.username.charAt(i) == ' ' || user.username.charAt(i) == '-') {
                return ActionResult.INVALID_USERNAME;
            }
        }
        users.add(user);
        return ActionResult.SUCCESS;
    }

    ActionResult edit(String username, String firstName, String lastName, String phoneNumber, String newUsername,
            String newPassword, AccessLevel newAccessLevel) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username == username) {
                users.get(i).firstName = firstName;
                users.get(i).lastName = lastName;
                users.get(i).phoneNumber = phoneNumber;
                users.get(i).username = newUsername;
                users.get(i).password = Integer.toString(newPassword.hashCode());
                users.get(i).accessLevel = newAccessLevel;

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }

    ActionResult remove(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username == username) {
                users.remove(i);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }
}
