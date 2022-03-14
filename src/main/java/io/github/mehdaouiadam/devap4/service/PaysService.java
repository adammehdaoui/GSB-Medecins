package io.github.mehdaouiadam.devap4.service;

import io.github.mehdaouiadam.devap4.entity.Pays;
import io.github.mehdaouiadam.devap4.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaysService {
    final PaysRepository paysRepository;

    @Autowired
    public PaysService(PaysRepository paysRepository) {
        this.paysRepository = paysRepository;
    }

    public List<Pays> findAll(){
        return this.paysRepository.findAll();
    }

    public Pays findPaysById(Long id) { return this.paysRepository.findPaysById(id); }

}
