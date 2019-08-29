package portal.util;

public class TextFormatter {

    public static String capitalize(String w) {
        String[] words = w.split("\\s");
        String[] r = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            r[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", r);
    }

}
