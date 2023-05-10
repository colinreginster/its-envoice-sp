package be.itseasy.itsenvoice.model.entities;


import be.itseasy.itsenvoice.model.utils.JavaFXEntity;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses", schema = "main")
public class Address extends JavaFXEntity {
    private Integer addId;
    private SimpleStringProperty streetAddress = new SimpleStringProperty(this, "streetAddress");
    private SimpleIntegerProperty streetNumber = new SimpleIntegerProperty(this, "streetNumber");
    private SimpleStringProperty postBox = new SimpleStringProperty(this, "postBox");

    private City city;

    public Address(){
        addProperty(streetAddress);
        addProperty(streetNumber);
        addProperty(postBox);
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "add_id", nullable = true)
    public Integer getAddId() {
        return addId;
    }

    public void setAddId(Integer addId) {
        this.addId = addId;
    }

    @Basic
    @Column(name = "street_address", nullable = false, length = -1)
    public String getStreetAddress() {
        return streetAddress.get();
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress.set(streetAddress);
    }


    @Basic
    @Column(name = "street_number", nullable = false)
    public Integer getStreetNumber() {
        return streetNumber.get();
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber.set(streetNumber);
    }

    @Basic
    @Column(name = "post_box", nullable = true, length = -1)
    public String getPostBox() {
        return postBox.get();
    }

    public void setPostBox(String postBox) {
        this.postBox.set(postBox);
    }

    @ManyToOne
    @JoinColumn(name = "cit_id", referencedColumnName = "cit_id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return  streetNumber.get() == address.streetNumber.get() && Objects.equals(addId, address.addId) && Objects.equals(streetAddress.get(), address.streetAddress.get()) && Objects.equals(postBox.get(), address.postBox.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(addId, streetAddress.get(), streetNumber.get(), postBox.get());
    }

    @Transient
    public SimpleStringProperty streetAddressProperty() {
        return streetAddress;
    }
    @Transient
    public SimpleIntegerProperty streetNumberProperty() {
        return streetNumber;
    }
    @Transient
    public SimpleStringProperty postBoxProperty() {
        return postBox;
    }
}
