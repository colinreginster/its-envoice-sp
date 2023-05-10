package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "credit_notes", schema = "main")
public class CreditNote {
    private Integer creId;
    private SimpleIntegerProperty creNumber = new SimpleIntegerProperty(this, "creNumber");
    private SimpleObjectProperty<Date> creDate = new SimpleObjectProperty<>(this, "creDate");
    private Collection<CreditNoteItem> creditNoteItems;
    private Invoice invoice;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cre_id", nullable = true)
    public Integer getCreId() {
        return creId;
    }

    public void setCreId(Integer creId) {
        this.creId = creId;
    }

    @Basic
    @Column(name = "cre_number", nullable = false)
    public int getCreNumber() {
        return creNumber.get();
    }

    public void setCreNumber(int creNumber) {
        this.creNumber.set(creNumber);
    }

    @Basic
    @Column(name = "cre_date", nullable = false)
    public Date getCreDate() {
        return creDate.get();
    }

    public void setCreDate(Date creDate) {
        this.creDate.set(creDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditNote that = (CreditNote) o;
        return creNumber.get() == that.creNumber.get() &&  Objects.equals(creId, that.creId) && Objects.equals(creDate.get(), that.creDate.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(creId, creNumber.get(), creDate.get());
    }

    @OneToMany(mappedBy = "creditNote")
    public Collection<CreditNoteItem> getCreditNoteItems() {
        return creditNoteItems;
    }

    public void setCreditNoteItems(Collection<CreditNoteItem> creditNoteItems) {
        this.creditNoteItems = creditNoteItems;
    }

    @ManyToOne
    @JoinColumn(name = "inv_id", referencedColumnName = "inv_id", nullable = false)
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    @Transient
    public SimpleIntegerProperty creNumberProperty() {
        return creNumber;
    }
    @Transient
    public SimpleObjectProperty<Date> creDateProperty() {
        return creDate;
    }
}
