package support.util;

import java.util.regex.Pattern;

public class Util {

    public static boolean isInteger(String string) {
        if (string == null) return false;
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isLong(String string) {
        if (string == null) return false;
        try {
            Long.parseLong(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDateOfBirth(String s) {
        return s.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])");
    }

    public static boolean isLocalTime(String s) {
        return s.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
    }

    public static boolean isValidEmail(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
