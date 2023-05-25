package demo;

import demo.config.Server;
import demo.controller.LoginController;
import demo.controller.VehicleController;

public class App {

    public static void main(String[] args) {
        Server.getTcpServer()
                .createContext("/api/vehicle", new VehicleController());

        Server.getTcpServer()
                .createContext("/login", new LoginController());

        Server.getTcpServer().start();

        System.out.println("👌🏻Server just has started...");
    }


}
