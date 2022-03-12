package io.github.mehdaouiadam.devap4.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.*;
import java.util.List;
import javax.persistence.*;

@Entity
@Table
public class Departement {
    @Id
    private Long id;

    private String libelle;
    private String codepostal;

    @ManyToOne(optional = true)
    private Pays pays;

    @OneToMany(mappedBy = "departement")
    private List<Medecin> medecins;

    public Departement() {
    }

    public Departement(Long id, String libelle, String codepostal, Pays pays, List<Medecin> medecins) {
        this.id = id;
        this.libelle = libelle;
        this.codepostal = codepostal;
        this.pays = pays;
        this.medecins = medecins;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    @JsonBackReference
    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", codepostal='" + codepostal + '\'' +
                '}';
    }
}
