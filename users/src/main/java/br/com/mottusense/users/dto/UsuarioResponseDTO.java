package br.com.mottusense.users.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UsuarioResponseDTO {

    private String nomeUsuario;
    private String cpfUsuario;
    private String telefoneUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String linkFotoUsuario;
    private LocalDate dataNascimentoUsuario;
    private LocalDateTime dataCriacaoUsuario;

}
