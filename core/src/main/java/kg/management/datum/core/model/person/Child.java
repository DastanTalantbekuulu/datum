package kg.management.datum.core.model.person;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Child {
    private final long id;
    private final String name;
    private final Person mother;
    private final Person father;
}
