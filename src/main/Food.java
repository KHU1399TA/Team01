package main;

import java.util.ArrayList;

public class Food {
    int id;
    String name;
    String[] ingredients;
    boolean isAvailable;
    int price;

    public Food(int id, String name, String[] ingredients, boolean isAvailable, int price) {

        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    @Override
    public String toString() {
        String ingred = "";

        for (int i = 0; i < ingredients.length; i++) {
            ingred += ingredients[i] + " ";
        }

        String availability;

        if (isAvailable)
            availability = "Available";
        else
            availability = "Not Available";

        return id + " " + name + " ( " + ingred + ") " + availability + " " + price + "$";
    }

}
