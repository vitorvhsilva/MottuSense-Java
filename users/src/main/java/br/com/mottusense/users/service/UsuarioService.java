package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.ConfiguracaoUsuario;
import br.com.mottusense.users.domain.Localizacao;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.dto.EnderecoViaCep;
import br.com.mottusense.users.http.ViaCepClient;
import br.com.mottusense.users.repository.ConfiguracaoUsuarioRepository;
import br.com.mottusense.users.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private ConfiguracaoUsuarioRepository configuracaoRepository;

    public Usuario salvar(Usuario usuario, LocalDate dataNasc) {
        usuario.setDataNascimentoUsuario(dataNasc);
        usuario.setDataCriacaoUsuario(LocalDateTime.now());
        usuario.setLinkFotoUsuario("");

        Usuario entity = usuarioRepository.save(usuario);
        configuracaoRepository.save(criarConfiguracaoUsuario(usuario));
        return entity;
    }

    private ConfiguracaoUsuario criarConfiguracaoUsuario(Usuario usuario) {
        return new ConfiguracaoUsuario(null, false, false,
                false, false, false, usuario );
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> listarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    public void deletarPorId(String id) {
        usuarioRepository.deleteById(id);
    }

}
