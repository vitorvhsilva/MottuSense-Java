package br.com.mottusense.users.dto.output;

import br.com.mottusense.users.domain.ConfiguracaoUsuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ObterUsuarioResponseDTO {
    private String idUsuario;
    private String nomeUsuario;
    private String cpfUsuario;
    private String telefoneUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String linkFotoUsuario;
    private LocalDate dataNascimentoUsuario;
    private LocalDateTime dataCriacaoUsuario;
    private ConfiguracaoUsuario configuracaoUsuario;
}
