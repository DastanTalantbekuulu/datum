package kg.management.common.geo;


import kg.management.common.geo.internal.GeographicCoordinate;
import kg.management.common.geo.internal.LatLonDirection;

import static kg.management.common.geo.exception.ExceptionMessages.DIRECTION_CANT_BE_NEITHER;
import static kg.management.common.geo.exception.ExceptionMessages.DIRECTION_NULL;
import static kg.management.common.geo.exception.ExceptionMessages.LAT_LON_RANGE_ERROR;
import static kg.management.common.geo.internal.Objects.failIf;

/**
 * Lines of longitude run parallel to the Prime Meridian (perpendicular to the Equator).  Longitude denotes whether a
 * location is east or west of the Prime Meridian.  The Prime Meridian is located at longitude 0.
 */
public record Longitude (
        int degrees,
        int minutes,
        double seconds,
        Direction direction
) implements GeographicCoordinate {

    /** Indicates whether a location is north or south of the Prime Meridian, or on the Prime Meridian */
    public enum Direction implements LatLonDirection {
        /** Indicates that the location is east of the Prime Meridian */
        EAST("E"),

        /** Indicates that the location is west of the Prime Meridian */
        WEST("W"),

        /** Indicates that the location is on the Prime Meridian (neither east not west:  the longitude is exactly 0.0). */
        NEITHER("");

        private final String abbreviation;

        Direction(final String abbr) {
            this.abbreviation = abbr;
        }

        @Override
        public String getAbbreviation() {
            return abbreviation;
        }
    }

    /**
     * When expressed as a floating-point number, valid longitudes sit in a range of +/- 180.0.  When expressed as
     * degrees/minutes/seconds, the valid range for degrees is 0-180, with minutes and seconds equal to 0 when
     * degrees is 180.
     */
    public static final double MAX_VALUE = 180.0;


    /**
     * Creates a new longitude object
     *
     * @param degrees   - Accepted range [0-180]
     * @param minutes   - Accepted range [0-59] unless {@code degrees} is 180, in which case {@code minutes} must be 0
     * @param seconds   - Accepted range [0-59.9999999999999] unless {@code degrees} is 180, in which case {@code seconds} must be 0
     * @param direction - A {@linkplain Direction}
     * @throws IllegalArgumentException If any arguments fall outside their accepted ranges, or if degrees/minutes/seconds
     *                                  are all 0 with a {@code direction} other than {@linkplain Direction#NEITHER}
     */
    public Longitude {
        failIf(degrees < 0 || degrees > MAX_VALUE, Longitude::getRangeError);
        failIf(minutes < 0 || minutes > MAX_VALUE_MINUTES, Longitude::getRangeError);
        failIf(seconds < 0.0 || seconds > MAX_VALUE_SECONDS, Longitude::getRangeError);
        failIf(degrees == MAX_VALUE && (minutes > 0 || seconds > 0.0), Longitude::getRangeError);
        failIf(direction == null, () -> DIRECTION_NULL);
        failIf(direction == Direction.NEITHER && !(degrees == 0 && minutes == 0 && seconds == 0.0d), () -> DIRECTION_CANT_BE_NEITHER);
    }

    /**
     * Creates a new longitude object
     *
     * @param longitude - A signed value.  Positive values are east; negative values are west.  Note that a value
     *                    of 0.0 is the Prime Meridian, which is neither east nor west.  If you supply a value of
     *                    0.0, the {@code direction} will be initialized to {@link Direction#NEITHER}.
     * @throws IllegalArgumentException If the supplied value falls outside +/- {@linkplain Longitude#MAX_VALUE}
     */
    public Longitude(final double longitude) {
        this(
            ((int) Math.abs(longitude)),
            ((int) ((Math.abs(longitude) - (int) Math.abs(longitude)) * 60.0d)),
            ((((Math.abs(longitude) - (int) Math.abs(longitude)) * 60.0d) % 1.0d) * 60.0d),
            switch ((int) Math.signum(longitude)) {
                case 1 -> Direction.EAST;
                case -1 -> Direction.WEST;
                default -> Direction.NEITHER;
            }
        );
    }

    public double toDouble() {
        final double decimal = degrees() + (minutes() / 60.0d) + (seconds() / 3600.0d);
        return direction() == Direction.EAST ? decimal : -decimal;
    }

    /** Returns the string representation provided by {@link #toDmsString()} */
    @Override
    public String toString() {
        return toDmsString();
    }

    public static String getRangeError() {
        return LAT_LON_RANGE_ERROR.replaceAll("\n", "%n").formatted(MAX_VALUE, MAX_VALUE, (int) MAX_VALUE, (int) MAX_VALUE);
    }
}
