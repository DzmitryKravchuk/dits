package devinc.dits.service.impl;

import devinc.dits.entity.Literature;
import devinc.dits.repository.LiteratureRepository;
import devinc.dits.service.LiteratureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LiteratureServiceImpl implements LiteratureService {
    LiteratureRepository repository;

    @Autowired
    public void setRepository(LiteratureRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<Literature> findAll() {
        return repository.findAll(Literature.class);
    }

    @Transactional
    @Override
    public void update(Literature t) {
        repository.update(t);
    }

    @Transactional
    @Override
    public void delete(Literature t) {
        repository.delete(t);
    }

    @Transactional
    @Override
    public void save(Literature t) {
        repository.save(t);
    }

    @Transactional
    @Override
    public Literature getById(int id) {
        return repository.getById(Literature.class, id);
    }
}
