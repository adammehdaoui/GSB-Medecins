package io.github.mehdaouiadam.devap4.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.lang.Nullable;

import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private String tel;

    @ManyToOne
    private Departement departement;

    @ManyToOne(optional = true)
    private Specialitecomplementaire specialitecomplementaire;

    public Medecin() {
    }

    public Medecin(Long id, String nom, String prenom, String adresse, String tel, Specialitecomplementaire specialitecomplementaire, Departement departement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.specialitecomplementaire = specialitecomplementaire;
        this.departement = departement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @JsonBackReference
    public Specialitecomplementaire getSpecialiteComplementaire() {
        return specialitecomplementaire;
    }

    public void setSpecialiteComplementaire(Specialitecomplementaire specialiteComplementaire) {
        this.specialitecomplementaire = specialiteComplementaire;
    }

    @JsonBackReference
    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", departement=" + departement +
                ", specialitecomplementaire=" + specialitecomplementaire +
                '}';
    }
}
