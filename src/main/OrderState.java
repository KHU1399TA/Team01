package main;

public enum OrderState {
    MADE("The order has been made"), CONFIRMED("The order has been confirmed"), COOKED("The order has been cooked!"),
    DELIVERED("The order has been delivered!");

    String state;

    OrderState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}