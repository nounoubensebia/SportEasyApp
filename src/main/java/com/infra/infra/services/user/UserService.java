package com.infra.infra.services.user;

import com.infra.infra.models.User;

import java.util.List;

public interface UserService {

    public User getConnectedUser();
    public List<User> getAll();
    public User getById(long id);
    public void create(User user);
    public void update(User user);
    public void delete(User session);

}
