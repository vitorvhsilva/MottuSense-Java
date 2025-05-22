package br.com.mottusense.users.dto;

import br.com.mottusense.users.domain.Localizacao;
import lombok.Data;

@Data
public class FilialResponseDTO {

    private String idFilial;
    private String nomeFilial;
    private Localizacao localizacao;
}
