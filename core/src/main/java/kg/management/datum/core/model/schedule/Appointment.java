package kg.management.datum.core.model.schedule;

import kg.management.datum.core.model.therapy.Treatment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Appointment {
    private final long id;
    private final long start;
    private final long end;
    private final long employeeId;
    private final long personId;
    private final Treatment treatment;
}
