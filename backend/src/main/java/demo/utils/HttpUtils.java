package demo.utils;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class HttpUtils {

    public static void allowCors(Headers respHeaders) {
        List<String> all = asList("*");
        List<String> methods = asList("GET", "POST", "OPTIONS");
        respHeaders.put("Access-Control-Allow-Methods", methods);
        respHeaders.put("Access-Control-Allow-Credentials", asList("true"));
        respHeaders.put("Access-Control-Allow-Origin", all);
        respHeaders.put("Access-Control-Allow-Headers", all);
        respHeaders.put("Access-Control-Expose-Headers", all);
        respHeaders.put("Access-Control-Allow-Credentials-Header", all);
    }

    public static boolean allowCorsForOptionsRequest(HttpExchange httpExchange) throws IOException {
        // CORS
        if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Credentials-Header", "*");
            httpExchange.sendResponseHeaders(204, -1);
            return true;
        }
        return false;
    }

    private HttpUtils() {
    }
}
