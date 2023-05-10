package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {
}