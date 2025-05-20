package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialService {

    @Autowired
    private FilialRepository repository;


    public Filial salvar(Filial filial) {
        return repository.save(filial);
    }

    public List<Filial> listarFiliais() {
        return repository.findAll();
    }

    public Optional<Filial> listarPorId(String id) {
        return repository.findById(id);
    }

    public void deletarPorId(String id) {
        repository.deleteById(id);
    }
}
