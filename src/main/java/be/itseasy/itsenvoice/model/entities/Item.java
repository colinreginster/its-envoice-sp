package be.itseasy.itsenvoice.model.entities;

import be.itseasy.itsenvoice.model.utils.JavaFXEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "items", schema = "main")
public class Item extends JavaFXEntity {
    private Integer iteid;
    private SimpleStringProperty name = new SimpleStringProperty(this, "name");
    private SimpleStringProperty description = new SimpleStringProperty(this, "description");
    private SimpleObjectProperty<BigDecimal> price = new SimpleObjectProperty<>(this, "price", BigDecimal.ZERO);
    private SimpleObjectProperty<BigDecimal> vatRate = new SimpleObjectProperty<>(this, "vatRate", BigDecimal.ZERO);

    public Item() {
        addProperty(name);
        addProperty(description);
        addProperty(price);
        addProperty(vatRate);
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ite_id", nullable = true)
    public Integer getIteid() {
        return iteid;
    }

    public void setIteid(Integer iteid) {
        this.iteid = iteid;
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
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public BigDecimal getPrice() {
        return price.get();
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
    }

    @Basic
    @Column(name = "vat_rate", nullable = false, precision = 0)
    public BigDecimal getVatRate() {
        return vatRate.get();
    }

    public void setVatRate(BigDecimal vatRate) {
        this.vatRate.set(vatRate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(iteid, item.iteid) && Objects.equals(name.get(), item.name.get()) && Objects.equals(description.get(), item.description.get()) && Objects.equals(price.get(), item.price.get()) && Objects.equals(vatRate.get(), item.vatRate.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(iteid, name.get(), description.get(), price.get(), vatRate.get());
    }
    @Transient
    public SimpleStringProperty nameProperty() {
        return name;
    }
    @Transient
    public SimpleStringProperty descriptionProperty() {
        return description;
    }
    @Transient
    public SimpleObjectProperty<BigDecimal> priceProperty() {
        return price;
    }
    @Transient
    public SimpleObjectProperty<BigDecimal> vatRateProperty() {
        return vatRate;
    }
}
