package io.github.mehdaouiadam.devap4.controller;

import io.github.mehdaouiadam.devap4.entity.Medecin;
import io.github.mehdaouiadam.devap4.entity.Specialitecomplementaire;
import io.github.mehdaouiadam.devap4.service.MedecinService;
import io.github.mehdaouiadam.devap4.service.SpecialiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/specialites")
public class SpecialiteController {
    final MedecinService medecinService;
    final SpecialiteService specialiteService;

    public SpecialiteController(MedecinService medecinService, SpecialiteService specialiteService) {
        this.medecinService = medecinService;
        this.specialiteService = specialiteService;
    }

    @GetMapping("")
    public String getPays(Model model){
        List<Specialitecomplementaire> specialites = this.specialiteService.findAll();
        model.addAttribute("specialiteList",specialites);
        return "ListSpecialites";
    }
    
    @GetMapping("/medecins/{spe}")
    public String getMedecins(Model model, @PathVariable("spe") Long spe) {
        List<Medecin> medecins = this.medecinService.findMedecinsBySpecialitecomplementaire(spe);

        if(medecins.size() == 0){
            return "noMedecinSpe";
        }
        else if(medecins.size()>0 & medecins.size()<5){
            model.addAttribute("medecinList",medecins);
            return "listMedecinsWithFooter";
        }
        else{
            model.addAttribute("medecinList",medecins);
            return "ListMedecins";
        }
    }
}
