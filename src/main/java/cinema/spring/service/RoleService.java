package cinema.spring.service;

import cinema.spring.model.Role;
import java.util.Optional;

public interface RoleService {
    Role add(Role role);

    Optional<Role> getByName(String roleName);
}
