package io.github.mehdaouiadam.devap4.service;

import io.github.mehdaouiadam.devap4.entity.Departement;
import io.github.mehdaouiadam.devap4.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {
    final DepartementRepository departementRepository;

    @Autowired
    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public List<Departement> findAll(){return this.departementRepository.findAll();}
}
