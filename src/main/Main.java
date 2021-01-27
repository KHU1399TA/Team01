package main;

import java.util.Scanner;
import java.text.ParseException;
import java.util.Date;

public class Main {
    static int currentUserindex;
    static User currentUser;

    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);

        Readfile r = new Readfile();
        Restaurant restaurant = new Restaurant();
        restaurant = r.read(restaurant);
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
        int job = 5;
        String info;
        String uname;
        int lvl;
        AccessLevel acclvl;
        Resault res;
        switch (cuAccLvl) {
            case 1:
                Manager manager = (Manager) currentUser;
                do {
                    System.out.println("What you want to do?1.Register a User 2.Edit a user 3.Remove a User 4.Logout");
                    job = input.nextInt();
                    switch (job) {
                        case 1:
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
                            System.out.println("Enter the username you want to edit");
                            uname = input.next();
                            System.out.println(
                                    "Enter user's new information in this order:Firstname Lastname Phonenumber Username Password if you dont want to change them enter 0");
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
                                    editedinfo[4], acclvl, restaurant);
                            restaurant = res.restaurant;
                            System.out.println(res.actionResult);

                            break;
                        case 3:
                            System.out.println("Enter the username you want to remove:");
                            uname = input.next();
                            res = manager.remove(uname, restaurant);
                            restaurant = res.restaurant;
                            System.out.println(res.actionResult);
                            break;

                        default:
                            logout();
                            break;
                    }

                } while (job != 4);

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

            default:
                break;
        }
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

    public static void logout() {
        System.out.println("Logout was successful.");
    }

}