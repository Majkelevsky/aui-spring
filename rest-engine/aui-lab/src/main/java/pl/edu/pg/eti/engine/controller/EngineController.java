package pl.edu.pg.eti.engine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.engine.dto.CreateEngineRequest;
import pl.edu.pg.eti.engine.dto.GetEngineResponse;
import pl.edu.pg.eti.engine.dto.GetEnginesResponse;
import pl.edu.pg.eti.engine.dto.UpdateEngineRequest;
import pl.edu.pg.eti.engine.entity.Engine;
import pl.edu.pg.eti.engine.service.EngineService;

import java.util.Optional;

@RestController
@RequestMapping("api/engines")
public class EngineController {

    private EngineService engineService;

    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @GetMapping
    public ResponseEntity<GetEnginesResponse> getEngines() {
        return ResponseEntity.ok(GetEnginesResponse.entityToDtoMapper().apply(engineService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetEngineResponse> getEngine(@PathVariable("name") String name) {
        return engineService.find(name)
                .map(value -> ResponseEntity.ok(GetEngineResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteEngine(@PathVariable("name") String name) {
        Optional<Engine> engine = engineService.find(name);
        if (engine.isPresent()) {
            engineService.delete(engine.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createEngine(@RequestBody CreateEngineRequest request, UriComponentsBuilder builder) {
        Engine engine = CreateEngineRequest.dtoToEntityMapper().apply(request);
        engineService.create(engine);
        return ResponseEntity.created(builder.pathSegment("api", "engines", "name")
                .buildAndExpand(engine.getName()).toUri()).build();
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateEngine(@RequestBody UpdateEngineRequest request,
                                             @PathVariable("name") String name) {
        Optional<Engine> engine = engineService.find(name);
        if (engine.isPresent()) {
            UpdateEngineRequest.dtoToEntityUpdater().apply(engine.get(), request);
            engineService.update(engine.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
