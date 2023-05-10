package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customers", schema = "main")
public class Customer {
    private Integer cusId;
    private SimpleStringProperty firstName = new SimpleStringProperty(this, "firstName");
    private SimpleStringProperty lastName = new SimpleStringProperty(this, "lastName");
    private SimpleStringProperty companyName = new SimpleStringProperty(this, "companyName");
    private SimpleStringProperty email = new SimpleStringProperty(this, "email");
    private SimpleStringProperty phone = new SimpleStringProperty(this, "phone");
    private SimpleStringProperty mobile = new SimpleStringProperty(this, "mobile");
    private SimpleStringProperty vatNumber = new SimpleStringProperty(this, "vatNumber");
    private SimpleIntegerProperty vatRegistered = new SimpleIntegerProperty(this, "vatRegistered");
    private Address address;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cus_id", nullable = true)
    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = -1)
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = -1)
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    @Basic
    @Column(name = "company_name", nullable = false, length = -1)
    public String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Basic
    @Column(name = "phone", nullable = false, length = -1)
    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Basic
    @Column(name = "mobile", nullable = false, length = -1)
    public String getMobile() {
        return mobile.get();
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }

    @Basic
    @Column(name = "vat_number", nullable = false, length = -1)
    public String getVatNumber() {
        return vatNumber.get();
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber.set(vatNumber);
    }

    @Basic
    @Column(name = "vat_registered", nullable = false)
    public int getVatRegistered() {
        return vatRegistered.get();
    }

    public void setVatRegistered(int vatRegistered) {
        this.vatRegistered.set(vatRegistered);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return vatRegistered.get() == customer.vatRegistered.get() && Objects.equals(cusId, customer.cusId) && Objects.equals(firstName.get(), customer.firstName.get()) && Objects.equals(lastName.get(), customer.lastName.get()) && Objects.equals(companyName.get(), customer.companyName.get()) && Objects.equals(email.get(), customer.email.get()) && Objects.equals(phone.get(), customer.phone.get()) && Objects.equals(mobile.get(), customer.mobile.get()) && Objects.equals(vatNumber.get(), customer.vatNumber.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cusId, firstName.get(), lastName.get(), companyName.get(), email.get(), phone.get(), mobile.get(), vatNumber.get(), vatRegistered.get());
    }

    @ManyToOne
    @JoinColumn(name = "add_id", referencedColumnName = "add_id", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    @Transient
    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }
    @Transient
    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }
    @Transient
    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }
    @Transient
    public SimpleStringProperty emailProperty() {
        return email;
    }
    @Transient
    public SimpleStringProperty phoneProperty() {
        return phone;
    }
    @Transient
    public SimpleStringProperty mobileProperty() {
        return mobile;
    }
    @Transient
    public SimpleStringProperty vatNumberProperty() {
        return vatNumber;
    }
    @Transient
    public SimpleIntegerProperty vatRegisteredProperty() {
        return vatRegistered;
    }
}
