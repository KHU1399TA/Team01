package main;

import java.security.interfaces.RSAKey;
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

	Resault register(User user, Restaurant restaurant) {
		Resault resault = new Resault();
		resault.restaurant = restaurant;

		for (int i = 0; i < restaurant.users.size(); i++) {
			if (restaurant.users.get(i).username.equals(user.username)) {

				resault.actionResult = ActionResult.USERNAME_ALREADY_EXIST;
				return resault;
			}
		}
		for (int i = 0; i < user.username.length(); i++) {
			if (user.username.charAt(i) == ' ' || user.username.charAt(i) == '-') {

				resault.actionResult = ActionResult.INVALID_USERNAME;
				return resault;
			}
		}
		user.password = Integer.toString(user.password.hashCode());

		restaurant.users.add(user);

		resault.restaurant = restaurant;
		resault.actionResult = ActionResult.SUCCESS;
		return resault;
	}

	Resault edit(String username, String firstName, String lastName, String phoneNumber, String newPassword,
			AccessLevel newAccessLevel, Restaurant restaurant) {

		Resault resault = new Resault();
		resault.restaurant = restaurant;

		for (int i = 0; i < restaurant.users.size(); i++) {
			if (restaurant.users.get(i).username.equals(username)) {
				if (restaurant.users.get(i).accessLevel = AccessLevel.MANAGER) {
					resault.actionResult = ActionResult.NOT_MANAGER;
					return resault;
				}

				if (!firstName.equals("0"))
					restaurant.users.get(i).firstName = firstName;

				if (!lastName.equals("0"))
					restaurant.users.get(i).lastName = lastName;

				if (!phoneNumber.equals("0"))
					restaurant.users.get(i).phoneNumber = phoneNumber;

				if (!newPassword.equals("0"))
					restaurant.users.get(i).password = Integer.toString(newPassword.hashCode());

				restaurant.users.get(i).accessLevel = newAccessLevel;

				resault.restaurant = restaurant;
				resault.actionResult = ActionResult.SUCCESS;
				return resault;
			}
		}
		resault.actionResult = ActionResult.USERNAME_NOT_FOUND;
		return resault;
	}

	Resault remove(String username, Restaurant restaurant) {
		Resault resault = new Resault();
		resault.restaurant = restaurant;

		for (int i = 0; i < restaurant.users.size(); i++) {
			if (restaurant.users.get(i).username.equals(username)) {
				if (restaurant.users.get(i).accessLevel = AccessLevel.MANAGER) {
					resault.actionResult = ActionResult.NOT_MANAGER;
					return resault;
				}
				restaurant.users.remove(i);

				resault.restaurant = restaurant;
				resault.actionResult = ActionResult.SUCCESS;
				return resault;
			}
		}
		resault.actionResult = ActionResult.USERNAME_NOT_FOUND;
		return resault;
	}
}
