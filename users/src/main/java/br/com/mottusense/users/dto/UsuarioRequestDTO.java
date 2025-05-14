package br.com.mottusense.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
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
}
