package io.github.mehdaouiadam.devap4.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class Specialitecomplementaire{
    @Id
    private Long id;

    private String libelle;

    @OneToMany(mappedBy = "specialitecomplementaire")
    private List<Medecin> medecins;

    public Specialitecomplementaire(){
    }

    public Specialitecomplementaire(Long id, String libelle, List<Medecin> medecins) {
        this.id = id;
        this.libelle = libelle;
        this.medecins = medecins;
    }

    public Long getId() {
        return id;
    }

    public void setIdSpe(Long idSpe) {
        id = idSpe;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

    @Override
    public String toString() {
        return "Specialitecomplementaire{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
