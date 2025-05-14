package br.com.mottusense.users.dto;

import jakarta.validation.constraints.NotBlank;

public class FilialRequestDTO {
    @NotBlank(message = "Você precisa colocar o nome de uma filial")
    private String nome;

    public FilialRequestDTO(){

    }

    public FilialRequestDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
