package br.com.accenture.apipessoa.services;

import br.com.accenture.apipessoa.exceptions.ObjectNotFoundException;
import br.com.accenture.apipessoa.domain.Pessoa;
import br.com.accenture.apipessoa.domain.dto.PessoaDTO;
import br.com.accenture.apipessoa.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    private final ModelMapper mapper;

    public PessoaService(PessoaRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Cacheable("pessoas")
    public Pessoa findById(Integer id) {
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Cacheable("pessoas")
    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa create(PessoaDTO obj) {
        return repository.save(mapper.map(obj, Pessoa.class));
    }

    public Pessoa update(PessoaDTO obj) {
        return repository.save(mapper.map(obj, Pessoa.class));
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
