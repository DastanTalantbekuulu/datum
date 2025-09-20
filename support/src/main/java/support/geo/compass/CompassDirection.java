package support.geo.compass;

import java.math.BigDecimal;


public interface CompassDirection {

    String getAbbreviation();

    String getPrintName();

    BigDecimal getMinimum();

    BigDecimal getMiddle();

    BigDecimal getMaximum();

    CompassDirection getPrevious();

    CompassDirection getNext();
}
