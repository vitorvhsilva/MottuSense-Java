package br.com.mottusense.users.controller;

import br.com.mottusense.users.domain.Usuario;
import br.com.mottusense.users.dto.input.AtualizarUsuarioRequestDTO;
import br.com.mottusense.users.dto.input.CadastroUsuarioRequestDTO;
import br.com.mottusense.users.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/usuariosview")
@AllArgsConstructor
public class UsuarioControllerView {

    private final UsuarioService usuarioService;
    private final ModelMapper mapper;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios/listarUsuarios";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicao(Model model) {
        model.addAttribute("usuario", new CadastroUsuarioRequestDTO());
        return "usuarios/formularioUsuario";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@Valid @ModelAttribute("usuario") CadastroUsuarioRequestDTO dto,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "usuarios/formularioUsuario";
        }
        Usuario usuario = mapper.map(dto, Usuario.class);
        LocalDate dataNasc = LocalDate.of(dto.getAno(), dto.getMes(), dto.getDia());
        usuarioService.salvar(usuario, dataNasc);
        return "redirect:/usuariosview/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable String id, Model model) {
        Usuario usuario = usuarioService.obterPorId(id);

        AtualizarUsuarioRequestDTO dto = mapper.map(usuario, AtualizarUsuarioRequestDTO.class);

        model.addAttribute("usuario", dto);
        model.addAttribute("id", id);
        return "usuarios/editarUsuario";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarUsuario(@PathVariable String id,
                                   @Valid @ModelAttribute("usuario") AtualizarUsuarioRequestDTO dto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            return "usuarios/editarUsuario";
        }

        AtualizarUsuarioRequestDTO apiDto = mapper.map(dto, AtualizarUsuarioRequestDTO.class);

        usuarioService.atualizarUsuario(id, apiDto);
        return "redirect:/usuariosview/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable String id) {
        usuarioService.deletarPorId(id);
        return "redirect:/usuariosview/listar";
    }

    @GetMapping("/detalhar/{id}")
    public String detalharUsuario(@PathVariable String id, Model model) {
        model.addAttribute("usuario", usuarioService.obterPorId(id));
        return "usuarios/detalharUsuario";
    }
}
