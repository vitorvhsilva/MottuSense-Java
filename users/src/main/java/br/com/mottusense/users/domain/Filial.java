package br.com.mottusense.users.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TB_FILIAL")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Filial {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_filial")
    private String idFilial;

    @Column(name = "nome_filial", nullable = false)
    private String nomeFilial;

    @ManyToMany(mappedBy = "filiais")
    private List<Usuario> usuario;

    @OneToOne
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;
}
