package pl.edu.pg.eti.engine.dto;

import lombok.*;
import pl.edu.pg.eti.engine.entity.Engine;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetEngineResponse {
    private String name;
    private double volume;
    private int horsePower;

    public static Function<Engine, GetEngineResponse> entityToDtoMapper() {
        return engine -> GetEngineResponse.builder()
                .name(engine.getName())
                .volume(engine.getVolume())
                .horsePower(engine.getHorsePower())
                .build();
    }
}
