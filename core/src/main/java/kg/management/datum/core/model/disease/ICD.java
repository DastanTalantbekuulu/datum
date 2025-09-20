package kg.management.datum.core.model.disease;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ICD implements Disease {

    protected static final Version ICD10 = new Version(10);
    protected static final Version ICD11 = new Version(11);

    private final int id;
    private final String name;
    private final String code;
    private final int parentId;
    private final String parentCode;
    private final int nodeCount;
    private final String additionalInfo;
    private final Version version;
}
