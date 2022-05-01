package pl.edu.pg.eti.configuration;

import org.springframework.stereotype.Component;
import pl.edu.pg.eti.car.entity.Car;
import pl.edu.pg.eti.car.service.CarService;
import pl.edu.pg.eti.engine.entity.Engine;
import pl.edu.pg.eti.engine.service.EngineService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final CarService carService;
    private final EngineService engineService;

    public InitializedData(CarService carService, EngineService engineService) {
        this.carService = carService;
        this.engineService = engineService;
    }

    @PostConstruct
    private synchronized void init() {
        Engine LV8 = Engine.builder()
                .name("LV8")
                .build();

        Engine B48 = Engine.builder()
                .name("B48")
                .build();

        engineService.create(LV8);
        engineService.create(B48);

        Car Mustang = Car.builder()
                .name("Ford Mustang GT")
                .engine(LV8)
                .year(2018)
                .build();

        Car Z4 = Car.builder()
                .name("BMW Z4 sDrive20i")
                .engine(B48)
                .year(2019)
                .build();

        carService.create(Mustang);
        carService.create(Z4);
    }
}
