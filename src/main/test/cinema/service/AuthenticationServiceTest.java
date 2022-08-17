package cinema.service;

import static org.junit.jupiter.api.Assertions.*;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.impl.AuthenticationServiceImpl;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AuthenticationServiceTest {
    private static UserService userService;
    private static RoleService roleService;
    private static AuthenticationService authenticationService;
    private static ShoppingCartService shoppingCartService;
    private static User bob;
    private static String email;
    private static String password;

    @BeforeAll
    static void beforeAll() {
        roleService = Mockito.mock(RoleService.class);
        userService = Mockito.mock(UserService.class);
        shoppingCartService = Mockito.mock(ShoppingCartService.class);
        authenticationService
                = new AuthenticationServiceImpl(userService, shoppingCartService, roleService);
        email = "bob@gmail.com";
        password = "Qwerty!234";
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.USER);
        bob = getTestUser(email, password, Set.of(adminRole));
    }

    @Test
    void register_valid_OK() {
        Mockito.when(roleService.getByName(Role.RoleName.USER.name()))
                .thenReturn(bob.getRoles().stream().findFirst());
        Mockito.when(userService.add(bob)).thenReturn(bob);
        User actual = authenticationService.register(bob.getEmail(), bob.getPassword());
        assertNotNull(actual);
        assertEquals(bob.getEmail(), actual.getEmail());
        assertEquals(bob.getPassword(), actual.getPassword());
    }

    private static User getTestUser(String email, String password, Set<Role> roles) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }
}
