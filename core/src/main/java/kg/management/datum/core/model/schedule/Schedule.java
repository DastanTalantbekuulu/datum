package kg.management.datum.core.model.schedule;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Schedule {
    private final long id;
    private final long employeeId;
    private final List<Map<Integer, Long>> days;
}
