package online.flowerinsnow.consolefilter.util;

public abstract class StringUtils {
    private StringUtils() {
    }

    public static String parseColour(String string) {
        return string.replace("&", "§").replace("§§", "&");
    }

    public static String greedyString(String[] src, int pos, String padding) {
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < src.length; i++) {
            sb.append(src[i]);
            if (i + 1 < src.length) {
                sb.append(padding);
            }
        }
        return sb.toString();
    }
}
