package io.github.mehdaouiadam.devap4.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class Pays {
    @Id
    private Long id;

    private String nom;

    private String code_iso;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pays")
    List<Departement> departements;

    public Pays(){
    }

    public Pays(Long id, String nom, String code_iso, List<Departement> departements) {
        this.id = id;
        this.nom = nom;
        this.code_iso = code_iso;
        this.departements = departements;
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

    public String getCode_iso() {
        return code_iso;
    }

    public void setCodeIso(String code_iso) {
        this.code_iso = code_iso;
    }

    public List<Departement> getDepartements() {
        return this.departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", codeIso='" + code_iso + '\'' +
                ", departements=" + departements +
                '}';
    }
}
