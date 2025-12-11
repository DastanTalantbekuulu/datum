package kg.management.datum.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ToothMobility {
    NORMAL(0),
    I(1),
    II(2),
    III(3),
    ;
    private final int index;
}
