package demo.integration.repository;

import demo.model.InsuranceOffer;
import demo.repository.InsuranceOfferRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class InsuranceOfferRepositoryTest {

    private final InsuranceOfferRepository insuranceOfferRepository = new InsuranceOfferRepository();

    private static final long[] VEHICLE_IDS = {-1, -3};

    @Test
    void findByVehicleIdTest() {
        List<InsuranceOffer> insuranceOfferListFound = insuranceOfferRepository.findAllByVehicleIds(VEHICLE_IDS);

        assertFalse(insuranceOfferListFound.isEmpty());
        assertEquals(-1, insuranceOfferListFound.get(0).getId());
        assertEquals("ATA", insuranceOfferListFound.get(0).getInsurer());
        assertEquals(77.00f, insuranceOfferListFound.get(0).getPrice());
        assertFalse(insuranceOfferListFound.isEmpty());
    }
}
