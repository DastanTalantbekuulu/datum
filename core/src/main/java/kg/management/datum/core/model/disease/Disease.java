package kg.management.datum.core.model.disease;

public interface Disease {
    int getId();

    String getName();

    String getCode();

    int getParentId();

    String getParentCode();

    int getNodeCount();

    String getAdditionalInfo();

    Version getVersion();
}
