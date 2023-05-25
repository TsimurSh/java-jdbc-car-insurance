package demo.integration.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.App;
import demo.config.Server;
import demo.model.Vehicle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import static demo.integration.TestUtils.getResponse;
import static demo.integration.TestUtils.sendGetRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VehicleControllerTest {

    public final ObjectMapper objectMapper = new ObjectMapper();

    public static final String COOKIE_TOKEN_NAME = "token";
    private static final long USER_ID = -1L;

    @BeforeAll
    public static void startServer() {
        App.main(null);
    }

    @AfterAll
    public static void stopServer() {
        Server.stopServer();
    }

    @Test
    void getRequestToApiShouldReturnVehicles() throws IOException {
        String cookieValue = COOKIE_TOKEN_NAME + "=" + USER_ID;
        HttpURLConnection con = sendGetRequest("/api/vehicle", cookieValue);

        String json = getResponse(con);

        List<Vehicle> vehicleListAll = objectMapper.readValue(json, new TypeReference<List<Vehicle>>() {
        });

        assertFalse(vehicleListAll.isEmpty());
        Vehicle first = vehicleListAll.get(0);
        assertNotNull(first);
        assertEquals(-3, first.getId());
        assertEquals("Focus", first.getModel());
        assertEquals("Ford", first.getBrand());
        assertEquals(77.00f, first.getInsurances().get(0).getPrice());
    }

}

