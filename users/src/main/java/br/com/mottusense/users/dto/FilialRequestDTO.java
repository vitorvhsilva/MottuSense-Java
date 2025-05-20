package br.com.mottusense.users.dto;

import jakarta.validation.constraints.NotBlank;

public class FilialRequestDTO {
    @NotBlank(message = "Você precisa colocar o nome de uma filial")
    private String nomeFilial;

    public FilialRequestDTO(){

    }

    public FilialRequestDTO(String nome) {
        this.nomeFilial = nome;
    }

    public String getNome() {
        return nomeFilial;
    }

    public void setNome(String nome) {
        this.nomeFilial = nome;
    }
}
