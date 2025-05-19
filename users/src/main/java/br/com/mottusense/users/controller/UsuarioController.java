package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.dto.UsuarioRequestDTO;
import br.com.mottusense.users.dto.UsuarioResponseDTO;
import br.com.mottusense.users.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
        Usuario usuarioSalvo = usuarioService.save(usuario, dataNasc);
        UsuarioResponseDTO rDTO = mapper.map(usuarioSalvo, UsuarioResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(rDTO); // Fazer os outros com base nesse
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsers() {
        List<Usuario> users = usuarioService.findAll();
        List<UsuarioResponseDTO> responseDtos = users.stream()
                .map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class))
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable String id){
        return usuarioService.findById(id)
                .map(usuario -> ResponseEntity.ok(mapper.map(usuario, UsuarioResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable String id, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return usuarioService.findById(id)
                .map(usuario -> {
                    LocalDate dataNasc = LocalDate.of(usuarioRequestDTO.getAno(), usuarioRequestDTO.getMes(), usuarioRequestDTO.getDia());
                    usuario.setNomeUsuario(usuarioRequestDTO.getNome());
                    Usuario atualizarUsuario = usuarioService.save(usuario, dataNasc );
                    return ResponseEntity.ok(mapper.map(usuario, UsuarioResponseDTO.class));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id){
        if(usuarioService.findById(id).isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
