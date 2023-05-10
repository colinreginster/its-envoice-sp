package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametersRepository extends JpaRepository<Parameters, Integer> {
}