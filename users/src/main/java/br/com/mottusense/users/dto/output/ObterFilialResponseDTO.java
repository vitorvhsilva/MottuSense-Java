package br.com.mottusense.users.dto.output;

import br.com.mottusense.users.domain.Localizacao;
import lombok.Data;

@Data
public class ObterFilialResponseDTO {
    private String idFilial;
    private String nomeFilial;
    private Localizacao localizacao;
}
