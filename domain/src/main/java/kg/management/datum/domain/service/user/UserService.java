package kg.management.datum.domain.service.user;

import kg.management.datum.domain.entity.user.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
}
