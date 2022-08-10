package cinema.spring.service.impl;

import cinema.spring.dao.RoleDao;
import cinema.spring.model.Role;
import cinema.spring.service.RoleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        return roleDao.getByName(roleName);
    }
}
