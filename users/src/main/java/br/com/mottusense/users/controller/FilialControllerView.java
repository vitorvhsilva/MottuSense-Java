package br.com.mottusense.users.controller.view;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.dto.input.AtualizarFilialRequestDTO;
import br.com.mottusense.users.dto.input.CadastroFilialRequestDTO;
import br.com.mottusense.users.dto.output.ObterFilialResponseDTO;
import br.com.mottusense.users.service.FilialService;
import br.com.mottusense.users.service.LocalizacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/filiaisview")
@RequiredArgsConstructor
public class FilialControllerView {

    private final FilialService filialService;
    private final LocalizacaoService localizacaoService;
    private final ModelMapper mapper;

    // GET /filiaisview/listar  -> templates/filiais/listarFiliais.html
    @GetMapping("/listar")
    public String listar(@RequestParam(value = "sucesso", required = false) String sucesso,
                         Model model) {
        List<Filial> filiais = filialService.listarFiliais();
        List<ObterFilialResponseDTO> itens = filiais.stream()
                .map(f -> mapper.map(f, ObterFilialResponseDTO.class))
                .toList();

        model.addAttribute("filiais", itens);
        if (sucesso != null) model.addAttribute("sucesso", sucesso);
        return "filiais/listar";
    }

    // GET /filiaisview/adicionar -> templates/filiais/adicionarFilial.html
    @GetMapping("/adicionar")
    public String adicionar(Model model) {
        model.addAttribute("filial", new CadastroFilialRequestDTO());
        return "filiais/adicionar";
    }

    // POST /filiaisview/salvar
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("filial") CadastroFilialRequestDTO dto,
                         BindingResult br,
                         RedirectAttributes ra,
                         Model model) {
        if (br.hasErrors()) {
            return "filiais/adicionar";
        }
        try {
            Filial entidade = mapper.map(dto, Filial.class);
            Filial salva = filialService.salvar(entidade);

            // Se seu DTO possui CEP (como na REST), persiste a localização
            if (dto.getCep() != null && !dto.getCep().isBlank()) {
                localizacaoService.persistirLocalizacao(dto.getCep(), salva);
            }

            ra.addFlashAttribute("sucesso", "Filial cadastrada com sucesso");
            return "redirect:/filiaisview/listar";
        } catch (Exception e) {
            model.addAttribute("erro", "Não foi possível cadastrar a filial.");
            return "filiais/adicionar";
        }
    }

    // GET /filiaisview/editar/{id} -> templates/filiais/editarFilial.html
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        Filial filial = filialService.obterPorId(id);
        AtualizarFilialRequestDTO form = mapper.map(filial, AtualizarFilialRequestDTO.class);

        model.addAttribute("idFilial", id);
        model.addAttribute("filial", form);
        return "filiais/editarFilial";
    }

    // POST /filiaisview/atualizar/{id}
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable String id,
                            @Valid @ModelAttribute("filial") AtualizarFilialRequestDTO dto,
                            BindingResult br,
                            RedirectAttributes ra,
                            Model model) {
        if (br.hasErrors()) {
            model.addAttribute("idFilial", id);
            return "filiais/editarFilial";
        }
        try {
            filialService.atualizarFilial(id, dto);
            ra.addFlashAttribute("sucesso", "Filial atualizada com sucesso");
            return "redirect:/filiaisview/listar";
        } catch (Exception e) {
            model.addAttribute("idFilial", id);
            model.addAttribute("erro", "Não foi possível atualizar a filial.");
            return "filiais/editarFilial";
        }
    }

    // POST /filiaisview/deletar/{id}
    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable String id, RedirectAttributes ra) {
        try {
            filialService.deletarPorId(id);
            ra.addFlashAttribute("sucesso", "Filial excluída com sucesso");
        } catch (Exception e) {
            ra.addFlashAttribute("erro", "Não foi possível excluir a filial.");
        }
        return "redirect:/filiaisview/listar";
    }
}
