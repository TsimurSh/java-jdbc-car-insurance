package demo.config;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
    private static final String LOCALHOST = "localhost";
    private static final int PORT = 8080;

    private static HttpServer httpServer;


    private Server() {
    }

    public static HttpServer getTcpServer() {
        if (httpServer != null) return httpServer;
        InetSocketAddress inetSocketAddress = new InetSocketAddress(LOCALHOST, PORT);
        try {
            System.out.println("Creating server on port " + PORT);

            httpServer = HttpServer.create(inetSocketAddress, 0);
            // Czy zaproponowane przez Ciebie rozwiązanie można zoptymalizować, aby pozwalało obsłużyć jednocześnie komunikaty od kilku klientów?
            httpServer.setExecutor(Executors.newCachedThreadPool()); // < -- odpowiedź
        } catch (IOException e) {
            throw new RuntimeException("Unable to start server on port " + PORT, e);
        }
        return httpServer;
    }

    public static void stopServer() {
        httpServer.stop(0);
    }
}
