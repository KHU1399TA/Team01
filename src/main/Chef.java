package main;

import java.util.Date;

public class Chef extends User {

	Resault resault = new Resault();

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

	Resault addFood(Food food, Restaurant restaurant) {
		resault.restaurant = restaurant;
		for (int i = 0; i < restaurant.menu.size(); i++) {
			if (restaurant.menu.get(i).id == food.id) {
				resault.actionResult = ActionResult.FOOD_ALREADY_EXIST;
				return resault;
			}
		}
		restaurant.menu.add(food);
		resault.restaurant = restaurant;
		resault.actionResult = ActionResult.SUCCESS;
		return resault;
	}

	Resault editFood(int id, String name, String[] ingredients, int price, Restaurant restaurant) {
		resault.restaurant = restaurant;
		for (int i = 0; i < restaurant.menu.size(); i++) {
			if (restaurant.menu.get(i).id == id) {
				restaurant.menu.get(i).name = name;
				restaurant.menu.get(i).ingredients = ingredients;
				if (price != -1)
					restaurant.menu.get(i).price = price;
				resault.restaurant = restaurant;
				resault.actionResult = ActionResult.SUCCESS;
				return resault;
			}
		}
		resault.actionResult = ActionResult.FOOD_NOT_FOUND;
		return resault;
	}

	Resault removeFood(int id, Restaurant restaurant) {
		resault.restaurant = restaurant;
		for (int i = 0; i < restaurant.menu.size(); i++) {
			if (restaurant.menu.get(i).id == id) {
				restaurant.menu.remove(i);
				resault.restaurant = restaurant;
				resault.actionResult = ActionResult.SUCCESS;
				return resault;
			}
		}
		resault.actionResult = ActionResult.FOOD_NOT_FOUND;
		return resault;
	}

	Resault changeFoodStatus(int id, Restaurant restaurant) {
		resault.restaurant = restaurant;
		for (int i = 0; i < menu.size(); i++) {
			if (restaurant.menu.get(i).id == id) {
				restaurant.menu.get(i).isAvailable = !restaurant.menu.get(i).isAvailable;
				resault.actionResult = ActionResult.SUCCESS;
				return resault;
			}
		}
		resault.actionResult = ActionResult.FOOD_NOT_FOUND;
		return resault;
	}

	Resault cook(String id, Restaurant restaurant) {
		resault.restaurant = restaurant;
		for (int i = 0; i < restaurant.orders.size(); i++) {
			if (restaurant.orders.get(i).id.equals(id)) {
				if (restaurant.orders.get(i).state == OrderState.CONFIRMED) {
					restaurant.orders.get(i).state = OrderState.COOKED;
					resault.actionResult = ActionResult.SUCCESS;
					return resault;
				} else {
					resault.actionResult = ActionResult.ORDER_NOT_CONFIRMED;
					return resault;
				}
			}
		}
		resault.actionResult = ActionResult.ORDER_NOT_FOUND;
		return resault;
	}
}