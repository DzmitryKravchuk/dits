package devinc.dits.service.impl;

import devinc.dits.entity.Link;
import devinc.dits.repository.LinkRepository;
import devinc.dits.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    LinkRepository repository;

    @Autowired
    public void setRepository(LinkRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<Link> findAll() {
        return repository.findAll(Link.class);
    }

    @Transactional
    @Override
    public void update(Link t) {
        repository.update(t);
    }

    @Transactional
    @Override
    public void delete(Link t) {
        repository.delete(t);
    }

    @Transactional
    @Override
    public void save(Link t) {
        repository.save(t);
    }

    @Transactional
    @Override
    public Link getById(int id) {
        return repository.getById(Link.class, id);
    }
}
