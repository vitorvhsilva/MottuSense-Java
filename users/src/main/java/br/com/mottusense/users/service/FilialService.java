package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.repository.FilialRepository;

import java.util.List;

public class FilialService {
    private FilialRepository repository;

    // Fazer o save com base na aula 08/05 que não é necessário cadastrar e atualizar, pode fazer apenas um save que servirá para os dois

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

    public Filial buscarPorId(int id){
        return  repository.findById(String.valueOf(id)).orElse(null); // Ver isso com o vito
    }
}
