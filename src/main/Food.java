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
        String ing = "";
        for (int i = 0; i < ingredients.length; i++) {
            ing += ingredients[i] + " ";
        }
        String avb;
        if (isAvailable)
            avb = "Available";
        else
            avb = "Not Available";
        return id + " " + name + " ( " + ing + ") " + avb + " " + price + "$";
    }

}
