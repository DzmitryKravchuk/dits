package devinc.dits.service.impl;

import devinc.dits.entity.Role;
import devinc.dits.entity.User;
import devinc.dits.repository.UserRepository;
import devinc.dits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
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
        repository.save(t);
    }

    @Transactional
    @Override
    public User getById(int id) {
        return repository.getById(User.class, id);
    }

}
