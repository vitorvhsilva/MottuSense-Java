package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.repository.FilialRepository;

import java.util.List;
import java.util.Optional;

public class FilialService {

    private FilialRepository repository;

    public Filial save(Filial filial) {
        return repository.save(filial);
    }

    public List<Filial> findAll() {
        return repository.findAll();
    }

    public Optional<Filial> findById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }


    // Perguntar pro vito sobre esse valueof
}
