package br.com.accenture.apipessoa.resources;

import br.com.accenture.apipessoa.domain.Usuario;
import br.com.accenture.apipessoa.domain.dto.PessoaDTO;
import br.com.accenture.apipessoa.domain.dto.UsuarioDTO;
import br.com.accenture.apipessoa.repositories.UsuarioRepository;
import br.com.accenture.apipessoa.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

    private PasswordEncoder encoder;

    private ModelMapper mapper;

    private UsuarioService service;

    public UsuarioResource(PasswordEncoder encoder, ModelMapper mapper, UsuarioService service) {
        this.encoder = encoder;
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x, UsuarioDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody UsuarioDTO usuarioDTO) {

        usuarioDTO.setPassword(encoder.encode(usuarioDTO.getPassword()));
        return ResponseEntity.ok(service.create(usuarioDTO));
    }

    @GetMapping("/validPassword")
    public ResponseEntity<Boolean> validPassword(@RequestParam String login,
                                                @RequestParam String password) {

        Optional<Usuario> optUsuario = service.validPassword(login);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Usuario usuario = optUsuario.get();
        boolean valid = encoder.matches(password, usuario.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);

    }
}
