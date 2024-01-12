package br.com.accenture.apipessoa.repositories;

import br.com.accenture.apipessoa.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByLogin(String login);
}