package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}