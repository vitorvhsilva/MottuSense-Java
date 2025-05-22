package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Localizacao;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.dto.EnderecoViaCep;
import br.com.mottusense.users.http.ViaCepClient;
import br.com.mottusense.users.repository.LocalizacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocalizacaoService {

    private LocalizacaoRepository repository;
    private ViaCepClient cepClient;

    public Localizacao persistirLocalizacao(String cep, Filial filial) {

        EnderecoViaCep viaCep = cepClient.obterEnderecoDoUsuario(cep);

        Localizacao localizacao = new Localizacao(null, cep, viaCep.getLogradouro(), viaCep.getBairro(),
                viaCep.getEstado(), viaCep.getRegiao(), filial);

        return repository.save(localizacao);
    }
}
