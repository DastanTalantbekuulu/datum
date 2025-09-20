package kg.management.datum.core.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Occlusion {

    public static final Occlusion NORMAL = new Occlusion("NORMAL", 0);
    public static final Occlusion DISTAL = new Occlusion("DISTAL", 1);
    public static final Occlusion MESIAL = new Occlusion("MESIAL", 2);
    public static final Occlusion OPEN = new Occlusion("OPEN", 3);
    public static final Occlusion DEEP = new Occlusion("DEEP", 4);
    public static final Occlusion CROSS = new Occlusion("CROSS", 5);
    public static final Occlusion DISTOPIA = new Occlusion("DISTOPIA", 6);
    public static final Occlusion DIASTEMA = new Occlusion("DIASTEMA", 7);

    private final int index;
    private final String name;

    private Occlusion(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
