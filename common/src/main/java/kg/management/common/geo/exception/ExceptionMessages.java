package kg.management.common.geo.exception;

public class ExceptionMessages {

    public static final String LAT_LON_RANGE_ERROR = """
        Value out of range.  Values must be within the following ranges (inclusive):

        Decimal:  -%f to %f

        Degrees:  0 to %d
        Minutes:  0 to 59
        Seconds:  0 to 59.9[...]
        (Minutes and seconds must be 0 when degrees is %d)
        """;
    public static final String DIRECTION_NULL = "Direction is null";
    public static final String DIRECTION_CANT_BE_NEITHER = "Direction can only be NEITHER when the value is 0.0";
    public static final String BEARING_OUT_OF_RANGE = "Bearing [%s] is out of range [0, 360]";
    public static final String COMPASS_TYPE_NULL = "Compass type is null";
    public static final String BEARING_NULL = "Bearing is null";
    public static final String LATITUDE_NULL = "Latitude is null";
    public static final String LONGITUDE_NULL = "Longitude is null";

    public static final String STARTING_POINT_NULL = "Starting point is null";
    public static final String BEARING_TO_NULL = "'to' is null";
    public static final String BEARING_FROM_LATITUDE_NULL = "'from' latitude is null";
    public static final String BEARING_FROM_LONGITUDE_NULL = "'from' longitude is null";
    public static final String BEARING_TO_LATITUDE_NULL = "'to' latitude is null";
    public static final String BEARING_TO_LONGITUDE_NULL = "'to' longitude is null";
}
