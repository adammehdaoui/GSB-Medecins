package io.github.mehdaouiadam.devap4.controller;

import io.github.mehdaouiadam.devap4.entity.Medecin;
import io.github.mehdaouiadam.devap4.service.MedecinService;
import io.github.mehdaouiadam.devap4.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departements")
public class DepartementController {
    final MedecinService medecinService;
    final DepartementService departementService;

    @Autowired
    public DepartementController(MedecinService medecinService, DepartementService departementService) {
        this.medecinService = medecinService;
        this.departementService = departementService;
    }

    @GetMapping("")
    public String getDepartements(Model model) {
        model.addAttribute("departementList",this.departementService.findAll());
        return "ListDepartements";
    }

    @GetMapping("/medecins/{dep}")
    public String getMedecins(Model model, @PathVariable("dep") Long dep) {
        List<Medecin> medecins = this.medecinService.findMedecinsByDepartement(dep);

        if(medecins.size() == 0){
            return "noMedecinDepartement";
        }
        else {
            model.addAttribute("medecinList", medecins);
            return "ListMedecins";
        }
    }
}
