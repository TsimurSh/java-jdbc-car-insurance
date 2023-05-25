package demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import demo.model.LoginResponseDto;
import demo.model.UserPrincipal;
import demo.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static demo.utils.HttpUtils.allowCors;
import static demo.utils.HttpUtils.allowCorsForOptionsRequest;
import static demo.utils.TokenUtils.encode;
import static java.util.Arrays.asList;

public class LoginController implements HttpHandler {
    private final UserService userService = new UserService();
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (allowCorsForOptionsRequest(httpExchange)) return; // if OPTIONS request - return

        System.out.println("Got login request...");
        Long userId = null;

        if ("POST".equals(httpExchange.getRequestMethod())) {
            userId = checkUserExistsAndGetUserId(httpExchange);
        }

        sendResponse(httpExchange, userId);
    }


    private Long checkUserExistsAndGetUserId(HttpExchange httpExchange) {
        UserPrincipal loginData = parseGetRequest(httpExchange);
        // TODO: userId будет ноль если юзер ввел неправильный пароль или логин
        Long userId = userService.checkUserExistsAndPasswordMatchedAndGetUserId(loginData);
        return userId;
    }

    private UserPrincipal parseGetRequest(HttpExchange httpExchange) {
        InputStream requestBody = httpExchange.getRequestBody();
        try {
            UserPrincipal userPrincipal = OBJECT_MAPPER.readValue(requestBody, UserPrincipal.class);
            return userPrincipal;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Can't parse /login request", e);
        }
    }

    private void sendResponse(HttpExchange exchange, Long userId) throws IOException {
        boolean isLoggedId = userId != null; // userId если ноль - значит юзер ввел неправильный пароль
        int status = isLoggedId ? 200 : 401;

        String secretTokenWithUserId = encode(userId); // закодировать и проставим в тело и в куки

        Headers respHeaders = exchange.getResponseHeaders();
        allowCors(respHeaders);

        setToken(secretTokenWithUserId, respHeaders);

        String json = OBJECT_MAPPER.writeValueAsString(
                new LoginResponseDto(secretTokenWithUserId)
        );
        exchange.sendResponseHeaders(status, json.length());

        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(json.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private static void setToken(String secretTokenWithUserId, Headers respHeaders) {
        String value = "token=" + secretTokenWithUserId;
        respHeaders.add("Set-Cookie", value);
        respHeaders.put("Authorization", asList(secretTokenWithUserId));
    }


}
