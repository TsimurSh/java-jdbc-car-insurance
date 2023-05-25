package demo.integration.repository;

import demo.model.Vehicle;
import demo.repository.VehicleRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class VehicleRepositoryTest {

    private final VehicleRepository vehicleRepository = new VehicleRepository();

    public static final int USER_ID = -2;

    @Test
    void findByUserIdTest() {
        List<Vehicle> listTest = vehicleRepository.findAllByUserId(USER_ID);

        assertFalse(listTest.isEmpty());

        Vehicle vehicleTest = listTest.get(0);
        assertEquals(USER_ID, vehicleTest.getId());
        assertEquals("540d", vehicleTest.getModel());
        assertEquals("BMW", vehicleTest.getBrand());

    }
}
