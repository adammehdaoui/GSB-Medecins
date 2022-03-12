package io.github.mehdaouiadam.devap4.repository;

import io.github.mehdaouiadam.devap4.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

    List<Pays> findAll();

    Pays findPaysById(Long id);

}

