package com.vitane.usercontrol.specification;

import com.vitane.usercontrol.domain.User;
import com.vitane.usercontrol.domain.User_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {

    public static Specification<User> findByEmail(String email) {
        return email != null
                ? (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.email), email)
                : null;
    }

    public static Specification<User> findByLogin(String login) {
        return login != null
                ? (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.login), login)
                : null;
    }

    public static Specification<User> findNonDeleted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.deleted), false);
    }
}
