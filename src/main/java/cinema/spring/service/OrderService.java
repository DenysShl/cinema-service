package cinema.spring.service;

import cinema.spring.model.Order;
import cinema.spring.model.ShoppingCart;
import cinema.spring.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
