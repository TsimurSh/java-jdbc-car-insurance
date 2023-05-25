package demo.repository;

import demo.config.DBConnector;
import demo.model.InsuranceOffer;
import demo.model.exception.NoSuchOfferException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsuranceOfferRepository {
    private static final String QUERY_INSURANCE =
            "SELECT id, insurer, price, vehicle_id FROM insurance_offers WHERE vehicle_id IN(?)";

    public List<InsuranceOffer> findAllByVehicleIds(long[] vehicleIds) {
        List<InsuranceOffer> insuranceOffers = new ArrayList<>();

        String arrayIdsAsString = Arrays.toString(vehicleIds);
        String vehiclesIdsAsString = arrayIdsAsString.substring(1, arrayIdsAsString.length() - 1);
        String QUERY_INSURANCE_MODIFY = QUERY_INSURANCE.replace("?", vehiclesIdsAsString);

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY_INSURANCE_MODIFY)) {

            System.out.println("💫 Prepared Statement = " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String insurer = rs.getString("insurer");
                float price = rs.getFloat("price");

                long vehicleId = rs.getLong("vehicle_id");

                InsuranceOffer insuranceOffer = new InsuranceOffer(id, insurer, price, vehicleId);

                System.out.println("Found insurance: " + insuranceOffer);

                insuranceOffers.add(insuranceOffer);
            }
            rs.close();

            System.out.println("🔎Found vehicle's insurances: " + insuranceOffers.size());
            return insuranceOffers;
        } catch (SQLException e) {
            throw new NoSuchOfferException("Error searching all vehicle's insurances  in DB", e);
        }
    }
}

