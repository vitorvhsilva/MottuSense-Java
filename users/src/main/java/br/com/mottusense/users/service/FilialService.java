package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.repository.FilialRepository;

import java.util.List;

public class FilialService {
    private FilialRepository repository;

    public void cadastrarFilial(Filial filial) {
        repository.save(filial);
    }

    public void atualizarFilial(Filial filial){
        repository.save(filial);
    }

    public List<Filial> listarFiliais(){
        return repository.findAll();
    }

    public void deletarFilial(int id){
        repository.deleteAllById(id);
    }
}
