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
import pl.edu.pg.eti.engine.service.EngineService;

import java.util.Optional;

@RestController
@RequestMapping("api/cars")
public class CarController {

    private CarService carService;
    private EngineService engineService;

    public CarController(CarService carService, EngineService engineService) {
        this.carService = carService;
        this.engineService = engineService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars() {
        return ResponseEntity.ok(GetCarsResponse.entityToDtoMapper().apply(carService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("id") long id) {
        return carService.find(id)
                .map(value -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CreateCarRequest request, UriComponentsBuilder builder) {
        Car car = CreateCarRequest
                .dtoToEntityMapper(name -> engineService.find(name).orElseThrow())
                .apply(request);
        car = carService.create(car);
        return ResponseEntity.created(builder.pathSegment("api", "cars", "{id}")
                .buildAndExpand(car.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") long id) {
        Optional<Car> car = carService.find(id);
        if (car.isPresent()) {
            carService.delete(car.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCar(@RequestBody UpdateCarRequest request, @PathVariable("id") long id) {
        Optional<Car> car = carService.find(id);
        if (car.isPresent()) {
            UpdateCarRequest.dtoToEntityUpdater().apply(car.get(), request);
            carService.update(car.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
