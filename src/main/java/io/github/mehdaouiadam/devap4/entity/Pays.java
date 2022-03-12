package io.github.mehdaouiadam.devap4.entity;

import javax.persistence.Entity;
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

    @OneToMany(mappedBy = "pays")
    List<Departement> departements;

    public Pays(){
    }

    public Pays(Long id, String nom, List<Departement> departements) {
        this.id = id;
        this.nom = nom;
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

    public List<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", departements=" + departements +
                '}';
    }
}
