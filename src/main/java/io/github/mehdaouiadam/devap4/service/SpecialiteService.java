package io.github.mehdaouiadam.devap4.service;

import io.github.mehdaouiadam.devap4.entity.Specialitecomplementaire;
import io.github.mehdaouiadam.devap4.repository.MedecinRepository;
import io.github.mehdaouiadam.devap4.repository.SpecialiteRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpecialiteService {
    final SpecialiteRepository specialiteRepository;
    final MedecinRepository medecinRepository;

    public SpecialiteService(SpecialiteRepository specialiteRepository, MedecinRepository medecinRepository) {
        this.specialiteRepository = specialiteRepository;
        this.medecinRepository = medecinRepository;
    }

    public List<Specialitecomplementaire> findAll(){
        return this.specialiteRepository.findAll();
    }

    public Map<String, Long> findSpeCountMedecins() {

        List<Specialitecomplementaire> allSpecialites = this.findAll();

        Map<String, Long> data = new LinkedHashMap<String, Long>();

        for(Integer i=0; i<allSpecialites.size(); i++){
            Specialitecomplementaire specialiteObj = allSpecialites.get(i);

            Long countMedecins = this.medecinRepository.countBySpecialitecomplementaire(specialiteObj);
            String specialite = specialiteObj.getLibelle();

            data.put(specialite,countMedecins);
        }

        return data;
    }
}
