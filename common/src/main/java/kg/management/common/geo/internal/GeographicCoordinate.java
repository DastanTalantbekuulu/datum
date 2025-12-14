package kg.management.common.geo.internal;

import java.util.Locale;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public interface GeographicCoordinate {
    int MAX_VALUE_MINUTES = 59;
    double MAX_VALUE_SECONDS = 59.9999999999999d;

    int degrees();

    int minutes();

    double seconds();

    LatLonDirection direction();

    double toDouble();

    default double toRadians() {
        return Math.toRadians(toDouble());
    }

    /**
     * Returns a degrees-minutes-seconds formatted string for the specified locale.  For example,
     * <code>30°60'40.912"N</code>
     *
     * @return String representation of this object
     */
    default String toDmsString() {
        final DecimalFormat fmt = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.US));
        fmt.setMaximumFractionDigits(15);

        return String.format(Locale.US,
                "%d°%d'%s\"%s",
                degrees(),
                minutes(),
                fmt.format(seconds()),
                direction().getAbbreviation());
    }
}
