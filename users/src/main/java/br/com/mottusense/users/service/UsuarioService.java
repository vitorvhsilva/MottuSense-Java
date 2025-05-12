package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private UsuarioRepository repository;

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return repository.findById(String.valueOf(id));
    }

    public void deleteById(Long id) {
        repository.deleteById(String.valueOf(id));
    }


    // Perguntar pro vito sobre esse valueof
}
