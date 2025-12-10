package support.model;

import lombok.Builder;

@Builder
public record FieldConfig(String name, String label, String type, boolean readOnly) {
}