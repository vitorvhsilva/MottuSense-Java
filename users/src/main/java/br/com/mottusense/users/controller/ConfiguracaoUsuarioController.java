package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.ConfiguracaoUsuario;
import br.com.mottusense.users.dto.ConfiguracaoUsuarioRequestDTO;
import br.com.mottusense.users.dto.ConfiguracaoUsuarioResponseDTO;
import br.com.mottusense.users.service.ConfiguracaoUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("configuracoes")
@RestController
public class ConfiguracaoUsuarioController {

    private ModelMapper mapper;
    private ConfiguracaoUsuarioService service;

    @PutMapping
    public ResponseEntity<ConfiguracaoUsuarioResponseDTO> alterarConfiguracao(@RequestBody @Valid ConfiguracaoUsuarioRequestDTO dto) {
        ConfiguracaoUsuario config = mapper.map(dto, ConfiguracaoUsuario.class);
        ConfiguracaoUsuarioResponseDTO response = mapper.map(service.alterarConfiguracao(config), ConfiguracaoUsuarioResponseDTO.class);
        return ResponseEntity.ok(response);
    }

}
