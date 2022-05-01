package pl.edu.pg.eti.engine.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.engine.entity.Engine;
import pl.edu.pg.eti.engine.repository.EngineRepository;

import java.util.Optional;

@Service
public class EngineService {

    private EngineRepository repository;

    public EngineService(EngineRepository repository) {
        this.repository = repository;
    }

    public Optional<Engine> find(String name) {
        return repository.findById(name);
    }

    @Transactional
    public void create(Engine engine) {
        repository.save(engine);
    }

    @Transactional
    public void delete(Engine engine) {
        repository.delete(engine);
    }
}
