package kg.management.datum.domain.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ToothCondition {
    NORMAL(0),
    CARIES(1),
    PULPIT(2),
    PERIODONTITIS(3),
    DEFECT(4),
    FILLING(5),
    CROWN(6),
    ARTIFICIAL(7),
    INLAY(8),
    IMPLANT(10),
    ROOT(11),
    MISSING(12),
    ;
    private final int index;
}
