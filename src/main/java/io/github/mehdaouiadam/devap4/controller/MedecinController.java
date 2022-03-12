package io.github.mehdaouiadam.devap4.controller;

import io.github.mehdaouiadam.devap4.entity.Departement;
import io.github.mehdaouiadam.devap4.entity.Medecin;
import io.github.mehdaouiadam.devap4.entity.Specialitecomplementaire;
import io.github.mehdaouiadam.devap4.service.DepartementService;
import io.github.mehdaouiadam.devap4.service.MedecinService;
import io.github.mehdaouiadam.devap4.service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/medecins")
public class MedecinController {
    final MedecinService medecinService;
    final DepartementService departementService;
    final SpecialiteService specialiteService;

    @Autowired
    public MedecinController(MedecinService medecinService, DepartementService departementService, SpecialiteService specialiteService) {
        this.medecinService = medecinService;
        this.departementService = departementService;
        this.specialiteService = specialiteService;
    }

    @GetMapping("")
    public String getAllMedecins(Model model) {
        List<Medecin> medecins = this.medecinService.findAll();
        model.addAttribute("medecinList", medecins);
        return "ListMedecins";
    }

    @GetMapping("/{id}")
    public String getMedecinById(Model model, @PathVariable("id") Long id) {
        Medecin medecin = this.medecinService.findMedecinById(id);
        model.addAttribute("medecin",medecin);
        return "detailMedecin";
    }

    @GetMapping("/createMedecin")
    public String createMedecin(Model model){

        Medecin medecinForm = new Medecin();

        List<Departement> departements = this.departementService.findAll();
        List<Specialitecomplementaire> specialites = this.specialiteService.findAll();

        model.addAttribute("medecinForm",medecinForm);
        model.addAttribute("departementList",departements);
        model.addAttribute("specialiteList",specialites);

        return "medecinForm";
    }

    @GetMapping("modifierMedecin/{id}")
    public void editMedecin(){
    }

    @GetMapping("deleteMedecin/{id}")
    public String deleteMedecin(Model model, @PathVariable("id") Long id){

        Medecin medecin = this.medecinService.findMedecinById(id);

        this.medecinService.deleteMedecin(medecin);

        List<Medecin> medecins = this.medecinService.findAll();
        model.addAttribute("medecinList",medecins);
        return "confirmSupMedecin";
    }

    /*@PostMapping("/nom")
    public String getMedecinsByNom(@Valid @ModelAttribute("medecinForm") Medecin medecinForm, BindingResult result,
                                   Model model){

        String nopr = medecinForm.getNom();
        List<Medecin> medecins = this.medecinService.findMedecinsByNomOrPrenom(nopr);
        model.addAttribute("medecinList",medecins);

        return "ListMedecins";
    }*/

    @PostMapping("")
    public String newMedecin(@Valid @ModelAttribute("medecinForm") Medecin medecinForm, BindingResult bindingResult)
            throws Exception {

        if(bindingResult.hasErrors()){
            return "medecinForm";
        }
        else {
            try{
                Medecin savedMedecin = medecinService.saveMedecin(medecinForm);
                return "confirmMedecin";
            }
            catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

    }
}
