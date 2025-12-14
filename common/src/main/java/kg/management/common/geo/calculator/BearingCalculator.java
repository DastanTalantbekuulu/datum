package kg.management.common.geo.calculator;

import kg.management.common.geo.Bearing;
import kg.management.common.geo.Point;
import kg.management.common.geo.compass.CompassDirection;
import kg.management.common.geo.compass.CompassDirection16;
import kg.management.common.geo.compass.CompassDirection32;
import kg.management.common.geo.compass.CompassDirection8;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static kg.management.common.geo.exception.ExceptionMessages.BEARING_NULL;
import static kg.management.common.geo.exception.ExceptionMessages.BEARING_OUT_OF_RANGE;
import static kg.management.common.geo.exception.ExceptionMessages.BEARING_TO_NULL;
import static kg.management.common.geo.exception.ExceptionMessages.COMPASS_TYPE_NULL;
import static kg.management.common.geo.exception.ExceptionMessages.STARTING_POINT_NULL;
import static kg.management.common.geo.internal.Objects.failIf;


/**
 * This class calculates initial bearing and back azimuth.
 *
 * <p><strong>
 * THIS IS HOBBYIST SOFTWARE.  THE AUTHOR HAS NO BACKGROUND IN, OR EVEN AN UNDERSTANDING OF, GEODESY, AND MERELY
 * IMPLEMENTED FORMULAS FOUND ONLINE.  DON'T ENTRUST YOUR SAFETY TO THIS SOFTWARE.  NOW WOULD BE A GOOD TIME TO
 * READ AND UNDERSTAND THE WAIVER PRESENT IN THIS SOFTWARE'S LICENSE.
 * </strong></p>
 */
public class BearingCalculator {

    private static final BigDecimal BD_360 = new BigDecimal(360);
    private static final BigDecimal BD_180 = new BigDecimal(180);
    private static final BigDecimal BD_NEG_180 = new BigDecimal(-180);


    /**
     * <p>
     * Calculates the initial bearing that will take you from point A to point B.
     * Keep in mind that the bearing will change over the course of the trip and will need to be recalculated.
     * </p>
     *
     * <p><strong>
     * THIS IS HOBBYIST SOFTWARE.  THE AUTHOR HAS NO BACKGROUND IN, OR EVEN AN UNDERSTANDING OF, GEODESY, AND MERELY
     * IMPLEMENTED FORMULAS FOUND ONLINE.  DON'T ENTRUST YOUR SAFETY TO THIS SOFTWARE.  NOW WOULD BE A GOOD TIME TO
     * READ AND UNDERSTAND THE WAIVER PRESENT IN THIS SOFTWARE'S LICENSE.
     * </strong></p>
     *
     * @param compassType The returned {@code Bearing} will be parameterized with this type, allowing you to safely cast it
     * @param from        The departing point
     * @param to          The destination point
     * @return The initial bearing from A to B, and a mapping of the bearing to an 8, 16 or 32-point compass direction, depending on {@code compassType}
     * @see <a href="http://www.movable-type.co.uk/scripts/latlong.html">http://www.movable-type.co.uk/scripts/latlong.html</a>.
     */
    public static <T extends CompassDirection> Bearing<T> initialBearing(
            final Class<T> compassType,
            final Point from,
            final Point to
    ) {
        return newBearing(compassType, calculateBearing(from, to));
    }

    /**
     * Calculates the back azimuth - the bearing that gets you back to your starting point
     *
     * <p><strong>
     * THIS IS HOBBYIST SOFTWARE.  THE AUTHOR HAS NO BACKGROUND IN, OR EVEN AN UNDERSTANDING OF, GEODESY, AND MERELY
     * IMPLEMENTED FORMULAS FOUND ONLINE.  DON'T ENTRUST YOUR SAFETY TO THIS SOFTWARE.  NOW WOULD BE A GOOD TIME TO
     * READ AND UNDERSTAND THE WAIVER PRESENT IN THIS SOFTWARE'S LICENSE.
     * </strong></p>
     *
     * @param compassType    The returned {@code Bearing} will be parameterized with this type, allowing you to safely cast it
     * @param initialBearing The initial bearing
     * @return The back azimuth based on initial bearing
     */
    public static <T extends CompassDirection> Bearing<T> backAzimuth(
            final Class<T> compassType,
            final BigDecimal initialBearing
    ) {
        return newBearing(compassType, calculateBackAzimuth(initialBearing));
    }

    private static <T extends CompassDirection> Bearing<T> newBearing(
            final Class<T> compassType,
            final BigDecimal angle
    ) {
        failIf(compassType == null, COMPASS_TYPE_NULL);

        if (compassType.equals(CompassDirection8.class)) {
            return new Bearing(CompassDirection8.getByBearing(angle), angle);
        } else if (compassType == CompassDirection16.class) {
            return new Bearing(CompassDirection16.getByBearing(angle), angle);
        } else if (compassType == CompassDirection32.class) {
            return new Bearing(CompassDirection32.getByBearing(angle), angle);
        }

        throw new IllegalArgumentException(COMPASS_TYPE_NULL);
    }

    private static BigDecimal calculateBearing(final Point from, final Point to) {
        failIf(from == null, STARTING_POINT_NULL);
        failIf(to == null, BEARING_TO_NULL);

        final double fromLatRadians = from.latitude().toRadians(),
                fromLonRadians = from.longitude().toRadians(),
                toLatRadians = to.latitude().toRadians(),
                toLonRadians = to.longitude().toRadians(),
                deltaLon = toLonRadians - fromLonRadians;

        final double y = Math.sin(deltaLon) * Math.cos(toLatRadians);
        final double x = Math.cos(fromLatRadians) * Math.sin(toLatRadians)
                         - Math.sin(fromLatRadians)
                           * Math.cos(toLatRadians)
                           * Math.cos(deltaLon);

        final double bearing = Math.toDegrees(Math.atan2(y, x));
        final double normalizedBearing = normalizeBearing(bearing);

        return new BigDecimal(normalizedBearing);
    }

    private static BigDecimal calculateBackAzimuth(final BigDecimal bearing) {
        final BigDecimal zeroedBearing;
        BigDecimal backAzimuth;

        failIf(bearing == null, BEARING_NULL);
        failIf(bearing.compareTo(ZERO) < 0 || bearing.compareTo(BD_360) > 0,
                () -> BEARING_OUT_OF_RANGE.formatted(bearing.toPlainString()));

        zeroedBearing = bearing.compareTo(BD_360) == 0 ? ZERO : bearing;

        if (zeroedBearing.compareTo(BD_180) == 0 || zeroedBearing.compareTo(BD_NEG_180) == 0) {
            backAzimuth = ZERO;
        } else if (zeroedBearing.compareTo(BD_180) < 0) {
            backAzimuth = zeroedBearing.add(BD_180);
        } else {
            backAzimuth = zeroedBearing.subtract(BD_180);
        }

        backAzimuth = normalizeBearing(backAzimuth);

        return backAzimuth;
    }

    private static double normalizeBearing(final double bearing) {
        return (bearing + 360) % 360;
    }

    private static BigDecimal normalizeBearing(final BigDecimal bearing) {
        return bearing.add(BD_360).remainder(BD_360);
    }
}
