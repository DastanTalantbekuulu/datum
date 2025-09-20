package kg.management.datum.core.model.employee;

public record Employee(
        long id,
        long departmentId,
        long userId,
        long postId,
        String name,
        String description
) {
}
