package br.com.mottusense.users.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_CONFIGURACAO_USUARIO")
public class ConfiguracaoUsuario {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_configuracao_usuario")
    private String idConfiguracaoUsuario;

    @Column(name = "alerta_moto_entrar_patio", nullable = false)
    private Boolean alertaMotoEntrarPatio;
    @Column(name = "alerta_moto_sair_patio", nullable = false)
    private Boolean alertaMotoSairPatio;
    @Column(name = "alerta_moto_chegar_sem_placa", nullable = false)
    private Boolean alertaMotoChegarSemPlaca;
    @Column(name = "alerta_moto_precisa_manutencao", nullable = false)
    private Boolean alertaMotoPrecisaManutencao;
    @Column(name = "alerta_moto_preparada_alugada", nullable = false)
    private Boolean alertaMotoPreparadaAlugada;

    @OneToOne(mappedBy = "configuracaoUsuario")
    private Usuario usuario;
}
