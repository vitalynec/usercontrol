package com.vitane.usercontrol.service;

import com.vitane.usercontrol.domain.User;
import com.vitane.usercontrol.domain.UserResponse;
import com.vitane.usercontrol.exception.PasswordNotMatchesException;
import com.vitane.usercontrol.exception.UserNotFoundException;
import com.vitane.usercontrol.repository.UserRepository;
import com.vitane.usercontrol.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final ConversionService conversionService;

    public void create(User user) {
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public UserResponse read(UUID id) throws UserNotFoundException {
        return conversionService.convert(getUser(id), UserResponse.class);
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> read(String login, String email, Pageable pageable) {
        Specification<User> specification = UserSpecification.findNonDeleted()
                .and(UserSpecification.findByEmail(email))
                .and(UserSpecification.findByLogin(login));
        return repository.findAll(specification, pageable).map(user -> conversionService.convert(user, UserResponse.class));
    }

    public UserResponse update(User user) throws UserNotFoundException {
        if (repository.existsById(user.getId())) {
            repository.save(user);
            return conversionService.convert(user, UserResponse.class);
        } else {
            throw new UserNotFoundException(user.getId().toString());
        }
    }

    public void delete(UUID id) throws UserNotFoundException {
        User currentUser = getUser(id);
        currentUser.setDeleted(true);
        update(currentUser);
    }

    public void setPassword(UUID id, String lastPassword, String newPassword)
            throws UserNotFoundException, PasswordNotMatchesException {
        User currentUser = getUser(id);
        if (passwordEncoder.matches(currentUser.getPassword(), lastPassword)) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            update(currentUser);
        } else {
            throw new PasswordNotMatchesException();
        }
    }

    private User getUser(UUID id) throws UserNotFoundException {
        User currentUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        if (currentUser.isDeleted()) {
            throw new UserNotFoundException(id.toString());
        } else {
            return currentUser;
        }
    }
}
