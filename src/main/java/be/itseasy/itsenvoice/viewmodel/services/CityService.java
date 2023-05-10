package be.itseasy.itsenvoice.viewmodel.services;

import be.itseasy.itsenvoice.model.entities.City;
import be.itseasy.itsenvoice.model.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> listAllCity() {
        return cityRepository.findAll();
    }

    public void saveCity(City city) {
        cityRepository.save(city);
    }

    public City getCity(Integer id) {
        return cityRepository.findById(id).get();
    }

    public void deleteCity(Integer id) {
        cityRepository.deleteById(id);
    }

    public City findCity(String zipCode, String cityName) {
        return cityRepository.findByNameLikeAndZipCodeLike(cityName,zipCode);
    }
    public List<City> findCities(String zipCode, String cityName) {
        return cityRepository.findByZipCodeLikeAndNameContains(zipCode,cityName);
    }
}