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
public class CreateEngineRequest {

    private String name;

    public static Function<CreateEngineRequest, Engine> dtoToEntityMapper() {
        return request -> Engine.builder()
                .name(request.getName())
                .build();
    }

}
