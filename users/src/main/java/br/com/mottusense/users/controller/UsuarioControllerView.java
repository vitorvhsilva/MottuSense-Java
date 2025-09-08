package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.dto.input.AtualizarUsuarioRequestDTO;
import br.com.mottusense.users.dto.input.CadastroUsuarioRequestDTO;
import br.com.mottusense.users.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@Data
@RequestMapping("/admin/usuarios")
@AllArgsConstructor
public class UsuarioControllerView {

    private UsuarioService usuarioService;
    private ModelMapper mapper;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("templates/usuarios", usuarios);
        model.addAttribute("titulo", "Gerenciamento de Usuários");
        return "templates/usuarios/lista";
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("usuarioDTO", new CadastroUsuarioRequestDTO());
        model.addAttribute("titulo", "Cadastrar Novo Usuário");
        model.addAttribute("action", "/admin/usuarios");
        return "templates/usuarios/form";
    }

    @PostMapping
    public String cadastrarUsuario(
            @Valid @ModelAttribute("usuarioDTO") CadastroUsuarioRequestDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Cadastrar Novo Usuário");
            model.addAttribute("action", "/admin/usuarios");
            return "templates/usuarios/form";
        }

        try {
            Usuario usuario = mapper.map(dto, Usuario.class);
            LocalDate dataNasc = LocalDate.of(dto.getAno(), dto.getMes(), dto.getDia());
            usuarioService.salvar(usuario, dataNasc);

            redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
            return "redirect:/admin/usuarios";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar usuário: " + e.getMessage());
            model.addAttribute("titulo", "Cadastrar Novo Usuário");
            model.addAttribute("action", "/admin/usuarios");
            return "templates/usuarios/form";
        }
    }

    @GetMapping("/{id}/editar")
    public String exibirFormularioEdicao(@PathVariable String id, Model model) {
        Usuario usuario = usuarioService.obterPorId(id);
        CadastroUsuarioRequestDTO dto = mapper.map(usuario, CadastroUsuarioRequestDTO.class);

        if (usuario.getDataNascimentoUsuario() != null) {
            dto.setDia(usuario.getDataNascimentoUsuario().getDayOfMonth());
            dto.setMes(usuario.getDataNascimentoUsuario().getMonthValue());
            dto.setAno(usuario.getDataNascimentoUsuario().getYear());
        }

        model.addAttribute("usuarioDTO", dto);
        model.addAttribute("titulo", "Editar Usuário");
        model.addAttribute("action", "/admin/usuarios/" + id);
        model.addAttribute("method", "put");
        return "templates/usuarios/form";
    }

    @PutMapping("/{id}")
    public String atualizarUsuario(
            @PathVariable String id,
            @Valid @ModelAttribute("usuarioDTO") AtualizarUsuarioRequestDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Usuário");
            model.addAttribute("action", "/admin/usuarios/" + id);
            model.addAttribute("method", "put");
            return "templates/usuarios/form";
        }

        try {
            usuarioService.atualizarUsuario(id, dto);
            redirectAttributes.addFlashAttribute("sucesso", "Usuário atualizado com sucesso!");
            return "redirect:/admin/usuarios";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar usuário: " + e.getMessage());
            model.addAttribute("titulo", "Editar Usuário");
            model.addAttribute("action", "/admin/usuarios/" + id);
            model.addAttribute("method", "put");
            return "templates/usuarios/form";
        }
    }

    @GetMapping("/{id}")
    public String exibirDetalhesUsuario(@PathVariable String id, Model model) {
        Usuario usuario = usuarioService.obterPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalhes do Usuário");
        return "templates/usuarios/detalhes";
    }

    // Excluir usuário
    @DeleteMapping("/{id}")
    public String excluirUsuario(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.deletarPorId(id);
            redirectAttributes.addFlashAttribute("sucesso", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir usuário: " + e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }

    // Confirmação de exclusão
    @GetMapping("/{id}/excluir")
    public String confirmarExclusao(@PathVariable String id, Model model) {
        Usuario usuario = usuarioService.obterPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Confirmar Exclusão");
        return "templates/usuarios/confirmar-exclusao";
    }
}