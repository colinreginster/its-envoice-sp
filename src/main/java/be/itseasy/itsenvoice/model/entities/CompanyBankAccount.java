package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company_bank_accounts", schema = "main")
public class CompanyBankAccount {
    private Integer cbaId;
    private SimpleStringProperty iban = new SimpleStringProperty(this, "iban");
    private SimpleStringProperty accountNumber = new SimpleStringProperty(this, "accountNumber");
    private Bank bank;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cba_id", nullable = true)
    public Integer getCbaId() {
        return cbaId;
    }

    public void setCbaId(Integer cbaId) {
        this.cbaId = cbaId;
    }

     @Basic
    @Column(name = "iban", nullable = false, length = -1)
    public String getIban() {
        return iban.get();
    }

    public void setIban(String iban) {
        this.iban.set(iban);
    }

    @Basic
    @Column(name = "account_number", nullable = false, length = -1)
    public String getAccountNumber() {
        return accountNumber.get();
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber.set(accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyBankAccount that = (CompanyBankAccount) o;
        return  Objects.equals(cbaId, that.cbaId) && Objects.equals(iban.get(), that.iban.get()) && Objects.equals(accountNumber.get(), that.accountNumber.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cbaId,  iban.get(), accountNumber.get());
    }

    @ManyToOne
    @JoinColumn(name = "ban_id", referencedColumnName = "ban_id", nullable = false)
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    @Transient
    public SimpleStringProperty ibanProperty() {
        return iban;
    }
    @Transient
    public SimpleStringProperty accountNumberProperty() {
        return accountNumber;
    }
}
