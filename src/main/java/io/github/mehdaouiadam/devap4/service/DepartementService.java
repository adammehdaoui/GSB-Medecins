package io.github.mehdaouiadam.devap4.service;

import io.github.mehdaouiadam.devap4.entity.Departement;
import io.github.mehdaouiadam.devap4.entity.Pays;
import io.github.mehdaouiadam.devap4.repository.DepartementRepository;
import io.github.mehdaouiadam.devap4.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartementService {
    final DepartementRepository departementRepository;
    final MedecinRepository medecinRepository;

    @Autowired
    public DepartementService(DepartementRepository departementRepository, MedecinRepository medecinRepository) {
        this.departementRepository = departementRepository;
        this.medecinRepository = medecinRepository;
    }

    public List<Departement> findAll(){return this.departementRepository.findAll();}

    public Map<String, Long> findDepartementCountMedecins(){

        List<Departement> allDepartements = this.findAll();

        Map<String, Long> data = new LinkedHashMap<String, Long>();

        for(Integer i=0; i<allDepartements.size(); i++){
            Departement departementObj = allDepartements.get(i);
            Pays paysObj = departementObj.getPays();

            Long countMedecins = this.medecinRepository.countByDepartement(departementObj);
            String departement = departementObj.getLibelle()+"\n"+'('+paysObj.getNom()+')';

            data.put(departement,countMedecins);
        }

        return data;
    }
}
