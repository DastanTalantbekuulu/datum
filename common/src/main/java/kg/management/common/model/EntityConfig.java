package kg.management.common.model;

import lombok.Builder;

import java.util.List;

@Builder
public record EntityConfig(String resourceName, String label, List<FieldConfig> fields) {
}
