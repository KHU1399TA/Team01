package main;

import utils.FileManager;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Readfile {
    private static final String DATA_FILE_PATH_USER = "src/resources/users.txt";
    private static final String DATA_FILE_PATH_FOOD = "src/resources/foods.txt";
    private static final String DATA_FILE_PATH_ORDER = "src/resources/orders.txt";

    public Restaurant read(Restaurant restaurant) {
        FileManager fileManager = new FileManager(DATA_FILE_PATH_USER);
        String content = fileManager.readAll();

        String[] us = content.split("\n");

        for (int i = 0; i < us.length; i++) {
            String[] line = us[i].split(";");
            Date registrationDate = new SimpleDateFormat("dd/MM/yyyy").parse(line[6]);
            Date lastloginDate = new SimpleDateFormat("dd/MM/yyyy").parse(line[7]);
            if (line[5].equals("MA")) {
                restaurant.users.add(new Manager(line[0], line[1], line[2], line[3], line[4], AccessLevel.MANAGER,
                        registrationDate, lastloginDate));
            } else if (line[5].equals("CA")) {
                restaurant.users.add(new Cashier(line[0], line[1], line[2], line[3], line[4], AccessLevel.CASHIER,
                        registrationDate, lastloginDate));
            } else if (line[5].equals("CH")) {
                restaurant.users.add(new Chef(line[0], line[1], line[2], line[3], line[4], AccessLevel.CHEF,
                        registrationDate, lastloginDate));
            } else if (line[5].equals("DE")) {
                restaurant.users.add(new Deliveryman(line[0], line[1], line[2], line[3], line[4],
                        AccessLevel.DELIVERYMAN, registrationDate, lastloginDate));
            } else {
                restaurant.users.add(new Client(line[0], line[1], line[2], line[3], line[4], AccessLevel.CLIENT,
                        registrationDate, lastloginDate, line[8]));
            }
        }

        fileManager = new FileManager(DATA_FILE_PATH_FOOD);
        content = fileManager.readAll();
        us = content.split("\n");

        for (int i = 0; i < us.length; i++) {
            String[] line = us[i].split(";");
            String[] ing = line[2].split("-");
            ArrayList<String> ingredients = new ArrayList<String>();
            for (int j = 0; j < ing.length; j++) {
                ingredients.add(ing[j]);
            }
            boolean isAvailable;
            if (line[3].equals("Yes"))
                isAvailable = true;
            else
                isAvailable = false;
            restaurant.menu.add(new Food(line[0], line[1], ingredients, isAvailable));
        }

        fileManager = new FileManager(DATA_FILE_PATH_ORDER);
        content = fileManager.readAll();
        us = content.split("\n");
        for (int i = 0; i < us.length; i++) {
            String[] line = us[i].split(";");
            OrderState state;
            Date orderdate = new SimpleDateFormat("dd/MM/yyyy").parse(line[4]);
            switch (line[3]) {
                case "MA":
                    state = OrderState.MADE;
                    break;
                case "CN":
                    state = OrderState.CONFIRMED;
                    break;
                case "CO":
                    state = OrderState.COOKED;
                    break;
                default:
                    state = OrderState.DELIVERED;
                    break;
            }
            restaurant.orders
                    .add(new Order(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]), state, orderdate));
        }
        return restaurant;
    }
}
