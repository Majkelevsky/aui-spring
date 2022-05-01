package pl.edu.pg.eti.car.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.car.entity.Car;
import pl.edu.pg.eti.car.repository.CarRepository;
import pl.edu.pg.eti.engine.entity.Engine;
import pl.edu.pg.eti.engine.repository.EngineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;
    private EngineRepository engineRepository;

    public CarService(CarRepository carRepository, EngineRepository engineRepository) {
        this.carRepository = carRepository;
        this.engineRepository = engineRepository;
    }

    public Optional<Car> find(Long id) {
        return carRepository.findById(id);
    }

    public Optional<Car> find(String name, Long id) {
        Optional<Engine> engine = engineRepository.findById(name);
        if (engine.isPresent()) {
            return carRepository.findByIdAndEngine(id, engine.get());
        } else {
            return Optional.empty();
        }
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findAll(Engine engine) {
        return carRepository.findAllByEngine(engine);
    }

    @Transactional
    public Car create(Car car) {
        return carRepository.save(car);
    }

    @Transactional
    public void update(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
