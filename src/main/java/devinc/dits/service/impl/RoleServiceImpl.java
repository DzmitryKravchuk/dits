package devinc.dits.service.impl;

import devinc.dits.entity.Role;
import devinc.dits.repository.RoleRepository;
import devinc.dits.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository repository;

    @Autowired
    public void setRepository(RoleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<Role> findAll() {
        return repository.findAll(Role.class);
    }

    @Transactional
    @Override
    public void update(Role t) {
        repository.update(t);
    }

    @Transactional
    @Override
    public void delete(Role t) {
        repository.delete(t);
    }

    @Transactional
    @Override
    public void save(Role t) {
        repository.save(t);
    }

    @Transactional
    @Override
    public Role getById(int id) {
        return repository.getById(Role.class, id);
    }
}
