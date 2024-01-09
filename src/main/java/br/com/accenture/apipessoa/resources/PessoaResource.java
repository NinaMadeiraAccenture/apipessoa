package br.com.accenture.apipessoa.resources;

import br.com.accenture.apipessoa.message.KafkaProducerMessage;
import br.com.accenture.apipessoa.domain.dto.PessoaDTO;
import br.com.accenture.apipessoa.services.PessoaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

    private final Logger LOG = LoggerFactory.getLogger(PessoaResource.class);

    private static final String ID = "/{id}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PessoaService service;

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @GetMapping(value = ID)
    public ResponseEntity<PessoaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), PessoaDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x, PessoaDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO obj,  UriComponentsBuilder uriBuilder) {
        var pessoaDTO = mapper.map(service.create(obj), PessoaDTO.class);
        if(null != pessoaDTO.getId()) {
            LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Pessoa Post information: {}", pessoaDTO);

            kafkaProducerMessage.sendMessage(pessoaDTO);
        }
        URI endereco = uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoaDTO.getId()).toUri();
        return ResponseEntity.created(endereco).body(pessoaDTO);

    }

    @PutMapping(value = ID)
    public ResponseEntity<PessoaDTO> update(@PathVariable Integer id, @Valid @RequestBody PessoaDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(obj), PessoaDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<PessoaDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
