package kg.management.common;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class CyrillicLatinConverter {

    private final Map<Character, String> cyrToLat;
    private final Map<String, Character> latToCyr;
    private final int maxLatinLength;

    public CyrillicLatinConverter() {
        Map<Character, String> tempCyr = new HashMap<>();
        Map<String, Character> tempLat = new HashMap<>();

        add(tempCyr, tempLat, 'А', "A");
        add(tempCyr, tempLat, 'Б', "B");
        add(tempCyr, tempLat, 'В', "V");
        add(tempCyr, tempLat, 'Г', "G");
        add(tempCyr, tempLat, 'Д', "D");
        add(tempCyr, tempLat, 'Е', "E");
        add(tempCyr, tempLat, 'З', "Z");
        add(tempCyr, tempLat, 'И', "I");
        add(tempCyr, tempLat, 'К', "K");
        add(tempCyr, tempLat, 'Л', "L");
        add(tempCyr, tempLat, 'М', "M");
        add(tempCyr, tempLat, 'Н', "N");
        add(tempCyr, tempLat, 'О', "O");
        add(tempCyr, tempLat, 'П', "P");
        add(tempCyr, tempLat, 'Р', "R");
        add(tempCyr, tempLat, 'С', "S");
        add(tempCyr, tempLat, 'Т', "T");
        add(tempCyr, tempLat, 'У', "U");
        add(tempCyr, tempLat, 'Ф', "F");

        add(tempCyr, tempLat, 'Ё', "Yo");
        add(tempCyr, tempLat, 'Ж', "Zh");
        add(tempCyr, tempLat, 'Й', "J");
        add(tempCyr, tempLat, 'Х', "Kh");
        add(tempCyr, tempLat, 'Ц', "Ts");
        add(tempCyr, tempLat, 'Ч', "Ch");
        add(tempCyr, tempLat, 'Ш', "Sh");
        add(tempCyr, tempLat, 'Щ', "Shch");
        add(tempCyr, tempLat, 'Ъ', "\"");
        add(tempCyr, tempLat, 'Ы', "Y");
        add(tempCyr, tempLat, 'Ь', "\'");
        add(tempCyr, tempLat, 'Э', "E");
        add(tempCyr, tempLat, 'Ю', "Yu");
        add(tempCyr, tempLat, 'Я', "Ya");

        add(tempCyr, tempLat, 'Ө', "Ö");
        add(tempCyr, tempLat, 'Ү', "Ü");
        add(tempCyr, tempLat, 'Ң', "Ng");

        int maxLen = 0;
        for (String s : tempLat.keySet()) {
            if (s.length() > maxLen) maxLen = s.length();
        }
        maxLatinLength = maxLen;

        cyrToLat = Collections.unmodifiableMap(tempCyr);
        latToCyr = Collections.unmodifiableMap(tempLat);
    }

    private void add(Map<Character, String> cyrMap, Map<String, Character> latMap, char cyr, String lat) {
        cyrMap.put(cyr, lat);
        cyrMap.put(Character.toLowerCase(cyr), lat.toLowerCase());

        latMap.put(lat, cyr);
        latMap.put(lat.toLowerCase(), Character.toLowerCase(cyr));
    }

    public String toLatin(String text) {
        if (text == null || text.isEmpty()) return text;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String lat = cyrToLat.get(c);

            if (lat != null) {
                if (lat.length() > 1 && Character.isUpperCase(c)) {
                    boolean nextIsUpper = (i < text.length() - 1) && Character.isUpperCase(text.charAt(i + 1));
                    boolean prevIsUpper = (i > 0) && Character.isUpperCase(text.charAt(i - 1));

                    if (nextIsUpper || prevIsUpper) {
                        sb.append(lat.toUpperCase());
                    } else {
                        sb.append(Character.toUpperCase(lat.charAt(0)));
                        sb.append(lat.substring(1));
                    }
                } else {
                    sb.append(lat);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String toCyrillic(String text) {
        if (text == null || text.isEmpty()) return text;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            boolean matched = false;
            for (int len = maxLatinLength; len > 0; len--) {
                if (i + len <= text.length()) {
                    String chunk = text.substring(i, i + len);
                    Character cyrChar = latToCyr.get(chunk.toLowerCase());

                    if (cyrChar != null) {
                        if (Character.isUpperCase(chunk.charAt(0))) {
                            sb.append(Character.toUpperCase(cyrChar));
                        } else {
                            sb.append(cyrChar);
                        }
                        i += len;
                        matched = true;
                        break;
                    }
                }
            }
            if (!matched) {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}