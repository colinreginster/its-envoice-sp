package be.itseasy.itsenvoice.model.repositories;

import be.itseasy.itsenvoice.model.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findByNameLikeAndZipCodeLike(@Nullable String name, @Nullable String zipCode);

    List<City> findByZipCodeLikeAndNameContains( @Nullable String zipCode,@Nullable String name);

}