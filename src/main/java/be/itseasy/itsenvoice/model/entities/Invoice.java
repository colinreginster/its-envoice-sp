package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "invoices", schema = "main")
public class Invoice {
    private Integer invId;
    private SimpleIntegerProperty invNumber = new SimpleIntegerProperty(this, "invNumber");
    private SimpleObjectProperty<Date> invDate = new SimpleObjectProperty<>(this, "invDate");
    private SimpleIntegerProperty paymentTerm = new SimpleIntegerProperty(this, "paymentTerm");
    private SimpleStringProperty status = new SimpleStringProperty(this, "status");
    private Collection<InvoiceItem> invoiceItems;
    private Customer customer;
    private Company company;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inv_id", nullable = true)
    public Integer getInvId() {
        return invId;
    }

    public void setInvId(Integer invId) {
        this.invId = invId;
    }

    @Basic
    @Column(name = "inv_number", nullable = false)
    public int getInvNumber() {
        return invNumber.get();
    }

    public void setInvNumber(int invNumber) {
        this.invNumber.set(invNumber);
    }

    @Basic
    @Column(name = "inv_date", nullable = false)
    public Date getInvDate() {
        return invDate.get();
    }

    public void setInvDate(Date invDate) {
        this.invDate.set(invDate);
    }

    @Basic
    @Column(name = "payment_term", nullable = false)
    public int getPaymentTerm() {
        return paymentTerm.get();
    }

    public void setPaymentTerm(int paymentTerm) {
        this.paymentTerm.set(paymentTerm);
    }

    @Basic
    @Column(name = "status", nullable = false, length = -1)
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invNumber.get() == invoice.invNumber.get() && paymentTerm.get() == invoice.paymentTerm.get() && Objects.equals(invId, invoice.invId) && Objects.equals(invDate.get(), invoice.invDate.get()) && Objects.equals(status.get(), invoice.status.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(invId, invNumber.get(), invDate.get(), paymentTerm.get(), status.get());
    }

    @OneToMany(mappedBy = "invoice")
    public Collection<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(Collection<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    @ManyToOne
    @JoinColumn(name = "cus_id", referencedColumnName = "cus_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "com_id", referencedColumnName = "com_id", nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    @Transient
    public SimpleIntegerProperty invNumberProperty() {
        return invNumber;
    }
    @Transient
    public SimpleObjectProperty<Date> invDateProperty() {
        return invDate;
    }
    @Transient
    public SimpleIntegerProperty paymentTermProperty() {
        return paymentTerm;
    }
    @Transient
    public SimpleStringProperty statusProperty() {
        return status;
    }
}
