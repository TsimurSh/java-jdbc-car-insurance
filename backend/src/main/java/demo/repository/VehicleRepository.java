package demo.repository;


import demo.config.DBConnector;
import demo.model.Vehicle;
import demo.model.exception.NoSuchVehicleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements DBConnector {
    private static final String QUERY_VEHICLE = "SELECT  v.id AS vehicle_id, v.brand AS vehicle_brand, v.model AS vehicle_model" +
            " FROM users u" +
            " INNER JOIN vehicles v ON u.login = v.login" +
            " WHERE u.id = ? ORDER BY v.id";


    public List<Vehicle> findAllByUserId(long userId) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY_VEHICLE)) {

            ps.setLong(1, userId);
            System.out.println("💫 Prepared Statement = " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                long vehicleId = rs.getLong("vehicle_id");
                String brand = rs.getString("vehicle_brand");
                String model = rs.getString("vehicle_model");

                Vehicle vehicle = new Vehicle(vehicleId, brand, model, null);

                System.out.println("Found vehicle: " + vehicle);

                vehicles.add(vehicle);
            }
            rs.close();

            System.out.println("🔎Found user's vehicles: " + vehicles.size());
            return vehicles;
        } catch (SQLException e) {
            throw new NoSuchVehicleException("Error searching all user's vehicles in DB", e);
        }
    }
}
