package devinc.dits.service.impl;

import devinc.dits.entity.Role;
import devinc.dits.entity.User;
import devinc.dits.repository.UserRepository;
import devinc.dits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return repository.findAll(User.class);
    }

    @Transactional
    @Override
    public void update(User t) {
        repository.update(t);
    }

    @Transactional
    @Override
    public void delete(User t) {
        repository.delete(t);
    }

    @Transactional
    @Override
    public void save(User t) {
        t.setPassword(passwordEncoder.encode(t.getPassword()));
        repository.save(t);
    }

    @Transactional
    @Override
    public User getById(int id) {
        return repository.getById(User.class, id);
    }

    @Transactional
    @Override
    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return repository.getByLogin(currentPrincipalName);
    }

    @Transactional
    @Override
    public User getByLogin(String login) {
        return repository.getByLogin(login);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("No such a user");
        }
        Set<GrantedAuthority> roles = new HashSet();
        for (Role role : user.getRoleList()) {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getLogin(),
                        user.getPassword(),
                        roles);

        return userDetails;
    }
}
