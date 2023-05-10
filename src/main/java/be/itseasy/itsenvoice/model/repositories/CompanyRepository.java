package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}