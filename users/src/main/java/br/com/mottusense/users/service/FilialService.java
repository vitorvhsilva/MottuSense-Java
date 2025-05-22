package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Localizacao;
import br.com.mottusense.users.dto.EnderecoViaCep;
import br.com.mottusense.users.http.ViaCepClient;
import br.com.mottusense.users.repository.FilialRepository;
import br.com.mottusense.users.repository.LocalizacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FilialService {

    private FilialRepository filialRepository;
    private LocalizacaoRepository localizacaoRepository;
    private ViaCepClient cepClient;

    public Filial salvar(Filial filial, String cep) {

        EnderecoViaCep viaCep = cepClient.obterEnderecoDoUsuario(cep);
        Filial entity = filialRepository.save(filial);

        Localizacao localizacao = new Localizacao(null, cep, viaCep.getLogradouro(), viaCep.getBairro(),
                viaCep.getEstado(), viaCep.getRegiao(), entity);

        localizacaoRepository.save(localizacao);

        return entity;
    }


    public List<Filial> listarFiliais() {
        return filialRepository.findAll();
    }

    public Optional<Filial> listarPorId(String id) {
        return filialRepository.findById(id);
    }

    public void deletarPorId(String id) {
        filialRepository.deleteById(id);
    }
}
