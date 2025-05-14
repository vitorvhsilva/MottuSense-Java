package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.dto.UsuarioRequestDTO;
import br.com.mottusense.users.dto.UsuarioResponseDTO;
import br.com.mottusense.users.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@Valid @RequestBody UsuarioRequestDTO dto){
        Usuario usuario = mapper.map(dto, Usuario.class);
        LocalDate dataNasc = LocalDate.of(dto.getAno(), dto.getMes(), dto.getDia());
        Usuario usuarioSalvo = usuarioService.save(usuario, dataNasc); // Fazer os outros com base nesse
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
