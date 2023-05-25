package demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehicle {
    private Long id;
    private String brand;
    private String model;
    private String login;
    private List<InsuranceOffer> offers = new ArrayList<>();

    public Vehicle() {
    }

    public Vehicle(Long id) {
        this.id = id;
    }

    public Vehicle(Long id, String brand, String model, String login) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<InsuranceOffer> getInsurances() {
        return offers;
    }

    public void setInsurances(List<InsuranceOffer> insuranceOffers) {
        this.offers = insuranceOffers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}

