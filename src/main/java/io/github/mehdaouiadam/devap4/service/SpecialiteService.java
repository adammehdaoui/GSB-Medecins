package io.github.mehdaouiadam.devap4.service;

import io.github.mehdaouiadam.devap4.entity.Specialitecomplementaire;
import io.github.mehdaouiadam.devap4.repository.SpecialiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialiteService {
    final SpecialiteRepository specialiteRepository;

    public SpecialiteService(SpecialiteRepository specialiteRepository) {
        this.specialiteRepository = specialiteRepository;
    }

    public List<Specialitecomplementaire> findAll(){
        return this.specialiteRepository.findAll();
    }
}
