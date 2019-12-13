package com.vitane.usercontrol.service;

import com.vitane.usercontrol.domain.User;
import com.vitane.usercontrol.repository.UserRepository;
import com.vitane.usercontrol.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public User read(UUID id) {
        return repository.read(id);
    }

    public List<User> read(Specification<User> specification, Pageable pageable) {
        return repository.read(specification, pageable).getContent();
    }

    public void update(User user) {
        if (existsById(user.getId())) {
            repository.update(user);
        }
        // else throw UserNotFoundException
    }

    public void delete(UUID id) {
        if (existsById(id)) {
            User currentUser = read(id);
            currentUser.setDeleted(true);
            update(currentUser);
        }
        // else throw UserNotFoundException
    }

    public void delete(User user) {
        delete(user.getId());
    }

    public void setPassword(UUID id, String password) {
        if (existsById(id)) {
            User currentUser = read(id);
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            currentUser.setPassword(passwordHash);
            update(currentUser);
        }
        // else throw UserNotFoundException
    }

    private boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
