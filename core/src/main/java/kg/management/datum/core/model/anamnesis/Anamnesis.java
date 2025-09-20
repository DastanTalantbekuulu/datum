package kg.management.datum.core.model.anamnesis;

import java.util.List;
import java.util.Map;
import kg.management.datum.core.model.disease.Disease;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Anamnesis {
    private final long id;
    private final long personId;
    private final long employeeId;
    private final List<Disease> disease;
    private final Map<String, String> details;
    private final DentalNotation dentalNotation;
    private final List<Radiography> radiography;
}
