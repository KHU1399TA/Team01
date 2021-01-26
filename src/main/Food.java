package main;

import java.util.ArrayList;

public class Food {
    int id;
    String name;
    ArrayList<String> Ingredients;
    boolean isAvailable;

    public Food(int id, String name, ArrayList<String> Ingredients, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.Ingredients = Ingredients;
        this.isAvailable = isAvailable;
    }

}
