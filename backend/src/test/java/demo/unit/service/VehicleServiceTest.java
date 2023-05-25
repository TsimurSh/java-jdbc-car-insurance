package demo.unit.service;

import demo.model.InsuranceOffer;
import demo.model.Vehicle;
import demo.repository.InsuranceOfferRepository;
import demo.repository.VehicleRepository;
import demo.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class VehicleServiceTest {
    private final VehicleRepository vehicleRepository = mock(VehicleRepository.class);
    private final InsuranceOfferRepository insuranceOfferRepository = mock(InsuranceOfferRepository.class);
    private final VehicleService vehicleService = new VehicleService(vehicleRepository, insuranceOfferRepository);

    private static List<Vehicle> vehiclesTest;
    private static List<InsuranceOffer> insurancesTest;

    @Test
    void findAllVehiclesByUserIdTest() {
        Vehicle vehicle = new Vehicle(123L, "GRUZ", "E22", "tut");
        insurancesTest = asList(new InsuranceOffer(22L, "PZU", 99.00f, vehicle.getId()));
        vehicle.setInsurances(insurancesTest);
        vehiclesTest = asList(vehicle);

        doReturn(vehiclesTest).when(vehicleRepository).findAllByUserId(1L);
        doReturn(insurancesTest).when(insuranceOfferRepository).findAllByVehicleIds(ArgumentMatchers.any());


        List<Vehicle> vehiclesTestResponse = vehicleService.findAllByUserId(1L);

        Vehicle actualResult = vehiclesTestResponse.get(0);

        assertNotNull(vehiclesTestResponse);
        assertEquals(123, actualResult.getId());
        assertEquals("GRUZ", actualResult.getBrand());
        assertEquals(insurancesTest, actualResult.getInsurances());
        assertEquals("tut", actualResult.getLogin());
        assertEquals("E22", actualResult.getModel());

        InsuranceOffer firstOffer = actualResult.getInsurances().get(0);
        assertEquals(99.00f, firstOffer.getPrice());
    }

    @Test
    void findAllVehiclesByUserIdNegativeTest() {
        insurancesTest = Collections.emptyList();
        vehiclesTest = Collections.emptyList();

        long[] emptyVehicleIdsTestArray = {};
        doReturn(vehiclesTest).when(vehicleRepository).findAllByUserId(anyLong());
        doReturn(insurancesTest).when(insuranceOfferRepository).findAllByVehicleIds(emptyVehicleIdsTestArray);

        List<Vehicle> vehiclesTestResponse = vehicleService.findAllByUserId(anyLong());
        assertNotNull(vehiclesTestResponse);
        assertEquals(0, vehiclesTest.size());
        assertTrue(vehiclesTest.isEmpty());
    }
}
