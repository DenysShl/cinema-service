package cinema.spring.dao;

import cinema.spring.model.Order;
import cinema.spring.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
