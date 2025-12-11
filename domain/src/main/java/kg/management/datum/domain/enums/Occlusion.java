package kg.management.datum.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Occlusion {
    NORMAL("NORMAL", 0),
    DISTAL("DISTAL", 1),
    MESIAL("MESIAL", 2),
    OPEN("OPEN", 3),
    DEEP("DEEP", 4),
    CROSS("CROSS", 5),
    DISTOPIA("DISTOPIA", 6),
    DIASTEMA("DIASTEMA", 7),
    ;
    private final String text;
    private final int index;
}
