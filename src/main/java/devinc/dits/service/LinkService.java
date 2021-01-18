package devinc.dits.service;

import devinc.dits.entity.Link;
import devinc.dits.entity.Role;

import java.util.List;

public interface LinkService {
    List<Link> findAll();

    void update(Link t);

    void delete(Link t);

    void save(Link t);

    Link getById(int id);
}
