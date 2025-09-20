package kg.management.datum.core.model.address;

import java.util.List;

public record Contact(
        Address address,
        List<Phone> phones
) {
    public Contact(Address address, Phone... phones) {
        this(address, List.of(phones));
    }
}
