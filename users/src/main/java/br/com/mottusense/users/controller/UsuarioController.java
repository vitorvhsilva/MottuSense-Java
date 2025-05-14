package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public void cadastrarUsuario(@RequestBody Usuario usuario){
        usuarioService.save(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscarUsuarioPorId(@PathVariable String id){
        return usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario){
        usuario.setIdUsuario(String.valueOf(id));
        usuarioService.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable String id){
        usuarioService.deleteById(id);
    }

}
