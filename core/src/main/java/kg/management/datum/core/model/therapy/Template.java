package kg.management.datum.core.model.therapy;

public record Template(
        long id,
        long diseaseId,
        long priceId,
        String name,
        String description
) {
}
