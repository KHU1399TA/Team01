package main;

public enum AccessLevel {
    MANAGER("Manager"), CHEF("Chef"), CASHIER("Cashier"),
    DELIVERYMAN("Deliveryman"), CLIENT("Client");

    String id;

    AccessLevel(String id) {
        this.id = id;
    }
}
