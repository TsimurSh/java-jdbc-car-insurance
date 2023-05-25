package demo.utils;

public class TokenUtils {

    public static final String SECRET = "SECRET_TOKEN-";

    public static String encode(Long userId) {
        return SECRET + userId;
    }

    public static Long decode(String token) {
        return Long.parseLong(token.replace(SECRET, ""));
    }

    private TokenUtils() {
    }
}
