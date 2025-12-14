package kg.management.common.geo.calculator;

import kg.management.common.geo.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static kg.management.common.geo.internal.Objects.failIf;


public class DistanceCalculator {

    /** Units of distance - use this with the {@code distance} method in this class. */
    public enum Unit {
        // Members are initialized with a conversion factor expressed in terms of 1 kilometer.

        CENTIMETERS(100000.0d),

        INCHES(39370.1d),

        /** This is the international foot.  For those in the U.S., yes, that is the foot you are accustomed to (12 inches = 1 ft). */
        FEET(1000.0d / .3048d),

        KILOMETERS(1),

        METERS(1000),

        MILES(1.0d / 1.609344d),

        /**
         * This is the <strong>international</strong> nautical mile.  It's not to be confused with:
         * <ol>
         *    <li>The U.S. nautical mile, which was abandoned in 1954</li>
         *    <li>The Imperial (UK) nautical mile (also known as the Admiralty mile), which was abandoned in 1970</li>
         * </ol>
         *
         * @see <a href="https://en.wikipedia.org/wiki/Nautical_mile">https://en.wikipedia.org/wiki/Nautical_mile</a>
         */
        NAUTICAL_MILES(1.0d / 1.852d),

        /**
         * <p>
         * For those of you living in the U.S., the U.S. Survey Foot is NOT the foot you think of when you think of
         * feet.  That is the {@link Unit#FEET international foot}.  The survey foot is used in geodetic surveys.
         * As defined by the National Bureau of Standards in 1959:
         * </p>
         *
         * <p>
         * "Any data expressed in feet derived from and published as a result of geodetic surveys within the United
         * States will continue to bear the following relationship as defined in 1893:  1 foot = 1200/3937 meter"
         * </p>
         *
         * @see <a href="http://www.ngs.noaa.gov/PUBS_LIB/FedRegister/FRdoc59-5442.pdf">http://www.ngs.noaa.gov/PUBS_LIB/FedRegister/FRdoc59-5442.pdf</a>
         */
        US_SURVEY_FEET(1000.0d / (1200 / 3937.0d)),

        YARDS(1000.0d / .9144d);

        private final double perKilometer;

        Unit(final double perKilometer) {
            this.perKilometer = perKilometer;
        }
    }

    /**
     * @see <a href="http://nssdc.gsfc.nasa.gov/planetary/factsheet/earthfact.html">http://nssdc.gsfc.nasa.gov/planetary/factsheet/earthfact.html</a>
     */
    private static final double EARTH_RADIUS_KILOMETERS = 6371;


    /**
     * <p>
     * Gets the total distance between an unlimited number of {@linkplain Point}s.  For example, if the distance from
     * point A to point B is 3, and the distance from point B to point C is 2, the total distance traveled will be
     * (3 + 2) = 5.  Just pass {@code Point}s in the order in which they're visited.
     * </p>
     *
     * <p><strong>
     * THIS IS HOBBYIST SOFTWARE.  THE AUTHOR HAS NO BACKGROUND IN, OR EVEN AN UNDERSTANDING OF, GEODESY, AND MERELY
     * IMPLEMENTED FORMULAS FOUND ONLINE.  DON'T ENTRUST YOUR SAFETY TO THIS SOFTWARE.  NOW WOULD BE A GOOD TIME TO
     * READ AND UNDERSTAND THE WAIVER PRESENT IN THIS SOFTWARE'S LICENSE.
     * </strong></p>
     *
     * @param unit   The unit that the returned value will be expressed in
     * @param points {@linkplain Point}s arranged in the order in which they are visited.  You must provide at least 2,
     *               otherwise a {@linkplain IllegalArgumentException} will be thrown.
     * @return The total distance traveled, expressed in terms of {@code unit}
     */
    public static double distance(final Unit unit, final Point... points) {
        return distance(unit, Arrays.stream(points).collect(Collectors.toList()));
    }

    /**
     * <p>
     * Gets the total distance between an unlimited number of {@linkplain Point}s.  For example, if the distance from
     * point A to point B is 3, and the distance from point B to point C is 2, the total distance traveled will be
     * (3 + 2) = 5.  Just pass {@code Point}s in the order in which they're visited.
     * </p>
     *
     * <p><strong>
     * THIS IS HOBBYIST SOFTWARE.  THE AUTHOR HAS NO BACKGROUND IN, OR EVEN AN UNDERSTANDING OF, GEODESY, AND MERELY
     * IMPLEMENTED FORMULAS FOUND ONLINE.  DON'T ENTRUST YOUR SAFETY TO THIS SOFTWARE.  NOW WOULD BE A GOOD TIME TO
     * READ AND UNDERSTAND THE WAIVER PRESENT IN THIS SOFTWARE'S LICENSE.
     * </strong></p>
     *
     * @param unit   The unit that the returned value will be expressed in
     * @param points {@linkplain Point}s arranged in the order in which they are visited.  You must provide at least 2,
     *               otherwise a {@linkplain IllegalArgumentException} will be thrown.
     * @return The total distance traveled, expressed in terms of {@code unit}
     */
    public static double distance(final Unit unit, final List<Point> points) {
        failIf(unit == null, () -> "Unit is null");
        failIf(points == null, () -> "Points are null");
        failIf(points.size() < 2, () -> "Need to provide at least 2 points");

        double distance = 0;
        Point previous = points.get(0);

        for (int i = 1; i < points.size(); i++) {
            final Point current = points.get(i);

            failIf(previous == null, "point %d is null".formatted(i - 1));
            failIf(current == null, "point %d is null".formatted(i));

            final double lat1 = previous.latitude().toRadians(),
                    lat2 = current.latitude().toRadians(),
                    lon1 = previous.longitude().toRadians(),
                    lon2 = current.longitude().toRadians(),
                    deltaLat = lat2 - lat1,
                    deltaLon = lon2 - lon1;

            final double d = (2.0d * EARTH_RADIUS_KILOMETERS)
                             * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2.0d), 2.0d)
                                                   + (Math.cos(lat1) * Math.cos(lat2)
                                                      * Math.pow(Math.sin(deltaLon / 2.0d), 2.0d))));

            distance += d * unit.perKilometer;
            previous = current;
        }

        return distance;
    }
}
