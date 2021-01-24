package devinc.dits.service;

import devinc.dits.entity.Role;
import devinc.dits.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void update(User t);

    void delete(User t);

    void save(User t);

    User getById(int id);

}
