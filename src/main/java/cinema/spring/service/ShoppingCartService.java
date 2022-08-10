package cinema.spring.service;

import cinema.spring.model.MovieSession;
import cinema.spring.model.ShoppingCart;
import cinema.spring.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
