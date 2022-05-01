package pl.edu.pg.eti.configuration;

import org.springframework.stereotype.Component;
import pl.edu.pg.eti.engine.entity.Engine;
import pl.edu.pg.eti.engine.service.EngineService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final EngineService engineService;

    public InitializedData(EngineService engineService) {
        this.engineService = engineService;
    }

    @PostConstruct
    private synchronized void init() {
        Engine LV8 = Engine.builder()
                .name("LV8")
                .horsePower(460)
                .volume(5.0)
                .build();

        Engine B48 = Engine.builder()
                .name("B48")
                .horsePower(194)
                .volume(2.0)
                .build();

        engineService.create(LV8);
        engineService.create(B48);
    }
}
