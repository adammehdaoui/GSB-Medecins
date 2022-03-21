package io.github.mehdaouiadam.devap4.service;

import io.github.mehdaouiadam.devap4.entity.Departement;
import io.github.mehdaouiadam.devap4.entity.Pays;
import io.github.mehdaouiadam.devap4.repository.DepartementRepository;
import io.github.mehdaouiadam.devap4.repository.MedecinRepository;
import io.github.mehdaouiadam.devap4.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaysService {
    final PaysRepository paysRepository;
    final DepartementRepository departementRepository;
    final MedecinRepository medecinRepository;

    @Autowired
    public PaysService(PaysRepository paysRepository, DepartementRepository departementRepository, MedecinRepository medecinRepository) {
        this.paysRepository = paysRepository;
        this.departementRepository = departementRepository;
        this.medecinRepository = medecinRepository;
    }

    public List<Pays> findAll(){
        return this.paysRepository.findAll();
    }

    public Pays findPaysById(Long id) { return this.paysRepository.findPaysById(id); }

    public Map<String, Long> findPaysCountMedecins(){

        this.departementRepository.findAll();

        List<Pays> allPays = this.findAll();

        Map<String, Long> data = new LinkedHashMap<String, Long>();

        for(Integer i=0; i<allPays.size(); i++){
            Pays paysObj = allPays.get(i);

            Long countMedecins = Long.valueOf(0);

            List<Departement> departementsPays = paysObj.getDepartements();

            for(Integer e=0; e<departementsPays.size(); e++){
                Long countMedecinsDep = this.medecinRepository.countByDepartement(departementsPays.get(e));

                countMedecins = countMedecins + countMedecinsDep;
            }

            data.put(paysObj.getNom(),countMedecins);
        }

        return data;
    }

}
