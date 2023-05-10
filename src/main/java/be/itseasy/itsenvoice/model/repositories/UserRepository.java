package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}