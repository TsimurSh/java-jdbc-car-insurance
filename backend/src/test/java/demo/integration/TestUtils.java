package demo.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUtils {
    private static final String API_URL = "http://localhost:8080";

    public static String getResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder json = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            json.append(inputLine);
        }

        in.close();
        con.disconnect();

        System.out.println("Got response string: " + json);

        return json.toString();
    }

    public static HttpURLConnection sendGetRequest(String path, String cookieValue) throws IOException {
        URL url = new URL(API_URL + path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Cookie", cookieValue);
        con.setRequestProperty("token", cookieValue);
        con.setRequestMethod("GET");
        return con;
    }
}
