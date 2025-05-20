package br.com.mottusense.users.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ConfiguracaoUsuarioResponseDTO {
    private String idConfiguracaoUsuario;
    private Boolean alertaMotoEntrarPatio;
    private Boolean alertaMotoSairPatio;
    private Boolean alertaMotoChegarSemPlaca;
    private Boolean alertaMotoPrecisaManutencao;
    private Boolean alertaMotoPreparadaAlugada;
}
