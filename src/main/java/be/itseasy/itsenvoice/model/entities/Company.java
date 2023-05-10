package be.itseasy.itsenvoice.model.entities;

import be.itseasy.itsenvoice.model.utils.JavaFXEntity;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import jakarta.persistence.*;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "companies", schema = "main")
public class Company extends JavaFXEntity {
    private Integer comId;
    private SimpleStringProperty name = new SimpleStringProperty(this, "name");
    private SimpleStringProperty slogan = new SimpleStringProperty(this, "slogan");
    private byte[] logo;
    private SimpleObjectProperty<Image> logoProperty = new SimpleObjectProperty<>();
    private SimpleStringProperty email = new SimpleStringProperty(this, "email");
    private SimpleStringProperty phone = new SimpleStringProperty(this, "phone");
    private SimpleStringProperty mobile = new SimpleStringProperty(this, "mobile");
    private SimpleStringProperty vatNumber = new SimpleStringProperty(this, "vatNumber");
    private SimpleBooleanProperty vatRegistered = new SimpleBooleanProperty(this, "vatRegistered");
    private Address address;
    private Collection<CompanyBankAccount> companyBankAccounts;

    public Company() {
        addProperty(name);
        addProperty(slogan);
        addProperty(logoProperty);
        addProperty(email);
        addProperty(phone);
        addProperty(mobile);
        addProperty(vatRegistered);
        addProperty(vatNumber);
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "com_id", nullable = true)
    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
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
    @Column(name = "slogan", nullable = false, length = -1)
    public String getSlogan() {
        return slogan.get();
    }

    public void setSlogan(String slogan) {
        this.slogan.set(slogan);
    }

    @Basic
    @Column(name = "logo", nullable = true)
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {

        this.logo = logo;
        if (logo != null) {
            this.logoProperty.set(new Image(new ByteArrayInputStream(logo)));
        }
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
    @Column(name = "vat_number", nullable = true, length = -1)
    public String getVatNumber() {
        return vatNumber.get();
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber.set(vatNumber);
    }

    @Basic
    @Column(name = "vat_registered", nullable = true)
    public boolean getVatRegistered() {
        return vatRegistered.get();
    }

    public void setVatRegistered(boolean vatRegistered) {
        this.vatRegistered.set(vatRegistered);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return vatRegistered.get() == company.vatRegistered.get() && Objects.equals(comId, company.comId) && Objects.equals(name.get(), company.name.get()) && Objects.equals(slogan.get(), company.slogan.get()) && Arrays.equals(logo, company.logo) && Objects.equals(email.get(), company.email.get()) && Objects.equals(phone.get(), company.phone.get()) && Objects.equals(mobile.get(), company.mobile.get()) && Objects.equals(vatNumber.get(), company.vatNumber.get());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(comId, name.get(), slogan.get(), email.get(), phone.get(), mobile.get(), vatNumber.get(), vatRegistered.get());
        result = 31 * result + Arrays.hashCode(logo);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "add_id", referencedColumnName = "add_id", nullable = false)
    public Address getAddress() {
        return address!=null?address:new Address();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Transient
    public SimpleStringProperty nameProperty() {
        return name;
    }

    @Transient
    public SimpleStringProperty sloganProperty() {
        return slogan;
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
    public SimpleBooleanProperty vatRegisteredProperty() {
        return vatRegistered;
    }

    @Transient
    public SimpleObjectProperty<Image> logoProperty() {
        return logoProperty;
    }
}
