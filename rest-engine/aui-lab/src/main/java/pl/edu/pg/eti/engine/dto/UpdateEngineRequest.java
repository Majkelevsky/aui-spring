package pl.edu.pg.eti.engine.dto;

import lombok.*;
import pl.edu.pg.eti.engine.entity.Engine;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateEngineRequest {

    private int horsePower;
    private double volume;

    public static BiFunction<Engine, UpdateEngineRequest, Engine> dtoToEntityUpdater() {
        return (engine, request) -> {
            engine.setHorsePower(request.getHorsePower());
            engine.setVolume(request.getVolume());
            return engine;
        };
    }
}
