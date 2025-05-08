package br.com.mottusense.users.controller;


import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filiais")
public class FilialController {

    @Autowired
    private FilialService filialService;

    @PostMapping
    public void cadastrarFilial(@RequestBody Filial filial){
        filialService.cadastrarFilial(filial);
    }

    @GetMapping
    public List<Filial> listarFiliais(){
        return filialService.listarFiliais();
    }

    @GetMapping("/{id}")
    public Filial buscarFilialPorId(@PathVariable int id){
        return filialService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public void atualizarFilial(@PathVariable int id, @RequestBody Filial filial){
        filial.setIdFilial(String.valueOf(id)); // Ver isso c o vito
        filialService.atualizarFilial(filial);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id){
        filialService.deletarFilial(id);
    }

}
