package pl.edu.pg.eti.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.car.entity.Car;
import pl.edu.pg.eti.engine.entity.Engine;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByIdAndEngine(Long id, Engine engine);

    List<Car> findAllByEngine(Engine engine);
}
