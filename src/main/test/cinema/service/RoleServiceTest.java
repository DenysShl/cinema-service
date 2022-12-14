package cinema.service;

import static org.junit.jupiter.api.Assertions.*;

import cinema.dao.impl.RoleDaoImpl;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import cinema.dao.RoleDao;
import cinema.service.impl.RoleServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RoleServiceTest {
    private static final String NonExistentRole = "USR";
    private static RoleDao roleDao;
    private static RoleService roleService;
    private static Role adminRole;

    @BeforeAll
    static void beforeAll() {
        roleDao = Mockito.mock(RoleDaoImpl.class);
        adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService = new RoleServiceImpl(roleDao);
    }

    @Test
    void save_validRoleDao_OK() {
        Mockito.when(roleDao.add(adminRole)).thenReturn(adminRole);
        Role actual = roleService.add(adminRole);
        assertNotNull(actual);
        assertEquals(adminRole.getRoleName(), actual.getRoleName());
    }

    @Test
    void getRoleByName_validByName_Ok() {
        Mockito.when(roleDao.getByName("ADMIN")).thenReturn(Optional.of(adminRole));
        Role actual = roleService.getByName("ADMIN").get();
        assertNotNull(actual);
        assertEquals(adminRole.getRoleName(), actual.getRoleName());
    }

    @Test
    void getRoleByName_nonExistent_notOk() {
        Mockito.when(roleDao.getByName(NonExistentRole))
                .thenThrow(DataProcessingException.class);
        assertThrows(RuntimeException.class,
                () -> roleService.getByName(NonExistentRole));
    }
}
