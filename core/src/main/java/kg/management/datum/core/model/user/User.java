package kg.management.datum.core.model.user;

import java.util.List;
import kg.management.datum.core.model.employee.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {
    private final long id;
    private final String email;
    private final String password;
    private final long personId;
    private final boolean enabled;
    private final List<Role> roles;
}
