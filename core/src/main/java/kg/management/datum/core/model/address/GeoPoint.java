package kg.management.datum.core.model.address;

import lombok.Getter;

@Getter
public class GeoPoint {

    private final double lat;
    private final double lon;
    private final double accuracy;

    public GeoPoint(double lat, double lon) {
        this(lat, lon, -1d);
    }

    public GeoPoint(double lat, double lon, double accuracy) {
        this.lat = lat;
        this.lon = lon;
        this.accuracy = accuracy < 0 ? -1d : accuracy;
    }

    public String toString() {
        return "lat = " + this.lat + "; lon = " + this.lon + (this.accuracy < 0 ? "" : ("; accuracy = " + this.accuracy));
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoPoint g)) return false;
        return g.lat == this.lat && g.lon == this.lon && g.accuracy == this.accuracy;
    }


}