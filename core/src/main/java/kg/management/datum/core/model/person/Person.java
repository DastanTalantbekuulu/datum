package kg.management.datum.core.model.person;

import java.util.List;
import kg.management.datum.core.model.address.Phone;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Person {
    private final long id;
    private final String mrz;
    private final List<Phone> phones;
}
