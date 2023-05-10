package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}