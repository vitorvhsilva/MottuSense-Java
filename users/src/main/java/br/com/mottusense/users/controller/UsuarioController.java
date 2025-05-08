package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public void cadastrarUsuario(@RequestBody Usuario usuario){
        usuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable int id){
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        usuario.setIdUsuario(String.valueOf(id));
        usuarioService.atualizarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable int id){
        usuarioService.deletarUsuario(id);
    }

}
