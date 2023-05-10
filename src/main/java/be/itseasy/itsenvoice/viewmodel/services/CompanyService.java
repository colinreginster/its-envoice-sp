package be.itseasy.itsenvoice.viewmodel.services;

import be.itseasy.itsenvoice.model.entities.Company;
import be.itseasy.itsenvoice.model.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    public List<Company> listAllCompany() {
        return companyRepository.findAll();
    }

    public void saveCompany(Company companyInfo) {
        companyRepository.save(companyInfo);
    }

    public Company getCompany(Integer id) {
        Optional<Company> value=companyRepository.findById(id);
        return value.isPresent()?value.get():new Company();
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}