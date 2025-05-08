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

    public String getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(String idFilial) {
        this.idFilial = idFilial;
    }

    public String getNomeFilial() {
        return nomeFilial;
    }

    public void setNomeFilial(String nomeFilial) {
        this.nomeFilial = nomeFilial;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
