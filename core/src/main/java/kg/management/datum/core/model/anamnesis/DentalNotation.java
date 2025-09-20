package kg.management.datum.core.model.anamnesis;

import java.util.Map;
import kg.management.datum.core.constants.Occlusion;
import kg.management.datum.core.constants.ToothNumber;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
public class DentalNotation {
    private final Map<ToothNumber, Tooth> notation;
    private final Occlusion occlusion;

    public DentalNotation(Map<ToothNumber, Tooth> notation) {
        this.notation = notation;
        occlusion = Occlusion.NORMAL;
    }
}
