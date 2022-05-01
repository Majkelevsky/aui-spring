package pl.edu.pg.eti.car.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.car.dto.CreateCarRequest;
import pl.edu.pg.eti.car.dto.GetCarResponse;
import pl.edu.pg.eti.car.dto.GetCarsResponse;
import pl.edu.pg.eti.car.dto.UpdateCarRequest;
import pl.edu.pg.eti.car.entity.Car;
import pl.edu.pg.eti.car.service.CarService;
import pl.edu.pg.eti.engine.entity.Engine;
import pl.edu.pg.eti.engine.service.EngineService;

import java.util.Optional;

@RestController
@RequestMapping("api/engines/{name}/cars")
public class EngineCarController {

    private CarService carService;

    private EngineService engineService;

    public EngineCarController(CarService carService, EngineService engineService) {
        this.carService = carService;
        this.engineService = engineService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars(@PathVariable("name") String name) {
        Optional<Engine> engine = engineService.find(name);
        return engine
                .map(value -> ResponseEntity.ok(GetCarsResponse.entityToDtoMapper().apply(carService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("name") String name, @PathVariable("id") long id) {
        return carService.find(name, id)
                .map(value -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@PathVariable("name") String name, @RequestBody CreateCarRequest request,
                                          UriComponentsBuilder builder) {
        Optional<Engine> engine = engineService.find(name);
        if (engine.isPresent()) {
            Car car = CreateCarRequest
                    .dtoToEntityMapper(val -> engine.get())
                    .apply(request);
            car = carService.create(car);
            return ResponseEntity.created(builder.pathSegment("api", "engines", "{name}", "cars", "{id}")
                    .buildAndExpand(engine.get().getName(), car.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("name") String name, @PathVariable("id") long id) {
        Optional<Car> car = carService.find(name, id);
        if (car.isPresent()) {
            carService.delete(car.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCar(@PathVariable("name") String name, @RequestBody UpdateCarRequest request,
                                          @PathVariable("id") long id) {
        Optional<Car> car = carService.find(name, id);
        if (car.isPresent()) {
            UpdateCarRequest.dtoToEntityUpdater().apply(car.get(), request);
            carService.update(car.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
