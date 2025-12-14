package kg.management.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
@RequiredArgsConstructor
public enum Day {
    SUNDAY(1, DayOfWeek.SUNDAY),
    MONDAY(2, DayOfWeek.MONDAY),
    TUESDAY(3, DayOfWeek.TUESDAY),
    WEDNESDAY(4, DayOfWeek.WEDNESDAY),
    THURSDAY(5, DayOfWeek.THURSDAY),
    FRIDAY(6, DayOfWeek.FRIDAY),
    SATURDAY(7, DayOfWeek.SATURDAY);

    private final int number;
    private final DayOfWeek javaTimeDay;

    public String getEnglishFullName() {
        return javaTimeDay.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public String getEnglishShortName() {
        return javaTimeDay.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    public String getRussianFullName() {
        return javaTimeDay.getDisplayName(TextStyle.FULL, Locale.of("ru"));
    }

    public String getRussianShortName() {
        return javaTimeDay.getDisplayName(TextStyle.SHORT, Locale.of("ru"));
    }

    public String getDisplayName(TextStyle style, Locale locale) {
        return javaTimeDay.getDisplayName(style, locale);
    }

    public static Day of(int number) {
        if (number < 1 || number > 7) {
            throw new IllegalArgumentException("Day number must be between 1 and 7");
        }
        return values()[number - 1];
    }

    public DayOfWeek toJavaTime() {
        return javaTimeDay;
    }
}