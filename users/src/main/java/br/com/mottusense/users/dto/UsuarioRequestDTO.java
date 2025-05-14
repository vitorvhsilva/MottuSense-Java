package br.com.mottusense.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UsuarioRequestDTO {
    @NotBlank(message = "É necessário digitar um nome de usuário")
    @Size(min = 3, message = "O nome deve ter mais de 3 caracteres;")
    private String nome;

    @NotBlank(message = "É necessário digitar um cpf")
    @Size(min = 11, max = 11, message = "O CPF não possui números diferentes")
    private String cpf;

    @NotBlank(message = "É necessário digitar um número de telefone")
    private String telefone;

    @NotBlank(message = "É necessário digitar um email")
    private String email;

    @NotBlank(message = "É necessário digitar uma senha")
    @Size(min = 6, message = "A senha deve ter mais de 6 caracteres")
    private String senha;

    @NotBlank(message = "É necessário digitar um CEP válido")
    @Size(min = 8, max = 8, message = "O CEP deve conter 8 digitos")
    private String cep;

    @NotBlank(message = "É necessário digitar a sua data de nascimento")
    private Integer dia;
    private Integer mes;
    private Integer ano;


    public UsuarioRequestDTO(){

    }

    public UsuarioRequestDTO(String nome, String cpf, String telefone, String email, String senha, String cep, Integer dia, Integer mes, Integer ano) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
