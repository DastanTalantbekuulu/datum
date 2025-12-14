package kg.management.common.model;

import lombok.Builder;

@Builder
public record FieldConfig(String name, String label, String type, boolean readOnly) {
}