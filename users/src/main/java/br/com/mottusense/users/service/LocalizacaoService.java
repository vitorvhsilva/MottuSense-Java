package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Localizacao;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalizacaoService {

    private LocalizacaoRepository repository;

    public Localizacao save(Localizacao localizacao) {
        return repository.save(localizacao);
    }


    public List<Localizacao> listarLocalizacoes(){
        return repository.findAll();
    }

    public Optional<Localizacao> buscarPorId(String id){
        return repository.findById(id);
    }
}
