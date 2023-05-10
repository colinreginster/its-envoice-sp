package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "countries", schema = "main")
public class Country {
    private Integer couId;
    private SimpleStringProperty alpha3Code = new SimpleStringProperty(this, "alpha3Code");
    private SimpleStringProperty isoCode = new SimpleStringProperty(this, "isoCode");
    private SimpleStringProperty name = new SimpleStringProperty(this, "name");

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cou_id", nullable = true)
    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }

    @Basic
    @Column(name = "alpha3_code", nullable = false, length = 255)
    public String getAlpha3Code() {
        return alpha3Code.get();
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code.set(alpha3Code);
    }

    @Basic
    @Column(name = "iso_code", nullable = false, length = 255)
    public String getIsoCode() {
        return isoCode.get();
    }

    public void setIsoCode(String isoCode) {
        this.isoCode.set(isoCode);
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(couId, country.couId) && Objects.equals(alpha3Code.get(), country.alpha3Code.get()) && Objects.equals(isoCode.get(), country.isoCode.get()) && Objects.equals(name.get(), country.name.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(couId, alpha3Code.get(), isoCode.get(), name.get());
    }

    @Transient
    public SimpleStringProperty alpha3CodeProperty() {
        return alpha3Code;
    }
    @Transient
    public SimpleStringProperty isoCodeProperty() {
        return isoCode;
    }
    @Transient
    public SimpleStringProperty nameProperty() {
        return name;
    }
}
