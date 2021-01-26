package main;

public enum AccessLevel {
    MANAGER (1),
    CASHIER(2),
    CHEF(3) ,
    DELIVERYMAN(4) ,
    CLIENT(5);

    int id;

    AccessLevel(int id) {
        this.id = id;
    }
}

