package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "banks", schema = "main")
public class Bank {
    private Integer banId;
    private SimpleStringProperty name = new SimpleStringProperty(this, "name");
    private SimpleStringProperty swift = new SimpleStringProperty(this, "swift");

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ban_id", nullable = true)
    public Integer getBanId() {
        return banId;
    }

    public void setBanId(Integer banId) {
        this.banId = banId;
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
    @Column(name = "swift", nullable = false, length = -1)
    public String getSwift() {
        return swift.get();
    }

    public void setSwift(String swift) {
        this.swift.set(swift);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(banId, bank.banId) && Objects.equals(name.get(), bank.name.get()) && Objects.equals(swift.get(), bank.swift.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(banId, name.get(), swift.get());
    }
    @Transient
    public SimpleStringProperty nameProperty() {
        return name;
    }
    @Transient
    public SimpleStringProperty swiftProperty() {
        return swift;
    }
}
