package pl.edu.pg.eti.engine.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.engine.entity.Engine;
import pl.edu.pg.eti.engine.event.repository.EngineEventRepository;
import pl.edu.pg.eti.engine.repository.EngineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EngineService {

    private EngineRepository repository;
    private EngineEventRepository eventRepository;

    public EngineService(EngineRepository repository, EngineEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Engine> find(String name) {
        return repository.findById(name);
    }

    public List<Engine> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Engine engine) {
        repository.save(engine);
        eventRepository.create(engine);

    }

    @Transactional
    public void update(Engine engine) {
        repository.save(engine);
    }

    @Transactional
    public void delete(Engine engine) {
        repository.delete(engine);
        eventRepository.delete(engine);
    }
}
