package support.geo;

import java.math.BigDecimal;
import kg.geo.compass.CompassDirection;
import lombok.Getter;
import lombok.Setter;
import static java.math.BigDecimal.ZERO;
import static kg.geo.exception.ExceptionMessages.BEARING_NULL;
import static kg.geo.exception.ExceptionMessages.BEARING_OUT_OF_RANGE;
import static kg.geo.internal.Objects.failIf;

/**
 * A class containing an exact bearing and a mapping of the bearing to a general compass direction
 *
 * @param <T> An implementation of {@linkplain CompassDirection}
 */
@Getter
public class Bearing<T extends CompassDirection> {

    @Setter
    private T compassDirection;
    private BigDecimal bearing;

    public Bearing() {
    }

    public Bearing(final T compassDirection, final BigDecimal bearing) {
        setCompassDirection(compassDirection);
        setBearing(bearing);
    }

    public void setBearing(final BigDecimal bearing) {
        failIf(bearing == null, () -> BEARING_NULL);
        failIf((bearing.compareTo(ZERO) < 0) || bearing.compareTo(new BigDecimal(360)) > 0,
                () -> BEARING_OUT_OF_RANGE.formatted(bearing.toPlainString()));

        this.bearing = bearing;
    }
}
