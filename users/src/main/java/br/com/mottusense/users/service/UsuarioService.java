package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private UsuarioRepository repository;

    public void cadastrarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    public void atualizarUsuario(Usuario usuario){
        repository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return repository.findAll();
    }

    public void deletarUsuario(int id){
        repository.deleteAllById(id);
    }
}
