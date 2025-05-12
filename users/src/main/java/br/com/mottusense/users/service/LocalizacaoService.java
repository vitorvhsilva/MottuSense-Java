package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Localizacao;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.repository.LocalizacaoRepository;

import java.util.List;
import java.util.Optional;

public class LocalizacaoService {

    private LocalizacaoRepository repository;

    public Localizacao save(Localizacao localizacao) {
        return repository.save(localizacao);
    }


    public List<Localizacao> listarLocalizacoes(){
        return repository.findAll();
    }

    public Optional<Localizacao> buscarPorId(int id){
        return repository.findById(String.valueOf(id));
    }
}
