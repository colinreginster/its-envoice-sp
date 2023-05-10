package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}