package cinema.dao;

import static org.junit.jupiter.api.Assertions.*;

import cinema.dao.impl.RoleDaoImpl;
import cinema.dao.impl.UserDaoImpl;
import cinema.model.Role;
import cinema.model.User;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDaoTest extends AbstractTest {
    private UserDao userDao;
    private RoleDao roleDao;
    private User bob;
    private Role adminRole;

    @BeforeEach
    void setUp() {
        adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        userDao = new UserDaoImpl(getSessionFactory());
        roleDao = new RoleDaoImpl(getSessionFactory());
        roleDao.add(adminRole);
        Set<Role> roles = Set.of(adminRole);
        bob = getTestUser("Bob@gmail.com", "Qwerty!234", roles);
    }

    @Test
    void add_validUser_Ok() {
        User actualUser = userDao.add(bob);
        assertNotNull(actualUser);
        assertEquals(bob, actualUser);
    }

    @Test
    void add_notPresent_notOk() {
        assertThrows(RuntimeException.class, () -> {
            userDao.add(null);
        });
    }

    @Test
    void findByEmail_validEmail_Ok() {
        userDao.add(bob);
        User actual = userDao.findByEmail(bob.getEmail()).get();
        assertNotNull(actual);
        assertEquals(bob.getEmail(), actual.getEmail());
    }

    @Test
    void findByEmail_notPresent_notOk() {
        Optional<User> optionalUser = userDao.findByEmail("Asd");
        assertTrue(optionalUser.isEmpty());
    }

    @Test
    void findById_validId_Ok() {
        userDao.add(bob);
        Optional<User> actualOptionalUser = userDao.get(1L);
        assertTrue(actualOptionalUser.isPresent());
        assertEquals(bob.getEmail(), actualOptionalUser.get().getEmail());
    }

    @Test
    void findById_notPresent_Ok() {
        Optional<User> optionalUser = userDao.get(-1L);
        assertTrue(optionalUser.isEmpty());
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[]{User.class, Role.class, Role.RoleName.class};
    }

    private User getTestUser(String email, String password, Set<Role> roles) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }
}
