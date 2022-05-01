package pl.edu.pg.eti.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.engine.entity.Engine;

@Repository
public interface EngineRepository extends JpaRepository<Engine, String> {
}
