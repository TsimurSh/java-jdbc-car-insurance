package demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import demo.model.Vehicle;
import demo.repository.InsuranceOfferRepository;
import demo.repository.VehicleRepository;
import demo.service.VehicleService;
import demo.utils.TokenUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import static demo.utils.HttpUtils.allowCors;
import static demo.utils.HttpUtils.allowCorsForOptionsRequest;

public class VehicleController implements HttpHandler {
    private final VehicleRepository vehicleRepository = new VehicleRepository();
    private final InsuranceOfferRepository insuranceOfferRepository = new InsuranceOfferRepository();
    private final VehicleService vehicleService = new VehicleService(vehicleRepository, insuranceOfferRepository);

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (allowCorsForOptionsRequest(httpExchange)) return; // if OPTIONS request - return

        List<Vehicle> response = Collections.emptyList();
        if ("GET".equals(httpExchange.getRequestMethod())) {
            response = parseGetRequest(httpExchange);
        } else {
            System.out.println(httpExchange.getRequestMethod() + " is unsupported yet");
        }
        sendResponse(httpExchange, response);
    }

    private List<Vehicle> parseGetRequest(HttpExchange httpExchange) {
        String userIdFromCookie = getUserIdFromCookie(httpExchange).toString();
        long userId = Long.parseLong(userIdFromCookie);
        return vehicleService.findAllByUserId(userId);
    }

    private void sendResponse(HttpExchange exchange, List<Vehicle> vehicles) throws IOException {
        Headers respHeaders = exchange.getResponseHeaders();
        allowCors(respHeaders);
        int status = vehicles == null || vehicles.isEmpty() ? 404 : 200;

        String json = OBJECT_MAPPER.writeValueAsString(vehicles);

        exchange.sendResponseHeaders(status, json.length());

        OutputStream outputStream = exchange.getResponseBody();

        outputStream.write(json.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private static Long getUserIdFromCookie(HttpExchange httpExchange) {
        final Headers requestHeaders = httpExchange.getRequestHeaders();
        List<String> cookie = requestHeaders.get("Authorization");
        String token = cookie.isEmpty()
                ? null
                : getValue(cookie);
        System.out.println("🔐 Token from Cookies = " + token);
        return TokenUtils.decode(token);
    }

    private static String getValue(List<String> cookie) {
        String first = cookie.get(0);
        return first;
    }
}
