package kg.management.datum.core.constants;

import lombok.Getter;

@Getter
public class ToothCondition {
    public static final ToothCondition NORMAL = new ToothCondition(0);
    public static final ToothCondition CARIES = new ToothCondition(1);
    public static final ToothCondition PULPIT = new ToothCondition(2);
    public static final ToothCondition PERIODONTITIS = new ToothCondition(3);
    public static final ToothCondition DEFECT = new ToothCondition(4);
    public static final ToothCondition FILLING = new ToothCondition(5);
    public static final ToothCondition CROWN = new ToothCondition(6);
    public static final ToothCondition ARTIFICIAL = new ToothCondition(7);
    public static final ToothCondition INLAY = new ToothCondition(8);
    public static final ToothCondition IMPLANT = new ToothCondition(10);
    public static final ToothCondition ROOT = new ToothCondition(11);
    public static final ToothCondition MISSING = new ToothCondition(12);

    private final int index;

    private ToothCondition(int index) {
        this.index = index;
    }
}
