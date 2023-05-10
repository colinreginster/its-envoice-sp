package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "credit_note_items", schema = "main")
public class CreditNoteItem {
    private Integer criId;
    private SimpleStringProperty comment = new SimpleStringProperty(this, "comment");
    private SimpleObjectProperty<BigDecimal> quantity = new SimpleObjectProperty<>(this, "quantity");
    private SimpleObjectProperty<BigDecimal> price = new SimpleObjectProperty<>(this, "price");
    private SimpleObjectProperty<BigDecimal> vatRate = new SimpleObjectProperty<>(this, "vatRate");
    private CreditNote creditNote;
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
    @Column(name = "cri_id", nullable = true)
    public Integer getCriId() {
        return criId;
    }

    public void setCriId(Integer criId) {
        this.criId = criId;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
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
        CreditNoteItem that = (CreditNoteItem) o;
        return quantity.get() == that.quantity.get() && Objects.equals(criId, that.criId) && Objects.equals(comment.get(), that.comment.get()) && Objects.equals(price.get(), that.price.get()) && Objects.equals(vatRate.get(), that.vatRate.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(criId, comment.get(), quantity.get(), price.get(), vatRate.get());
    }

    @ManyToOne
    @JoinColumn(name = "cre_id", referencedColumnName = "cre_id", nullable = false)
    public CreditNote getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(CreditNote creditNote) {
        this.creditNote = creditNote;
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
    public SimpleStringProperty commentProperty() {
        return comment;
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
