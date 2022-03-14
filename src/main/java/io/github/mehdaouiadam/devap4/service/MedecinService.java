package io.github.mehdaouiadam.devap4.service;

import io.github.mehdaouiadam.devap4.entity.Departement;
import io.github.mehdaouiadam.devap4.entity.Medecin;
import io.github.mehdaouiadam.devap4.entity.Pays;
import io.github.mehdaouiadam.devap4.entity.Specialitecomplementaire;
import io.github.mehdaouiadam.devap4.repository.MedecinRepository;
import io.github.mehdaouiadam.devap4.repository.DepartementRepository;
import io.github.mehdaouiadam.devap4.repository.PaysRepository;
import io.github.mehdaouiadam.devap4.repository.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MedecinService {
    final MedecinRepository medecinRepository;
    final DepartementRepository departementRepository;
    final PaysRepository paysRepository;
    final SpecialiteRepository specialiteRepository;

    @Autowired
    public MedecinService(MedecinRepository medecinRepository, DepartementRepository departementRepository, PaysRepository paysRepository, SpecialiteRepository specialiteRepository) {
        this.medecinRepository = medecinRepository;
        this.departementRepository = departementRepository;
        this.paysRepository = paysRepository;
        this.specialiteRepository = specialiteRepository;
    }

    public List<Medecin> findAll() {
        return this.medecinRepository.findAllByOrderByIdAsc();
    }

    public Medecin findMedecinById(Long id) {
        return this.medecinRepository.findMedecinById(id);
    }

    public List<Medecin> findMedecinsByDepartement(Long dep) {
        Departement departement = this.departementRepository.findDepartementById(dep);
        return this.medecinRepository.findMedecinsByDepartementOrderByIdAsc(departement);
    }

    public List<Medecin> findMedecinsBySpecialitecomplementaire(Long spe) {
        Specialitecomplementaire specialitecomplementaire =
                this.specialiteRepository.findSpecialitecomplementaireById(spe);
        return this.medecinRepository.findMedecinsBySpecialitecomplementaire(specialitecomplementaire);
    }

    //en développement
    public List<Medecin> findMedecinsByNomOrPrenom(String nopr){

        List<Medecin> medecinsByNom = this.medecinRepository.findMedecinsByNom(nopr);
        List<Medecin> medecinsByPrenom = this.medecinRepository.findMedecinsByPrenom(nopr);

        List<Medecin> medecins = Stream.concat(medecinsByNom.stream(),
                medecinsByPrenom.stream()).collect(Collectors.toList());

        return medecins;
    }

    public List<Medecin> findMedecinsByPays(Pays pays){

        List<Medecin> medecins = new ArrayList<>();
        List<Medecin> medecinsTemp;
        List<Departement> departements = this.departementRepository.findDepartementsByPays(pays);

        for(Integer i=0; i<departements.size(); i++){

            Departement departement = departements.get(i);

            medecinsTemp = this.medecinRepository.findMedecinsByDepartementOrderByIdAsc(departement);

            if(i!=0) {
                medecins = Stream.concat(medecins.stream(), medecinsTemp.stream()).collect(Collectors.toList());
            }
            else{
                medecins = medecinsTemp;
            }
        }

        return medecins;
    }

    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public void deleteMedecin(Medecin medecin) {
        medecinRepository.delete(medecin);
    }
}