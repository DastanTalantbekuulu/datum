package kg.management.datum.core.model.clinic;

import kg.management.datum.core.model.address.Contact;

public record Department(
        long departmentId,
        String name,
        Contact contact
) {
}
