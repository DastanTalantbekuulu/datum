package kg.management.identity;

import java.util.Locale;

public class I18n {
    static void main() {
        String[] isoLanguages = Locale.getISOLanguages();
        for (String language : isoLanguages) {
            System.out.println(language);
        }
    }
}
