package br.com.mottusense.users.repository;

import br.com.mottusense.users.domain.Filial;
import br.com.mottusense.users.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilialRepository extends JpaRepository<Filial, String> {
}
