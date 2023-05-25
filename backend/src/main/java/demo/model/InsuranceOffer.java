package demo.model;

import java.util.Objects;

public class InsuranceOffer {
    private Long id;
    private String insurer;
    private Float price;
    private Long vehicleId;

    public InsuranceOffer() {
    }

    public InsuranceOffer(Long id, String insurer, Float price, Long vehicleId) {
        this.id = id;
        this.insurer = insurer;
        this.price = price;
        this.vehicleId = vehicleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceOffer insuranceOffer = (InsuranceOffer) o;
        return Objects.equals(id, insuranceOffer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", insurer='" + insurer + '\'' +
                ", price=" + price +
                '}';
    }
}

