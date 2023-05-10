package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.CompanyBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyBankAccountRepository extends JpaRepository<CompanyBankAccount, Integer> {
}