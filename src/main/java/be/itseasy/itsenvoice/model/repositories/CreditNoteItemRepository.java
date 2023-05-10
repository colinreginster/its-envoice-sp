package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.CreditNoteItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditNoteItemRepository extends JpaRepository<CreditNoteItem, Integer> {
}