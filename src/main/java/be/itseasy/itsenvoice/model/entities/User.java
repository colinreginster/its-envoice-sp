package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "main")
public class User {
    private Integer userId;
    private SimpleStringProperty username = new SimpleStringProperty(this, "username");
    private SimpleStringProperty passwordHash = new SimpleStringProperty(this, "passwordHash");
    private SimpleStringProperty firstName = new SimpleStringProperty(this, "firstName");
    private SimpleStringProperty lastName = new SimpleStringProperty(this, "lastName");
    private SimpleStringProperty email = new SimpleStringProperty(this, "email");
    private SimpleStringProperty phone = new SimpleStringProperty(this, "phone");

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = -1)
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    @Basic
    @Column(name = "password_hash", nullable = false, length = -1)
    public String getPasswordHash() {
        return passwordHash.get();
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash.set(passwordHash);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username.get(), user.username.get()) && Objects.equals(passwordHash.get(), user.passwordHash.get()) && Objects.equals(firstName.get(), user.firstName.get()) && Objects.equals(lastName.get(), user.lastName.get()) && Objects.equals(email.get(), user.email.get()) && Objects.equals(phone.get(), user.phone.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username.get(), passwordHash.get(), firstName.get(), lastName.get(), email.get(), phone.get());
    }
    @Transient
    public SimpleStringProperty usernameProperty() {
        return username;
    }
    @Transient
    public SimpleStringProperty passwordHashProperty() {
        return passwordHash;
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
    public SimpleStringProperty emailProperty() {
        return email;
    }
    @Transient
    public SimpleStringProperty phoneProperty() {
        return phone;
    }
}
