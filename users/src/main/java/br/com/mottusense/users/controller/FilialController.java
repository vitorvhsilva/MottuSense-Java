package br.com.mottusense.users.controller;


import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filiais")
public class FilialController {

    @Autowired
    private FilialService filialService;

    @PostMapping
    public void cadastrarFilial( @RequestBody Filial filial){
        filialService.save(filial);
    }

    @GetMapping
    public List<Filial> listarFiliais(){
        return filialService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Filial> buscarFilialPorId(@PathVariable String id){
        return filialService.findById(id);
    }

    @PutMapping("/{id}")
    public void atualizarFilial(@PathVariable String id, @RequestBody Filial filial){
        filial.setIdFilial(String.valueOf(id)); // Ver isso c o vito
        filialService.save(filial);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id){
        filialService.deleteById(id);
    }


}
