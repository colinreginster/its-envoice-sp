package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "invoice_items", schema = "main")
public class InvoiceItem {
    private Integer iviId;
    private SimpleStringProperty comments = new SimpleStringProperty(this, "comments");
    private SimpleObjectProperty<BigDecimal> quantity = new SimpleObjectProperty<>(this, "quantity");
    private SimpleObjectProperty<BigDecimal> price = new SimpleObjectProperty<>(this, "price");
    private SimpleObjectProperty<BigDecimal> vatRate = new SimpleObjectProperty<>(this, "vatRate");
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "ite_id")
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ivi_id", nullable = true)
    public Integer getIviId() {
        return iviId;
    }

    public void setIviId(Integer iviId) {
        this.iviId = iviId;
    }

    @Basic
    @Column(name = "comments", nullable = true, length = -1)
    public String getComments() {
        return comments.get();
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public BigDecimal getQuantity() {
        return quantity.get();
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity.set(quantity);
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
        InvoiceItem that = (InvoiceItem) o;
        return quantity.get() == that.quantity.get() && Objects.equals(iviId, that.iviId) && Objects.equals(comments.get(), that.comments.get()) && Objects.equals(price.get(), that.price.get()) && Objects.equals(vatRate.get(), that.vatRate.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(iviId,  comments.get(), quantity.get(), price.get(), vatRate.get());
    }

    @ManyToOne
    @JoinColumn(name = "inv_id", referencedColumnName = "inv_id", nullable = false)
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @ManyToOne
    @JoinColumn(name = "ite_id", referencedColumnName = "ite_id", nullable = false)
    public Item getProduct() {
        return item;
    }

    public void setProduct(Item item) {
        this.item = item;
    }
    @Transient
    public SimpleStringProperty commentsProperty() {
        return comments;
    }
    @Transient
    public SimpleObjectProperty<BigDecimal> quantityProperty() {
        return quantity;
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
