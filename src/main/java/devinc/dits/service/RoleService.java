package devinc.dits.service;

import devinc.dits.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void update(Role t);

    void delete(Role t);

    void save(Role t);

    Role getById(int id);

    Role getRoleByName(String roleName);
}
