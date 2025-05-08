package br.com.mottusense.users.service;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private UsuarioRepository repository;

    // Fazer o save com base na aula 08/05 que não é necessário cadastrar e atualizar, pode fazer apenas um save que servirá para os dois

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

    public Usuario buscarPorId(int id){
        return  repository.findById(String.valueOf(id)).orElse(null); // Ver isso com o vito
    }
}
