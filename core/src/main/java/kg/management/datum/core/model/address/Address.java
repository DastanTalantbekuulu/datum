package kg.management.datum.core.model.address;

import kg.management.datum.core.constants.Country;

public record Address(
        Country country,
        String region,
        String city,
        String street,
        String house,
        GeoPoint geoPoint
) {
    public Address(
            Country country,
            String region,
            String city,
            String street,
            String house
    ) {
        this(country, region, city, street, house, null);
    }
}
