package kg.management.datum.core.constants;

import lombok.Getter;

@Getter
public class Country {

    public static final Country USD = new Country("United States", "US", "USD", 840);
    public static final Country RUS = new Country("Russian Federation", "RU", "RUS", 643);
    public static final Country KGZ = new Country("Kyrgyzstan", "KG", "KGZ", 417);
    public static final Country KAZ = new Country("Kazakhstan", "KZ", "KAZ", 398);
    public static final Country UZB = new Country("Uzbekistan", "UZ", "UZB", 860);

    private final String name;
    private final String alpha2;
    private final String alpha3;
    private final int numeric;

    private Country(
            String name,
            String alpha2,
            String alpha3,
            int numeric
    ) {
        this.name = name;
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.numeric = numeric;
    }
}
