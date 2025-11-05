package support.geo.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import static support.geo.internal.Objects.failIf;

public class EnumHelper {

    /**
     * Populate a map of enum members using a configurable getter from the enum as the key supplier
     *
     * @param enumClass    The enumeration
     * @param keyExtractor A method reference on the enum which supplies the key value for the map
     * @return A map containing all members of the enum
     * @throws IllegalArgumentException If the keyExtractor does not provide unique values, or if any parameter is null
     */
    public static <E extends Enum<E>, K> Map<K, E> populateEnumMap(
            final Class<E> enumClass,
            final Function<E, K> keyExtractor
    ) {
        failIf(enumClass == null, () -> "enumClass is null");
        failIf(keyExtractor == null, () -> "keyExtractor is null");

        final Map<K, E> map = new LinkedHashMap<>();

        for (final E enumObj : enumClass.getEnumConstants()) {
            if (map.put(keyExtractor.apply(enumObj), enumObj) != null) {
                throw new IllegalArgumentException("The specified key extractor does not provide unique values");
            }
        }

        return map;
    }
}
