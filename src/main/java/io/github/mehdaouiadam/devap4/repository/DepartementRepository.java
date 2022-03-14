package io.github.mehdaouiadam.devap4.repository;

import io.github.mehdaouiadam.devap4.entity.Departement;
import io.github.mehdaouiadam.devap4.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

    List<Departement> findAll();

    List<Departement> findDepartementsByLibelleContaining(String lib);

    List<Departement> findDepartementsByPays(Pays pays);

    Departement findDepartementById(Long id);

}

