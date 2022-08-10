package cinema.spring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import cinema.spring.dao.UserDao;
import cinema.spring.model.Role;
import cinema.spring.model.User;
import cinema.spring.service.impl.UserServiceImpl;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest {
    private static UserDao userDao;
    private static PasswordEncoder passwordEncoder;
    private static UserService userService;
    private static User bob;
    private static Role.RoleName userRole;

    @BeforeAll
    static void beforeAll() {
        userDao = Mockito.mock(UserDao.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserServiceImpl(passwordEncoder, userDao);
        userRole = Role.RoleName.ADMIN;
        Role role = new Role();
        role.setRoleName(userRole);
        bob = getTestUser("bob@gmail.com", "qwEds!23", Set.of(role));
    }

    @Test
    void save_validUserDao_Ok() {
        Mockito.when(userDao.add(bob)).thenReturn(bob);
        User actual = userService.add(bob);
        assertNotNull(actual);
        assertEquals(bob, actual);
        assertEquals(bob.getEmail(), actual.getEmail());
        assertEquals(bob.getPassword(), actual.getPassword());
    }

    @Test
    void save_nonExisting_notOk() {
        assertThrows(Exception.class,
                () -> userService.add(any()));
    }

    @Test
    void findById_valid_Ok() {
        long id = 1;
        Mockito.when(userDao.get(id)).thenReturn(Optional.of(bob));
        User actual = userService.get(id);
        assertNotNull(actual);
        assertEquals(actual, bob);
    }

    @Test
    void findById_nonExistingId_notOk() {
        Mockito.when(userDao.get(any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> userService.get(-1L));
    }

    @Test
    void findByEmail_valid_Ok() {
        Mockito.when(userDao.findByEmail(bob.getEmail())).thenReturn(Optional.of(bob));
        Optional<User> actualOptionalUserByEmail = userService.findByEmail(bob.getEmail());
        assertTrue(actualOptionalUserByEmail.isPresent());
        assertEquals(actualOptionalUserByEmail.get().getEmail(), bob.getEmail());
    }

    @Test
    void findByEmail_nonExistingId_notOk() {
        assertThrows(NoSuchElementException.class,
                () -> userService.findByEmail("asd@i.ua").get());
    }

    private static User getTestUser(String email, String password, Set<Role> roles) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }
}
