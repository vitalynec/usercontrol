package com.vitane.usercontrol.repository;

import com.vitane.usercontrol.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID>, JpaSpecificationExecutor<User> {

    default void create(User user) {
        save(user);
    }

    default User read(UUID id) {
        return findById(id).get();
    }

    default void update(User user) {
        save(user);
    }

    default Page<User> read(Specification<User> specification, Pageable pageable) {
        return findAll(specification, pageable);
    }
}
