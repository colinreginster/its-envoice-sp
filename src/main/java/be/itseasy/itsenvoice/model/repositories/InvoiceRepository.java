package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}