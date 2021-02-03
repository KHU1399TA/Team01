package main;

import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

import java.text.ParseException;

public class Main {
    static int currentUserindex;
    static User currentUser;
    static int cuAccLvl;

    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);

        File r = new File();

        Restaurant restaurant = new Restaurant();
        restaurant = r.read(restaurant);

        int action;
        String username, password;
        ActionResult loginaction;

        do {
            System.out.println("What you want to do?1.Login 2.Register 3.Exit");
            action = input.nextInt();

            cuAccLvl = 0;

            switch (action) {
                case 1:
                    while (cuAccLvl == 0) {

                        System.out.println("Enter your username:");
                        username = input.next();

                        System.out.println("Enter your password:");
                        password = input.next();

                        loginaction = login(username, password, restaurant);

                        System.out.println(loginaction);

                        if (loginaction == ActionResult.SUCCESS) {

                            restaurant.users.get(currentUserindex).lastLoginDate = java.util.Calendar.getInstance()
                                    .getTime();

                            System.out.println("Welcome " + currentUser.firstName + " " + currentUser.lastName);
                        } else {
                            cuAccLvl = 0;
                            System.out.println("Please try again");
                        }
                    }

                    int job;
                    String info;
                    String uname;
                    int lvl;
                    AccessLevel acclvl;
                    Resault res;

                    switch (cuAccLvl) {
                        case 1:
                            Manager manager = (Manager) currentUser;

                            do {
                                System.out.println(
                                        "What you want to do?1.Register a User 2.Edit a user 3.Remove a User 4.Logout");
                                job = input.nextInt();

                                switch (job) {
                                    case 1 -> {
                                        showUsers(restaurant);

                                        System.out.println(
                                                "Enter user's information in this order:Firstname Lastname Phonenumber Username Password ");
                                        input.nextLine();
                                        info = input.nextLine();
                                        String[] newUser = info.split(" ");

                                        System.out.println(
                                                "Enter user's role:1.Manager 2.Chef 3.Cashier 4.Deliveryman 5.Client");
                                        lvl = input.nextInt();

                                        User u;
                                        switch (lvl) {
                                            case 1 -> {
                                                acclvl = AccessLevel.MANAGER;
                                                u = new Manager(newUser[0], newUser[1], newUser[2], newUser[3],
                                                        newUser[4], acclvl, java.util.Calendar.getInstance().getTime(),
                                                        java.util.Calendar.getInstance().getTime());
                                            }
                                            case 2 -> {
                                                acclvl = AccessLevel.CHEF;
                                                u = new Chef(newUser[0], newUser[1], newUser[2], newUser[3], newUser[4],
                                                        acclvl, java.util.Calendar.getInstance().getTime(),
                                                        java.util.Calendar.getInstance().getTime());
                                            }
                                            case 3 -> {
                                                acclvl = AccessLevel.CASHIER;
                                                u = new Cashier(newUser[0], newUser[1], newUser[2], newUser[3],
                                                        newUser[4], acclvl, java.util.Calendar.getInstance().getTime(),
                                                        java.util.Calendar.getInstance().getTime());
                                            }
                                            case 4 -> {
                                                acclvl = AccessLevel.DELIVERYMAN;
                                                u = new DeliveryMan(newUser[0], newUser[1], newUser[2], newUser[3],
                                                        newUser[4], acclvl, java.util.Calendar.getInstance().getTime(),
                                                        java.util.Calendar.getInstance().getTime());
                                            }
                                            default -> {
                                                acclvl = AccessLevel.CLIENT;

                                                System.out.println("Enter user's address:");
                                                input.nextLine();
                                                String add = input.nextLine();

                                                u = new Client(newUser[0], newUser[1], newUser[2], newUser[3],
                                                        newUser[4], acclvl, java.util.Calendar.getInstance().getTime(),
                                                        java.util.Calendar.getInstance().getTime(), add);
                                            }
                                        }
                                        res = manager.register(u, restaurant);

                                        restaurant = res.restaurant;
                                        System.out.println(res.actionResult);
                                    }
                                    case 2 -> {
                                        showUsers(restaurant);

                                        System.out.println("Enter the username you want to edit");
                                        uname = input.next();

                                        System.out.println(
                                                "Enter user's new information in this order:Firstname Lastname Phonenumber Password if you dont want to change them enter 0");
                                        input.nextLine();
                                        info = input.nextLine();
                                        String[] editedInfo = info.split(" ");

                                        System.out.println(
                                                "Enter the new role:1.Manager 2.Chef 3.Cashier 4.Deliveryman 5.Client");
                                        lvl = input.nextInt();

                                        acclvl = switch (lvl) {
                                            case 1 -> AccessLevel.MANAGER;
                                            case 2 -> AccessLevel.CHEF;
                                            case 3 -> AccessLevel.CASHIER;
                                            case 4 -> AccessLevel.DELIVERYMAN;
                                            default -> AccessLevel.CLIENT;
                                        };
                                        res = manager.edit(uname, editedInfo[0], editedInfo[1], editedInfo[2],
                                                editedInfo[3], acclvl, restaurant);

                                        restaurant = res.restaurant;
                                        System.out.println(res.actionResult);
                                    }
                                    case 3 -> {
                                        showUsers(restaurant);

                                        System.out.println("Enter the username you want to remove:");
                                        uname = input.next();

                                        res = manager.remove(uname, restaurant);

                                        restaurant = res.restaurant;
                                        System.out.println(res.actionResult);
                                    }
                                    default -> logout();
                                }

                            } while (job != 4);

                            break;

                        case 2:
                            Chef chef = (Chef) currentUser;

                            String orderId;
                            String name;
                            String[] ingredients;
                            int id;
                            int availability;
                            int price;
                            boolean isAvailable;
                            Food food;

                            do {
                                System.out.println(
                                        "What you want to do?1.Add a food 2.Edit a food 3.Remove a food 4.Change the food status 5.cook 6.Logout");
                                job = input.nextInt();

                                switch (job) {
                                    case 1 -> {
                                        showMenu(restaurant);

                                        System.out.println("Enter your foodID:");
                                        id = input.nextInt();

                                        System.out.println("Enter your food Name:");
                                        name = input.next();

                                        System.out.println("Enter your food ingredients with space between them:");
                                        input.nextLine();
                                        ingredients = input.nextLine().split(" ");

                                        System.out.println("Enter the food status: 1.Available 2.NotAvailable");
                                        availability = input.nextInt();

                                        switch (availability) {
                                            case 1 -> isAvailable = true;
                                            default -> isAvailable = false;
                                        }

                                        System.out.println("Enter food's price:");
                                        price = input.nextInt();

                                        food = new Food(id, name, ingredients, isAvailable, price);
                                        res = chef.addFood(food, restaurant);

                                        System.out.println(res.actionResult);
                                        restaurant = res.restaurant;
                                    }
                                    case 2 -> {
                                        showMenu(restaurant);

                                        System.out.println("Enter the foodID that you want to edit:");
                                        id = input.nextInt();

                                        System.out.println(
                                                "Enter the new food name first (if you dont want to change, Enter the previous name ) then in next line Enter ingredients with space between them:");
                                        name = input.next();
                                        input.nextLine();
                                        ingredients = input.nextLine().split(" ");

                                        System.out
                                                .println("Enter the new price(if you dont want to change it enter -1)");
                                        price = input.nextInt();

                                        res = chef.editFood(id, name, ingredients, price, restaurant);

                                        System.out.println(res.actionResult);
                                        restaurant = res.restaurant;
                                    }
                                    case 3 -> {
                                        showMenu(restaurant);

                                        System.out.println("Enter the foodID that you want to remove:");
                                        id = input.nextInt();

                                        res = chef.removeFood(id, restaurant);

                                        System.out.println(res.actionResult);
                                        restaurant = res.restaurant;
                                    }
                                    case 4 -> {
                                        showMenu(restaurant);

                                        System.out.println("Enter The foodID to change it's status:");
                                        id = input.nextInt();

                                        res = chef.changeFoodStatus(id, restaurant);

                                        System.out.println(res.actionResult);
                                        restaurant = res.restaurant;
                                    }
                                    case 5 -> {
                                        showOrders(restaurant);

                                        System.out.println("Enter The OrderID to Cook it:");
                                        orderId = input.next();

                                        res = chef.cook(orderId, restaurant);

                                        System.out.println(res.actionResult);
                                        restaurant = res.restaurant;
                                    }
                                    default -> logout();
                                }
                            } while (job != 6);
                            break;

                        case 3:
                            Cashier cashier = (Cashier) currentUser;
                            do {
                                System.out.println("What you want to do 1.Confirm Orders 2.Logout");
                                job = input.nextInt();
                                switch (job) {
                                    case 1 -> {
                                        showOrders(restaurant);

                                        System.out.println("Enter the orderID to confirm it:");
                                        orderId = input.next();

                                        res = cashier.ConfirmOrder(orderId, restaurant);

                                        System.out.println(res.actionResult);
                                        restaurant = res.restaurant;
                                    }
                                    default -> logout();
                                }
                            } while (job != 2);
                            break;

                        case 4:
                            DeliveryMan deliveryMan = (DeliveryMan) currentUser;

                            do {
                                System.out.println("What you want to do 1.Deliver Orders 2.Logout");
                                job = input.nextInt();

                                switch (job) {
                                    case 1 -> {
                                        showOrders(restaurant);

                                        System.out.println("Enter the orderID to deliver it:");
                                        orderId = input.next();

                                        res = deliveryMan.deliver(orderId, restaurant);

                                        System.out.println(res.actionResult);
                                        restaurant = res.restaurant;
                                    }
                                    default -> logout();
                                }
                            } while (job != 2);
                            break;

                        default:
                            int number;
                            Order order;
                            Client client = (Client) currentUser;

                            do {
                                System.out.println("What you want to do?1.Make an order 2.Revoke an order 3.Logout");
                                job = input.nextInt();

                                switch (job) {
                                    case 1 -> {
                                        showMenu(restaurant);

                                        System.out.println("Enter the foodID of the food that you want:");
                                        id = input.nextInt();

                                        System.out.println("Enter the number of food you want:");
                                        number = input.nextInt();

                                        order = new Order(client.username + id, client.username, id, number,
                                                OrderState.MADE, java.util.Calendar.getInstance().getTime(),
                                                client.address);

                                        res = client.makeOrder(order, restaurant);

                                        System.out.println(res.actionResult);
                                        restaurant = res.restaurant;
                                    }
                                    case 2 -> {
                                        showClientOrders(restaurant, client.username);

                                        System.out.println("Enter the orderID that you want to revoke");
                                        orderId = input.next();

                                        res = client.revokeOrder(orderId, restaurant, client.username);

                                        System.out.println(res.actionResult);
                                        restaurant = res.restaurant;
                                    }
                                    default -> logout();
                                }
                            } while (job != 3);
                            break;
                    }
                    break;
                case 2:
                    String information;

                    System.out.println(
                            "Enter Your information in following order :firstName lastName PhoneNumber Username Password Address (insted of spaces in address enter -");
                    information = input.nextLine();
                    String[] registerInfo = information.split(" ");

                    Client client = new Client(registerInfo[0], registerInfo[1], registerInfo[2], registerInfo[3],
                            registerInfo[4], AccessLevel.CLIENT, java.util.Calendar.getInstance().getTime(),
                            java.util.Calendar.getInstance().getTime(), registerInfo[5]);

                    res = register(client, restaurant);
                    System.out.println(res.actionResult);
                    if (res.actionResult == ActionResult.SUCCESS) {
                        restaurant = res.restaurant;
                        int number;
                        Order order;
                        int id;
                        String orderId;

                        do {
                            System.out.println("What you want to do?1.Make an order 2.Revoke an order 3.Logout");
                            job = input.nextInt();

                            switch (job) {
                                case 1 -> {
                                    showMenu(restaurant);

                                    System.out.println("Enter the foodID of the food that you want:");
                                    id = input.nextInt();

                                    System.out.println("Enter the number of food you want:");
                                    number = input.nextInt();

                                    order = new Order(client.username + id, client.username, id, number,
                                            OrderState.MADE, java.util.Calendar.getInstance().getTime(),
                                            client.address);

                                    res = client.makeOrder(order, restaurant);

                                    System.out.println(res.actionResult);
                                    restaurant = res.restaurant;
                                }
                                case 2 -> {
                                    showClientOrders(restaurant, client.username);

                                    System.out.println("Enter the orderID that you want to revoke");
                                    orderId = input.next();

                                    res = client.revokeOrder(orderId, restaurant, client.username);

                                    System.out.println(res.actionResult);
                                    restaurant = res.restaurant;
                                }
                                default -> logout();
                            }
                        } while (job != 3);
                    }

                default:
                    r.save(restaurant);

                    System.out.println("Good Bye...");
                    break;
            }
        } while (action != 3);
        input.close();

    }

    public static ActionResult login(String username, String password, Restaurant restaurant) {
        String pass = Integer.toString(password.hashCode());

        for (int i = 0; i < restaurant.users.size(); i++) {

            if (restaurant.users.get(i).username.equals(username)) {
                if (restaurant.users.get(i).password.equals(pass)) {
                    currentUserindex = i;
                    if (restaurant.users.get(i).accessLevel = AccessLevel.MANAGER) {
                        cuAccLvl = 1;
                        currentUser = (Manager) restaurant.users.get(i);
                    } else if (restaurant.users.get(i).accessLevel = AccessLevel.CHEF) {
                        cuAccLvl = 2;
                        currentUser = (Chef) restaurant.users.get(i);
                    } else if (restaurant.users.get(i).accessLevel = AccessLevel.CASHIER) {
                        cuAccLvl = 3;
                        currentUser = (Cashier) restaurant.users.get(i);
                    } else if (restaurant.users.get(i).accessLevel = AccessLevel.DELIVERYMAN) {
                        cuAccLvl = 4;
                        currentUser = (DeliveryMan) restaurant.users.get(i);
                    } else if (restaurant.users.get(i).accessLevel = AccessLevel.CLIENT) {
                        cuAccLvl = 1;
                        currentUser = (Client) restaurant.users.get(i);
                    }

                    return ActionResult.SUCCESS;
                } else
                    return ActionResult.INCORRECT_PASSWORD;
            }
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }

    public static Resault register(Client client, Restaurant restaurant) {
        Resault resault = new Resault();
        resault.restaurant = restaurant;
        for (int i = 0; i < restaurant.users.size(); i++) {
            if (restaurant.users.get(i).username.equals(client.username)) {
                resault.actionResult = ActionResult.USERNAME_ALREADY_EXIST;
                return resault;
            }
        }
        for (int i = 0; i < client.username.length(); i++) {
            if (client.username.charAt(i) == ' ' || client.username.charAt(i) == '@' || client.username.charAt(i) == '%'
                    || client.username.charAt(i) == '^' || client.username.charAt(i) == '&'
                    || client.username.charAt(i) == '*') {
                resault.actionResult = ActionResult.INVALID_USERNAME;
                return resault;
            }
        }
        for (int i = 0; i < client.password.length(); i++) {
            if (client.password.charAt(i) == ' ') {
                resault.actionResult = ActionResult.INVALID_PASSWORD;
                return resault;
            }
        }
        client.password = Integer.toString(client.password.hashCode());
        resault.restaurant.users.add(client);
        resault.actionResult = ActionResult.SUCCESS;
        return resault;
    }

    public static void showUsers(Restaurant restaurant) {
        System.out.println(
                "Username Firstname Lastname PhoneNumber Role RegistrationDate LastLoginDate \n" + "-".repeat(100));

        for (int i = 0; i < restaurant.users.size(); i++)
            System.out.println(restaurant.users.get(i) + "\n" + "-".repeat(100));
    }

    public static void showMenu(Restaurant restaurant) {
        System.out.println("FoodID Name Ingredients Status Price \n" + "-".repeat(100));

        for (int i = 0; i < restaurant.menu.size(); i++)
            System.out.println(restaurant.menu.get(i) + "\n" + "-".repeat(100));
    }

    public static void showOrders(Restaurant restaurant) {
        System.out.println("OrderId Username FoodId Numbers State OrderDate Address\n" + "-".repeat(100));

        for (int i = 0; i < restaurant.orders.size(); i++)
            System.out.println(restaurant.orders.get(i) + "\n" + "-".repeat(100));
    }

    public static void showClientOrders(Restaurant restaurant, String username) {
        System.out.println("OrderId FoodId Numbers State OrderDate Address\n" + "-".repeat(100));

        for (int i = 0; i < restaurant.orders.size(); i++)
            if (restaurant.orders.get(i).username.equals(username))
                System.out.println(restaurant.orders.get(i).userOrders() + "\n" + "-".repeat(100));
    }

    public static void logout() {
        System.out.println("Logged out...");
    }

}
