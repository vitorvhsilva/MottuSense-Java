package br.com.mottusense.users.repository;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, String> {
}
