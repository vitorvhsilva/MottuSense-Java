package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Localizacao;
import br.com.mottusense.users.dto.input.AtualizarFilialRequestDTO;
import br.com.mottusense.users.dto.input.CadastroFilialRequestDTO;
import br.com.mottusense.users.service.FilialService;
import br.com.mottusense.users.service.LocalizacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/filiais")
@AllArgsConstructor
public class FilialControllerView {

    private FilialService filialService;
    private LocalizacaoService localizacaoService;
    private ModelMapper mapper;

    // Página inicial - Lista de filiais
    @GetMapping
    public String listarFiliais(Model model) {
        List<Filial> filiais = filialService.listarFiliais();
        model.addAttribute("templates/filiais", filiais);
        model.addAttribute("titulo", "Gerenciamento de Filiais");
        return "templates/filiais/lista";
    }

    // Formulário de cadastro de filial
    @GetMapping("/nova")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("filialDTO", new CadastroFilialRequestDTO());
        model.addAttribute("titulo", "Cadastrar Nova Filial");
        model.addAttribute("action", "/admin/filiais");
        return "templates/filiais/form";
    }

    // Processar cadastro de filial
    @PostMapping
    public String cadastrarFilial(
            @Valid @ModelAttribute("filialDTO") CadastroFilialRequestDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Cadastrar Nova Filial");
            model.addAttribute("action", "/admin/filiais");
            return "templates/filiais/form";
        }

        try {
            Filial filial = mapper.map(dto, Filial.class);
            Filial filialSalva = filialService.salvar(filial);
            Localizacao localizacao = localizacaoService.persistirLocalizacao(dto.getCep(), filialSalva);

            redirectAttributes.addFlashAttribute("sucesso", "Filial cadastrada com sucesso!");
            return "redirect:/admin/filiais";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar filial: " + e.getMessage());
            model.addAttribute("titulo", "Cadastrar Nova Filial");
            model.addAttribute("action", "/admin/filiais");
            return "templates/filiais/form";
        }
    }

    // Formulário de edição de filial
    @GetMapping("/{id}/editar")
    public String exibirFormularioEdicao(@PathVariable String id, Model model) {
        Filial filial = filialService.obterPorId(id);
        CadastroFilialRequestDTO dto = mapper.map(filial, CadastroFilialRequestDTO.class);

        // Preenche o CEP da localização se existir
        if (filial.getLocalizacao() != null) {
            dto.setCep(filial.getLocalizacao().getCepLocalizacao());
        }

        model.addAttribute("filialDTO", dto);
        model.addAttribute("titulo", "Editar Filial");
        model.addAttribute("action", "/admin/filiais/" + id);
        model.addAttribute("method", "put");
        return "templates/filiais/form";
    }

    // Processar edição de filial
    @PutMapping("/{id}")
    public String atualizarFilial(
            @PathVariable String id,
            @Valid @ModelAttribute("filialDTO") AtualizarFilialRequestDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Filial");
            model.addAttribute("action", "/admin/filiais/" + id);
            model.addAttribute("method", "put");
            return "templates/filiais/form";
        }

        try {
            Filial filial = filialService.atualizarFilial(id, dto);

            // Atualiza a localização se o CEP foi alterado
            if (dto.getCep() != null && !dto.getCep().isEmpty()) {
                localizacaoService.persistirLocalizacao(dto.getCep(), filial);
            }

            redirectAttributes.addFlashAttribute("sucesso", "Filial atualizada com sucesso!");
            return "redirect:/admin/filiais";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar filial: " + e.getMessage());
            model.addAttribute("titulo", "Editar Filial");
            model.addAttribute("action", "/admin/filiais/" + id);
            model.addAttribute("method", "put");
            return "templates/filiais/form";
        }
    }

    // Detalhes da filial
    @GetMapping("/{id}")
    public String exibirDetalhesFilial(@PathVariable String id, Model model) {
        Filial filial = filialService.obterPorId(id);
        model.addAttribute("filial", filial);
        model.addAttribute("titulo", "Detalhes da Filial");
        return "templates/filiais/detalhes";
    }

    // Excluir filial
    @DeleteMapping("/{id}")
    public String excluirFilial(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            filialService.deletarPorId(id);
            redirectAttributes.addFlashAttribute("sucesso", "Filial excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir filial: " + e.getMessage());
        }
        return "redirect:/admin/filiais";
    }

    // Confirmação de exclusão
    @GetMapping("/{id}/excluir")
    public String confirmarExclusao(@PathVariable String id, Model model) {
        Filial filial = filialService.obterPorId(id);
        model.addAttribute("filial", filial);
        model.addAttribute("titulo", "Confirmar Exclusão");
        return "templates/filiais/confirmar-exclusao";
    }

    @GetMapping("/buscar")
    public String buscarFiliaisPorId(@PathVariable String id, Model model) {
        List<Filial> filiais = new ArrayList<>();

        if (id != null && !id.isEmpty()) {
            try {
                Filial filial = filialService.obterPorId(id);
                if (filial != null) {
                    filiais.add(filial);
                }
                model.addAttribute("termoBusca", id);
            } catch (Exception e) {
                model.addAttribute("erro", "Filial não encontrada com o ID: " + id);
            }
        } else {
            filiais = filialService.listarFiliais();
        }

        model.addAttribute("templates/filiais", filiais);
        model.addAttribute("titulo", "Resultado da Busca");
        return "templates/filiais/lista";
    }
}