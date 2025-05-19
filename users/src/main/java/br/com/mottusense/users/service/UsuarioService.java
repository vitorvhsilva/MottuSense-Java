package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario, LocalDate dataNasc) {
        usuario.setDataNascimentoUsuario(dataNasc);
        usuario.setDataCriacaoUsuario(LocalDateTime.now());
        usuario.setLinkFotoUsuario("");
        return repository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public Optional<Usuario> listarPorId(String id) {
        return repository.findById(id);
    }

    public void deletarPorId(String id) {
        repository.deleteById(id);
    }



}
