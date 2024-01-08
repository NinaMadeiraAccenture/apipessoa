package br.com.accenture.apipessoa.services;

import br.com.accenture.apipessoa.exceptions.ObjectNotFoundException;
import br.com.accenture.apipessoa.domain.Pessoa;
import br.com.accenture.apipessoa.domain.dto.PessoaDTO;
import br.com.accenture.apipessoa.repositories.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    @Autowired
    private ModelMapper mapper;

    public Pessoa findById(Integer id) {
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

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
