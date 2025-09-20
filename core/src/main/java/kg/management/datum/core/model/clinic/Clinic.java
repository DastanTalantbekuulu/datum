package kg.management.datum.core.model.clinic;

import java.util.List;

public record Clinic(
        int clinicId,
        String name,
        String license,
        Department main,
        List<Department> departments
) {
}
