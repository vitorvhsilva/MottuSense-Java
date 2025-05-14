package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private UsuarioRepository repository;

    public Usuario save(Usuario usuario, LocalDate dataNasc) {
        usuario.setDataNascimentoUsuario(dataNasc);
        usuario.setDataCriacaoUsuario(LocalDateTime.now());
        usuario.setLinkFotoUsuario("");
        return repository.save(usuario);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Optional<Usuario> findById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }



    // Perguntar pro vito sobre esse valueof
}
