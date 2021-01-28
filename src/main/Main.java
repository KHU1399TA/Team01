package main;

import java.util.Scanner;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    static int currentUserindex;
    static User currentUser;

    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);

        File r = new File();
        Restaurant restaurant = new Restaurant();
        restaurant = r.read(restaurant);
        showUsers(restaurant);
        showMenu(restaurant);
        showOrders(restaurant);
        int cuAccLvl = 0;
        String username, password;
        ActionResult loginaction;
        while (cuAccLvl == 0) {

            System.out.println("Enter your role : 1.Manager 2.Chef 3.Cashier 4.Deliveryman 5.Client");
            int a = input.nextInt();
            System.out.println("Enter your username:");
            username = input.next();
            System.out.println("Enter your password:");
            password = input.next();

            switch (a) {
                case 1:
                    loginaction = login(username, password, AccessLevel.MANAGER, restaurant);
                    cuAccLvl = 1;
                    break;
                case 2:
                    loginaction = login(username, password, AccessLevel.CHEF, restaurant);
                    cuAccLvl = 2;
                    break;
                case 3:
                    loginaction = login(username, password, AccessLevel.CASHIER, restaurant);
                    cuAccLvl = 3;
                    break;
                case 4:
                    loginaction = login(username, password, AccessLevel.DELIVERYMAN, restaurant);
                    cuAccLvl = 4;
                    break;
                default:
                    loginaction = login(username, password, AccessLevel.CLIENT, restaurant);
                    cuAccLvl = 5;
                    break;
            }
            System.out.println(loginaction);
            if (loginaction == ActionResult.SUCCESS) {
                restaurant.users.get(currentUserindex).lastLoginDate = java.util.Calendar.getInstance().getTime();
                System.out.println("Welcome " + currentUser.firstName + " " + currentUser.lastName);
            } else {
                cuAccLvl = 0;
                System.out.println("Please try again");
            }

        }
        int job = 7;
        String info;
        String uname;
        int lvl;
        AccessLevel acclvl;
        Resault res = new Resault();
        switch (cuAccLvl) {
            case 1:
                Manager manager = (Manager) currentUser;
                do {
                    System.out.println("What you want to do?1.Register a User 2.Edit a user 3.Remove a User 4.Logout");
                    job = input.nextInt();
                    switch (job) {
                        case 1:
                            showUsers(restaurant);
                            System.out.println(
                                    "Enter user's information in this order:Firstname Lastname Phonenumber Username Password ");
                            info = input.nextLine();
                            String[] newUser = info.split(" ");
                            System.out.println("Enter user's role:1.Manager 2.Chef 3.Cashier 4.Deliveryman 5.Client");
                            lvl = input.nextInt();
                            User u;
                            switch (lvl) {
                                case 1:
                                    acclvl = AccessLevel.MANAGER;
                                    u = new Manager(newUser[0], newUser[1], newUser[2], newUser[3], newUser[4], acclvl,
                                            java.util.Calendar.getInstance().getTime(),
                                            java.util.Calendar.getInstance().getTime());
                                    break;
                                case 2:
                                    acclvl = AccessLevel.CHEF;
                                    u = new Chef(newUser[0], newUser[1], newUser[2], newUser[3], newUser[4], acclvl,
                                            java.util.Calendar.getInstance().getTime(),
                                            java.util.Calendar.getInstance().getTime());
                                    break;
                                case 3:
                                    acclvl = AccessLevel.CASHIER;
                                    u = new Cashier(newUser[0], newUser[1], newUser[2], newUser[3], newUser[4], acclvl,
                                            java.util.Calendar.getInstance().getTime(),
                                            java.util.Calendar.getInstance().getTime());
                                    break;
                                case 4:
                                    acclvl = AccessLevel.DELIVERYMAN;
                                    u = new DeliveryMan(newUser[0], newUser[1], newUser[2], newUser[3], newUser[4],
                                            acclvl, java.util.Calendar.getInstance().getTime(),
                                            java.util.Calendar.getInstance().getTime());
                                    break;
                                default:
                                    acclvl = AccessLevel.CLIENT;
                                    System.out.println("Enter user's address:");
                                    String add = input.nextLine();
                                    u = new Client(newUser[0], newUser[1], newUser[2], newUser[3], newUser[4], acclvl,
                                            java.util.Calendar.getInstance().getTime(),
                                            java.util.Calendar.getInstance().getTime(), add);
                                    break;
                            }
                            res = manager.register(u, restaurant);
                            restaurant = res.restaurant;
                            System.out.println(res.actionResult);
                            break;
                        case 2:
                            showUsers(restaurant);
                            System.out.println("Enter the username you want to edit");
                            uname = input.next();
                            System.out.println(
                                    "Enter user's new information in this order:Firstname Lastname Phonenumber Password if you dont want to change them enter 0");
                            info = input.nextLine();
                            String[] editedinfo = info.split(" ");
                            System.out.println("Enter the new role:1.Manager 2.Chef 3.Cashier 4.Deliveryman 5.Client");
                            lvl = input.nextInt();
                            switch (lvl) {
                                case 1:
                                    acclvl = AccessLevel.MANAGER;
                                    break;
                                case 2:
                                    acclvl = AccessLevel.CHEF;
                                    break;
                                case 3:
                                    acclvl = AccessLevel.CASHIER;
                                    break;
                                case 4:
                                    acclvl = AccessLevel.DELIVERYMAN;
                                    break;
                                default:
                                    acclvl = AccessLevel.CLIENT;
                                    break;
                            }
                            res = manager.edit(uname, editedinfo[0], editedinfo[1], editedinfo[2], editedinfo[3],
                                    acclvl, restaurant);
                            restaurant = res.restaurant;
                            System.out.println(res.actionResult);

                            break;
                        case 3:
                            showUsers(restaurant);
                            System.out.println("Enter the username you want to remove:");
                            uname = input.next();
                            res = manager.remove(uname, restaurant);
                            restaurant = res.restaurant;
                            System.out.println(res.actionResult);
                            break;

                        default:
                            r.save(restaurant);
                            logout();
                            break;
                    }

                } while (job != 4);

                break;

            case 2:
                Chef chef = (Chef) currentUser;
                int id;
                String orderId;
                String name;
                String[] ingredients;
                int avb;
                boolean isAvailable;
                Food food;
                int price;
                do {
                    System.out.println(
                            "What you want to do?1.Add a food 2.Edit a food 3.Remove a food 4.Change the food status 5.cook 6.Logout");
                    job = input.nextInt();
                    switch (job) {
                        case 1:
                            showMenu(restaurant);
                            System.out.println("Enter your foodID:");
                            id = input.nextInt();
                            System.out.println("Enter your food Name:");
                            name = input.next();
                            System.out.println("Enter your food ingredients with space between them:");
                            ingredients = input.nextLine().split(" ");
                            System.out.println("Enter the food status:1.Available 2.NotAvailable");
                            avb = input.nextInt();
                            switch (avb) {
                                case 1:
                                    isAvailable = true;
                                    break;

                                default:
                                    isAvailable = false;
                                    break;
                            }
                            System.out.println("Enter food's price:");
                            price = input.nextInt();
                            food = new Food(id, name, ingredients, isAvailable, price);
                            res = chef.addFood(food, restaurant);
                            System.out.println(res.actionResult);
                            restaurant = res.restaurant;
                            break;
                        case 2:
                            showMenu(restaurant);
                            System.out.println("Enter the foodID that you want to edit:");
                            id = input.nextInt();
                            System.out.println(
                                    "Enter the new food name first (if you dont want to change it type the pervious name ) than next line Enter ingredients with space between them:");
                            name = input.next();
                            ingredients = input.nextLine().split(" ");
                            System.out.println("Enter the new price(if you dont want to change it enter -1)");
                            price = input.nextInt();
                            res = chef.editFood(id, name, ingredients, price, restaurant);
                            System.out.println(res.actionResult);
                            restaurant = res.restaurant;
                            break;
                        case 3:
                            showMenu(restaurant);
                            System.out.println("Enter the foodID that you want to remove:");
                            id = input.nextInt();
                            res = chef.removeFood(id, restaurant);
                            System.out.println(res.actionResult);
                            restaurant = res.restaurant;
                            break;
                        case 4:
                            showMenu(restaurant);
                            System.out.println("Enter The foodID to change it's status:");
                            id = input.nextInt();
                            res = chef.changeFoodStatus(id, restaurant);
                            System.out.println(res.actionResult);
                            restaurant = res.restaurant;
                            break;
                        case 5:
                            showOrders(restaurant);
                            System.out.println("Enter The OrderID to Cook it:");
                            orderId = input.next();
                            res = chef.cook(orderId, restaurant);
                            System.out.println(res.actionResult);
                            restaurant = res.restaurant;
                            break;
                        default:
                            r.save(restaurant);
                            logout();
                            break;
                    }
                } while (job != 6);
                break;

            case 3:
                Cashier cashier = (Cashier) currentUser;
                do {
                    System.out.println("What you want to do 1.Confirm Orders 2.Logout");
                    job = input.nextInt();
                    switch (job) {
                        case 1:
                            showOrders(restaurant);
                            System.out.println("Enter the orderID to confirm it:");
                            orderId = input.next();
                            res = cashier.ConfirmOrder(orderId, restaurant);
                            System.out.println(res.actionResult);
                            restaurant = res.restaurant;
                            break;

                        default:
                            r.save(restaurant);
                            logout();
                            break;
                    }
                } while (job != 2);
                break;

            case 4:
                DeliveryMan deliveryMan = (DeliveryMan) currentUser;
                do {
                    System.out.println("What you want to do 1.Deliver Orders 2.Logout");
                    job = input.nextInt();
                    switch (job) {
                        case 1:
                            showOrders(restaurant);
                            System.out.println("Enter the orderID to deliver it:");
                            orderId = input.next();
                            res = deliveryMan.deliver(orderId, restaurant);
                            System.out.println(res.actionResult);
                            restaurant = res.restaurant;
                            break;

                        default:
                            r.save(restaurant);
                            logout();
                            break;
                    }
                } while (job != 2);
                break;

            default:
                Order order;
                Client client = (Client) currentUser;
                int number;
                do {
                    System.out.println("What you want to do?1.Make an order 2.Revoke an order 3.Logout");
                    job = input.nextInt();
                    switch (job) {
                        case 1:
                            showMenu(restaurant);
                            System.out.println("Enter the foodID of the food that you want:");
                            id = input.nextInt();
                            System.out.println("Enter the number of food you want:");
                            number = input.nextInt();
                            order = new Order(client.username + id, client.username, id, number, OrderState.MADE,
                                    java.util.Calendar.getInstance().getTime(), client.address);
                            res = client.makeOrder(order, restaurant);
                            System.out.println(res.actionResult);
                            restaurant = res.restaurant;
                            break;
                        case 2:
                            showClientOrders(restaurant, client.username);
                            System.out.println("Enter the orderID that you want to revoke");
                            orderId = input.next();
                            res = client.revokeOrder(orderId, restaurant, client.username);
                            System.out.println(res.actionResult);
                            restaurant = res.restaurant;
                            break;
                        default:
                            r.save(restaurant);
                            logout();
                            break;
                    }
                } while (job != 3);
                break;
        }
        input.close();
    }

    public static ActionResult login(String username, String password, AccessLevel accessLevel, Restaurant restaurant) {
        String pass = Integer.toString(password.hashCode());
        for (int i = 0; i < restaurant.users.size(); i++) {
            if (restaurant.users.get(i).username.equals(username)) {
                if (restaurant.users.get(i).password.equals(pass)) {
                    if (restaurant.users.get(i).accessLevel == accessLevel) {
                        currentUserindex = i;
                        currentUser = restaurant.users.get(i);
                        return ActionResult.SUCCESS;
                    } else
                        return ActionResult.ROLE_NOT_MATCH;
                } else
                    return ActionResult.INCORRECT_PASSWORD;
            }
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }

    public static void showUsers(Restaurant restaurant) {
        System.out.println(
                "Username Firstname Lastname PhoneNumber Role RegisterationDate LastLoginDate \n" + "-".repeat(100));
        for (int i = 0; i < restaurant.users.size(); i++) {
            System.out.println(restaurant.users.get(i) + "\n" + "-".repeat(100));
        }
    }

    public static void showMenu(Restaurant restaurant) {
        System.out.println("FoodID Name Ingredients Status Price \n" + "-".repeat(100));
        for (int i = 0; i < restaurant.menu.size(); i++) {
            System.out.println(restaurant.menu.get(i) + "\n" + "-".repeat(100));
        }
    }

    public static void showOrders(Restaurant restaurant) {
        System.out.println("OrderId Username FoodId Numbers State OrderDate Address\n" + "-".repeat(100));
        for (int i = 0; i < restaurant.orders.size(); i++) {
            System.out.println(restaurant.orders.get(i) + "\n" + "-".repeat(100));
        }
    }

    public static void showClientOrders(Restaurant restaurant, String username) {
        System.out.println("OrderId FoodId Numbers State OrderDate Address\n" + "-".repeat(100));
        for (int i = 0; i < restaurant.orders.size(); i++) {
            if (restaurant.orders.get(i).username.equals(username))
                System.out.println(restaurant.orders.get(i).userOrders() + "\n" + "-".repeat(100));
        }
    }

    public static void logout() {
        System.out.println("Logged out...");
    }

}