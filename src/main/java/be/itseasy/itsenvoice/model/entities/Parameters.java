package be.itseasy.itsenvoice.model.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class Parameters {
    private Integer parId;
    private SimpleIntegerProperty useAppToSendEmail = new SimpleIntegerProperty(this, "useAppToSendEmail");
    private SimpleStringProperty smtpServerHostname = new SimpleStringProperty(this, "smtpServerHostname");
    private SimpleObjectProperty<BigInteger> smtpServerPort = new SimpleObjectProperty<>(this, "smtpServerPort");
    private SimpleStringProperty smtpServerUsername = new SimpleStringProperty(this, "smtpServerUsername");
    private SimpleStringProperty smtpServerPassword = new SimpleStringProperty(this, "smtpServerPassword");
    private SimpleIntegerProperty smtpServerUseSsl = new SimpleIntegerProperty(this, "smtpServerUseSsl");
    private SimpleIntegerProperty smtpServerUseTls = new SimpleIntegerProperty(this, "smtpServerUseTls");
    private SimpleStringProperty smtpServerSendEmail = new SimpleStringProperty(this, "smtpServerSendEmail");
    private SimpleStringProperty smtpServerEmailTemplateLocation = new SimpleStringProperty(this, "smtpServerEmailTemplateLocation");
    private SimpleStringProperty invoicesFolderLocation = new SimpleStringProperty(this, "invoicesFolderLocation");
    private SimpleStringProperty pdfTemplateLocation = new SimpleStringProperty(this, "pdfTemplateLocation");
    private SimpleStringProperty invoiceNumberPattern = new SimpleStringProperty(this, "invoiceNumberPattern");

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "par_id", nullable = true)
    public Integer getParId() {
        return parId;
    }

    public void setParId(Integer parId) {
        this.parId = parId;
    }

    @Basic
    @Column(name = "use_app_to_send_email", nullable = false)
    public int getUseAppToSendEmail() {
        return useAppToSendEmail.get();
    }

    public void setUseAppToSendEmail(int useAppToSendEmail) {
        this.useAppToSendEmail.set(useAppToSendEmail);
    }

    @Basic
    @Column(name = "smtp_server_hostname", nullable = true, length = -1)
    public String getSmtpServerHostname() {
        return smtpServerHostname.get();
    }

    public void setSmtpServerHostname(String smtpServerHostname) {
        this.smtpServerHostname.set(smtpServerHostname);
    }

    @Basic
    @Column(name = "smtp_server_port", nullable = true, length = -1)
    public BigInteger getSmtpServerPort() {
        return smtpServerPort.get();
    }

    public void setSmtpServerPort(BigInteger smtpServerPort) {
        this.smtpServerPort.set(smtpServerPort);
    }

    @Basic
    @Column(name = "smtp_server_username", nullable = true, length = -1)
    public String getSmtpServerUsername() {
        return smtpServerUsername.get();
    }

    public void setSmtpServerUsername(String smtpServerUsername) {
        this.smtpServerUsername.set(smtpServerUsername);
    }

    @Basic
    @Column(name = "smtp_server_password", nullable = true, length = -1)
    public String getSmtpServerPassword() {
        return smtpServerPassword.get();
    }

    public void setSmtpServerPassword(String smtpServerPassword) {
        this.smtpServerPassword.set(smtpServerPassword);
    }

    @Basic
    @Column(name = "smtp_server_use_ssl", nullable = true)
    public Integer getSmtpServerUseSsl() {
        return smtpServerUseSsl.get();
    }

    public void setSmtpServerUseSsl(Integer smtpServerUseSsl) {
        this.smtpServerUseSsl.set(smtpServerUseSsl);
    }

    @Basic
    @Column(name = "smtp_server_use_tls", nullable = true)
    public Integer getSmtpServerUseTls() {
        return smtpServerUseTls.get();
    }

    public void setSmtpServerUseTls(Integer smtpServerUseTls) {
        this.smtpServerUseTls.set(smtpServerUseTls);
    }

    @Basic
    @Column(name = "smtp_server_send_email", nullable = true, length = -1)
    public String getSmtpServerSendEmail() {
        return smtpServerSendEmail.get();
    }

    public void setSmtpServerSendEmail(String smtpServerSendEmail) {
        this.smtpServerSendEmail.set(smtpServerSendEmail);
    }

    @Basic
    @Column(name = "smtp_server_email_template_location", nullable = true, length = -1)
    public String getSmtpServerEmailTemplateLocation() {
        return smtpServerEmailTemplateLocation.get();
    }

    public void setSmtpServerEmailTemplateLocation(String smtpServerEmailTemplateLocation) {
        this.smtpServerEmailTemplateLocation.set(smtpServerEmailTemplateLocation);
    }

    @Basic
    @Column(name = "invoices_folder_location", nullable = true, length = -1)
    public String getInvoicesFolderLocation() {
        return invoicesFolderLocation.get();
    }

    public void setInvoicesFolderLocation(String invoicesFolderLocation) {
        this.invoicesFolderLocation.set(invoicesFolderLocation);
    }

    @Basic
    @Column(name = "pdf_template_location", nullable = true, length = -1)
    public String getPdfTemplateLocation() {
        return pdfTemplateLocation.get();
    }

    public void setPdfTemplateLocation(String pdfTemplateLocation) {
        this.pdfTemplateLocation.set(pdfTemplateLocation);
    }

    @Basic
    @Column(name = "invoice_number_pattern", nullable = true, length = -1)
    public String getInvoiceNumberPattern() {
        return invoiceNumberPattern.get();
    }

    public void setInvoiceNumberPattern(String invoiceNumberPattern) {
        this.invoiceNumberPattern.set(invoiceNumberPattern);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return useAppToSendEmail.get() == that.useAppToSendEmail.get() && Objects.equals(parId, that.parId) && Objects.equals(smtpServerHostname.get(), that.smtpServerHostname.get()) && Objects.equals(smtpServerPort.get(), that.smtpServerPort.get()) && Objects.equals(smtpServerUsername.get(), that.smtpServerUsername.get()) && Objects.equals(smtpServerPassword.get(), that.smtpServerPassword.get()) && Objects.equals(smtpServerUseSsl.get(), that.smtpServerUseSsl.get()) && Objects.equals(smtpServerUseTls.get(), that.smtpServerUseTls.get()) && Objects.equals(smtpServerSendEmail.get(), that.smtpServerSendEmail.get()) && Objects.equals(smtpServerEmailTemplateLocation.get(), that.smtpServerEmailTemplateLocation.get()) && Objects.equals(invoicesFolderLocation.get(), that.invoicesFolderLocation.get()) && Objects.equals(pdfTemplateLocation.get(), that.pdfTemplateLocation.get()) && Objects.equals(invoiceNumberPattern.get(), that.invoiceNumberPattern.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(parId, useAppToSendEmail.get(), smtpServerHostname.get(), smtpServerPort.get(), smtpServerUsername.get(), smtpServerPassword.get(), smtpServerUseSsl.get(), smtpServerUseTls.get(), smtpServerSendEmail.get(), smtpServerEmailTemplateLocation.get(), invoicesFolderLocation.get(), pdfTemplateLocation.get(), invoiceNumberPattern.get());
    }
    @Transient
    public SimpleIntegerProperty useAppToSendEmailProperty() {
        return useAppToSendEmail;
    }
    @Transient
    public SimpleStringProperty smtpServerHostnameProperty() {
        return smtpServerHostname;
    }
    @Transient
    public SimpleObjectProperty<BigInteger> smtpServerPortProperty() {
        return smtpServerPort;
    }
    @Transient
    public SimpleStringProperty smtpServerUsernameProperty() {
        return smtpServerUsername;
    }
    @Transient
    public SimpleStringProperty smtpServerPasswordProperty() {
        return smtpServerPassword;
    }
    @Transient
    public SimpleIntegerProperty smtpServerUseSslProperty() {
        return smtpServerUseSsl;
    }
    @Transient
    public SimpleIntegerProperty smtpServerUseTlsProperty() {
        return smtpServerUseTls;
    }
    @Transient
    public SimpleStringProperty smtpServerSendEmailProperty() {
        return smtpServerSendEmail;
    }
    @Transient
    public SimpleStringProperty smtpServerEmailTemplateLocationProperty() {
        return smtpServerEmailTemplateLocation;
    }
    @Transient
    public SimpleStringProperty invoicesFolderLocationProperty() {
        return invoicesFolderLocation;
    }
    @Transient
    public SimpleStringProperty pdfTemplateLocationProperty() {
        return pdfTemplateLocation;
    }
    @Transient
    public SimpleStringProperty invoiceNumberPatternProperty() {
        return invoiceNumberPattern;
    }
}
