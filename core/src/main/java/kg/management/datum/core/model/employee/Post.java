package kg.management.datum.core.model.employee;

import java.util.List;

public record Post(
        long id,
        String name,
        String description,
        List<Role> roles
) {
}

