package main;

public enum OrderState {
    MADE("Your order has been made"),
    CONFIRMED("Your order has been confirmed"),
    COOKED("Your order has been cocked!"),
    DELIVERED("Your order has been delivered!");

    String state;

    OrderState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}