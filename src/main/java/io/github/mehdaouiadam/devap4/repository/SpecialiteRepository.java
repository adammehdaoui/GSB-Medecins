package io.github.mehdaouiadam.devap4.repository;

import io.github.mehdaouiadam.devap4.entity.Specialitecomplementaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialitecomplementaire, Long> {

    List<Specialitecomplementaire> findAll();

    Specialitecomplementaire findSpecialitecomplementaireById(Long id);

}

