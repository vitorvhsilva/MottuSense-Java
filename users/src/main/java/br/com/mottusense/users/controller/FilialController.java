package br.com.mottusense.users.controller;


import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.dto.FilialRequestDTO;
import br.com.mottusense.users.dto.FilialResponseDTO;
import br.com.mottusense.users.service.FilialService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/filiais")
public class FilialController {

    private FilialService filialService;
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<FilialResponseDTO> cadastrarFilial(@RequestBody FilialRequestDTO filialRequestDTO){
        Filial filial = mapper.map(filialRequestDTO, Filial.class);
        Filial filialSalva = filialService.salvar(filial);
        FilialResponseDTO rDTO = mapper.map(filialSalva, FilialResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(rDTO);
    }

    @GetMapping
    public ResponseEntity<List<FilialResponseDTO>> listarFiliais(){
        List<Filial> filiais = filialService.listarFiliais();
        List<FilialResponseDTO> responseDTOS = filiais.stream()
                .map(filial -> mapper.map(filial, FilialResponseDTO.class))
                .toList();
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialResponseDTO> buscarFilialPorId(@PathVariable String id){
        return filialService.listarPorId(id)
                .map(filial -> ResponseEntity.ok(mapper.map(filial, FilialResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilialResponseDTO> atualizarFilial(@PathVariable String id, @Valid @RequestBody FilialRequestDTO filialRequestDTO){
        return filialService.listarPorId(id)
                .map(filial -> {
                    filial.setNomeFilial(filialRequestDTO.getNome());
                    Filial atualizarFilial = filialService.salvar(filial);
                    return ResponseEntity.ok(mapper.map(filial, FilialResponseDTO.class));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilial(@PathVariable String id){
        if(filialService.listarPorId(id).isPresent()) {
            filialService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
