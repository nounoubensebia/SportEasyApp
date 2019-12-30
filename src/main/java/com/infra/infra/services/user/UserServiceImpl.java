package com.infra.infra.services.user;

import com.infra.infra.models.User;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository sessionRepository)
    {
        this.userRepository =sessionRepository;
    }

    @Override
    public User getConnectedUser() {
        return userRepository.getOne(1L);
    }

    @Override
    public List<User> getAll() {
        return IterableUtils.toList(userRepository.findAll());
    }

    @Override
    public User getById(long id) {
        if (userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

}
