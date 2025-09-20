package kg.management.datum.core.model.clinic;

import javax.money.MonetaryAmount;

public record Price(
        long id,
        long clinicId,
        long serviceId,
        String name,
        MonetaryAmount amount,
        String description
) {
}
