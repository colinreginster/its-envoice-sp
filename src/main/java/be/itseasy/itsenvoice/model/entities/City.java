package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cities", schema = "main")
public class City {
    private Integer citId;
    private SimpleStringProperty name = new SimpleStringProperty(this, "name");
    private SimpleStringProperty zipCode = new SimpleStringProperty(this, "zipCode");
    private Country country;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cit_id", nullable = true)
    public Integer getCitId() {
        return citId;
    }

    public void setCitId(Integer citId) {
        this.citId = citId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Basic
    @Column(name = "zip_code", nullable = false, length = 255)
    public String getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return  Objects.equals(citId, city.citId) && Objects.equals(name.get(), city.name.get()) && Objects.equals(zipCode.get(), city.zipCode.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(citId, name.get(), zipCode.get());
    }

    @ManyToOne
    @JoinColumn(name = "cou_id", referencedColumnName = "cou_id", nullable = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    @Transient
    public SimpleStringProperty nameProperty() {
        return name;
    }
    @Transient
    public SimpleStringProperty zipCodeProperty() {
        return zipCode;
    }
}
