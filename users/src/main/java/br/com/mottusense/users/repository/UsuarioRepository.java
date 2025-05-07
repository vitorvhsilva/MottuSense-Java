package br.com.mottusense.users.repository;

import br.com.mottusense.users.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    void deleteAllById(int id);
}
