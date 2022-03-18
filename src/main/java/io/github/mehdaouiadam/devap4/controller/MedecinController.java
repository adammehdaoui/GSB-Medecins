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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/nom")
    public String getMedecinsByNom(Model model, @RequestParam(defaultValue="") String nopr){

        List<Medecin> medecins = this.medecinService.findMedecinsByNomOrPrenom(nopr);

        if(medecins.size() == 0){
            return "noMedecinNom";
        }
        else if (medecins.size()>0 & medecins.size()<5){
            model.addAttribute("medecinList",medecins);
            return "listMedecinsWithFooter";
        }
        else{
            model.addAttribute("medecinList",medecins);
            return "ListMedecins";
        }
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

    @GetMapping("editMedecin/{id}")
    public String editMedecin(Model model, @PathVariable("id") Long id){

        Medecin medecin = this.medecinService.findMedecinById(id);

        List<Departement> departements = this.departementService.findAll();
        List<Specialitecomplementaire> specialites = this.specialiteService.findAll();

        model.addAttribute("medecin",medecin);
        model.addAttribute("departementList",departements);
        model.addAttribute("specialiteList",specialites);

        return "editMedecin";
    }

    @GetMapping("deleteMedecin/{id}")
    public String deleteMedecin(Model model, @PathVariable("id") Long id){

        Medecin medecin = this.medecinService.findMedecinById(id);

        this.medecinService.deleteMedecin(medecin);

        List<Medecin> medecins = this.medecinService.findAll();
        model.addAttribute("medecinList",medecins);
        return "confirmSupMedecin";
    }

    @PostMapping("editMedecin/{id}")
    public String editMedecin(@Valid @ModelAttribute("medecin") Medecin medecin, BindingResult bindingResult, @PathVariable("id") Long id, Model model)
                    throws Exception{

        List<Departement> departements = this.departementService.findAll();
        List<Specialitecomplementaire> specialites = this.specialiteService.findAll();

        if(bindingResult.hasErrors()){
            model.addAttribute("departementList",departements);
            model.addAttribute("specialiteList",specialites);

            return "editMedecin";
        }
        else {
            try{
                Medecin savedMedecin = medecinService.saveMedecin(medecin);

                model.addAttribute("departementList",departements);
                model.addAttribute("specialiteList",specialites);

                return "confirmEditMedecin";
            }
            catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("")
    public String newMedecin(@Valid @ModelAttribute("medecinForm") Medecin medecinForm, BindingResult bindingResult, Model model)
            throws Exception {

        if(bindingResult.hasErrors()){
            return "medecinForm";
        }
        else {
            try{
                Medecin savedMedecin = medecinService.saveMedecin(medecinForm);

                model.addAttribute("medecin",savedMedecin);

                return "confirmMedecin";
            }
            catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
