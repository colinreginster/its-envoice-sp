package be.itseasy.itsenvoice.viewmodel.services;

import be.itseasy.itsenvoice.model.entities.Country;
import be.itseasy.itsenvoice.model.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> listAllCountry() {
        return countryRepository.findAll();
    }

    public void saveCountry(Country country) {
        countryRepository.save(country);
    }

    public Country getCountry(Integer id) {
        return countryRepository.findById(id).get();
    }

    public void deleteCountry(Integer id) {
        countryRepository.deleteById(id);
    }


}