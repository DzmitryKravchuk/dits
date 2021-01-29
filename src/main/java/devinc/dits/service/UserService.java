package devinc.dits.service;

import devinc.dits.entity.Role;
import devinc.dits.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();

    void update(User t);

    void delete(User t);

    void save(User t);

    User getById(int id);

    User getLoggedUser();

    User getByLogin(String login);
}
