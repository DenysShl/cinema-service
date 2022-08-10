package cinema.spring.service;

import cinema.spring.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
