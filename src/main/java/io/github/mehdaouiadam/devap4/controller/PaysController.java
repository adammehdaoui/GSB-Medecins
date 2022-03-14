package io.github.mehdaouiadam.devap4.controller;

import io.github.mehdaouiadam.devap4.entity.Medecin;
import io.github.mehdaouiadam.devap4.entity.Pays;
import io.github.mehdaouiadam.devap4.service.MedecinService;
import io.github.mehdaouiadam.devap4.service.PaysService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pays")
public class PaysController {
    final MedecinService medecinService;
    final PaysService paysService;

    public PaysController(MedecinService medecinService, PaysService paysService) {
        this.medecinService = medecinService;
        this.paysService = paysService;
    }

    @GetMapping("")
    public String getPays(Model model){
        List<Pays> pays = this.paysService.findAll();
        model.addAttribute("paysList",pays);
        return "listPays";
    }

    @GetMapping("/medecins/{idPays}")
    public String getMedecinsByPays(Model model, @PathVariable("idPays") Long idPays){

        Pays pays = this.paysService.findPaysById(idPays);
        List<Medecin> medecins = this.medecinService.findMedecinsByPays(pays);

        if(medecins.size() == 0){
            return "noMedecinPays";
        }
        else{
            model.addAttribute("medecinList",medecins);
            return "ListMedecins";
        }
    }
}
