package br.com.mottusense.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FilialRequestDTO {
    @NotBlank(message = "Você precisa colocar o nome de uma filial")
    private String nomeFilial;
}
