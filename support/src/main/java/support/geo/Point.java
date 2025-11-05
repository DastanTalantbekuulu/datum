package support.geo;

import static support.geo.exception.ExceptionMessages.LATITUDE_NULL;
import static support.geo.exception.ExceptionMessages.LONGITUDE_NULL;
import static support.geo.internal.Objects.failIf;

/**
 * This class is a thin wrapper of a {@linkplain Latitude} and {@linkplain Longitude}.
 * It adds a {@link Point#Point(Latitude, Longitude, String) name} field.
 */
public record Point(Latitude latitude, Longitude longitude, String name) {

    /**
     * Creates a new Point object
     *
     * @param latitude  The {@linkplain Latitude}
     * @param longitude The {@linkplain Longitude}
     * @param name      Use for identification, such as displaying a caption on a map
     * @throws IllegalArgumentException If latitude or longitude is null
     */
    public Point {
        failIf(latitude == null, () -> LATITUDE_NULL);
        failIf(longitude == null, () -> LONGITUDE_NULL);
    }

    /**
     * Creates a new Point object
     *
     * @param latitude  {@linkplain Latitude}
     * @param longitude {@linkplain Longitude}
     * @throws IllegalArgumentException If any parameter is null
     */
    public Point(final Latitude latitude, final Longitude longitude) {
        this(latitude, longitude, null);
    }

    @Override
    public String toString() {
        return name != null
            ? "%s {%s , %s}".formatted(name, latitude, longitude)
            : "{%s , %s}".formatted(latitude, longitude);
    }
}
