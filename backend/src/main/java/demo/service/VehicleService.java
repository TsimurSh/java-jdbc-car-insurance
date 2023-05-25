package demo.service;

import demo.model.InsuranceOffer;
import demo.model.Vehicle;
import demo.repository.InsuranceOfferRepository;
import demo.repository.VehicleRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final InsuranceOfferRepository insuranceOfferRepository;

    public VehicleService(VehicleRepository vehicleRepository, InsuranceOfferRepository insuranceOfferRepository) {
        this.vehicleRepository = vehicleRepository;
        this.insuranceOfferRepository = insuranceOfferRepository;
    }


    public List<Vehicle> findAllByUserId(Long userId) {
        List<Vehicle> vehicleListWithInsurances = vehicleRepository.findAllByUserId(userId);
        System.out.println("⚡️ Searching vehicles for user with id = " + userId);
        long[] vehicleID = vehicleListWithInsurances.stream().mapToLong(Vehicle::getId).toArray();
        List<InsuranceOffer> insuranceOffers = insuranceOfferRepository.findAllByVehicleIds(vehicleID);

        vehicleListWithInsurances
                .forEach(vehicle -> {
                    List<InsuranceOffer> insuranceOfferListToCurrentVehicle = getInsurances(insuranceOffers, vehicle);
                    vehicle.setInsurances(insuranceOfferListToCurrentVehicle);
                });

        System.out.println("📊 Found Vehicles & Insurances: " + vehicleListWithInsurances.size());
        return vehicleListWithInsurances;
    }

    private static List<InsuranceOffer> getInsurances(List<InsuranceOffer> insuranceOffers, Vehicle vehicle) {
        return insuranceOffers.stream()
                .filter(insurance -> Objects.equals(insurance.getVehicleId(), vehicle.getId()))
                .collect(Collectors.toList());
    }
}
