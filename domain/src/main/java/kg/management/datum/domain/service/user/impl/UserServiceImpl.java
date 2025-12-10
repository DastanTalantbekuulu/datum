package kg.management.datum.domain.service.user.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import kg.management.datum.domain.entity.user.User;
import kg.management.datum.domain.repository.user.UserRepository;
import kg.management.datum.domain.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.Optional;

import static kg.management.datum.jooq.public_.Tables.USERS;


@Server
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final DSLContext context;


    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(context.select()
                .from(USERS)
                .where(USERS.ID.eq(id))
                .fetchOneInto(User.class));
    }
}
