package com.vitane.usercontrol.service;

import com.vitane.usercontrol.domain.User;
import com.vitane.usercontrol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository repository;

    @Autowired
    private void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public void create(User user) {
        repository.create(user);
    }

    public User read(String login) {
        return repository.read(login);
    }

    public List<User> read(Specification<User> specification, Pageable pageable) {
        return repository.read(specification, pageable).getContent();
    }

    public void update(User user) {
        if (existsByLogin(user.getLogin())) {
            repository.update(user);
        }
        // else throw UserNotFoundException
    }

    public void delete(String login) {
        if (existsByLogin(login)) {
            User currentUser = read(login);
            currentUser.setDeleted(true);
            update(currentUser);
        }
        // else throw UserNotFoundException
    }

    public void delete(User user) {
        delete(user.getLogin());
    }

    public void setPassword(String login, String password) {
        if (existsByLogin(login)) {
            User currentUser = read(login);
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            currentUser.setPassword(passwordHash);
            update(currentUser);
        }
        // else throw UserNotFoundException
    }

    private boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }
}
