package main;

import java.util.ArrayList;

public class DeliveryMan extends User {

    ActionResult deliver (int id){
        for (int i = 0; i < orders.size(); i++) {
            if ( orders.get(i).id == id ) {

                if ( orders.get(i).state == OrderState.COOKED ) {

                    orders.get(i).state = OrderState.DELIVERED;

                    return ActionResult.SUCCESS;
                }
                else
                    return ActionResult.ORDER_NOT_COOKED;
            }
        }

        return ActionResult.ORDER_NOT_FOUND;
    }
}
