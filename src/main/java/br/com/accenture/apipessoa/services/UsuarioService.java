package br.com.accenture.apipessoa.services;

import br.com.accenture.apipessoa.domain.Usuario;
import br.com.accenture.apipessoa.domain.dto.UsuarioDTO;
import br.com.accenture.apipessoa.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final ModelMapper mapper;

    public UsuarioService(UsuarioRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario create(UsuarioDTO obj) {
        return repository.save(mapper.map(obj, Usuario.class));
    }

    public Optional<Usuario> validPassword(String login){
        Optional<Usuario> optUsuario = repository.findByLogin(login);
        return optUsuario;

    }
}
