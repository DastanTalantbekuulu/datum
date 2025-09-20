package kg.management.datum.core.model.anamnesis;

import java.io.Serializable;
import java.util.List;
import kg.management.datum.core.constants.ToothCondition;
import kg.management.datum.core.constants.ToothMobility;
import kg.management.datum.core.constants.ToothNumber;
import kg.management.datum.core.model.disease.Disease;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Tooth implements Serializable {
    private final ToothNumber number;
    private final ToothCondition condition;
    private final ToothMobility mobility;
    private final List<Disease> disease;

    public Tooth(ToothNumber number) {
        this(number, ToothCondition.NORMAL, ToothMobility.NORMAL);
    }

    public Tooth(ToothNumber number, ToothCondition condition, ToothMobility mobility) {
        this.number = number;
        this.condition = condition;
        this.mobility = mobility;
        disease = null;
    }

    public Tooth(ToothNumber number, ToothCondition condition, ToothMobility mobility, Disease disease) {
        this.number = number;
        this.condition = condition;
        this.mobility = mobility;
        this.disease = List.of(disease);
    }
}
