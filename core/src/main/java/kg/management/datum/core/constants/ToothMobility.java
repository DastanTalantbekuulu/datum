package kg.management.datum.core.constants;

import lombok.Getter;

@Getter
public class ToothMobility  {
    public static final ToothMobility NORMAL = new ToothMobility(0);
    public static final ToothMobility I = new ToothMobility(1);
    public static final ToothMobility II = new ToothMobility(2);
    public static final ToothMobility III = new ToothMobility(3);

    private final int index;

    private ToothMobility(int index){
        this.index = index;
    }
}
