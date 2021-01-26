package main;

public class Main {
    public static void main(String[] args) {
        Readfile r = new Readfile();
        Restaurant restaurant = new Restaurant();
        restaurant = r.read(restaurant);
    }
}