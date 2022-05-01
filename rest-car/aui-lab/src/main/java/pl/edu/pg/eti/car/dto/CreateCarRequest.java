package pl.edu.pg.eti.car.dto;

import lombok.*;
import pl.edu.pg.eti.car.entity.Car;
import pl.edu.pg.eti.engine.entity.Engine;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCarRequest {

    private String name;
    private String engine;
    private int year;

    public static Function<CreateCarRequest, Car> dtoToEntityMapper(Function<String, Engine> engineFunction) {
        return request -> Car.builder()
                .name(request.getName())
                .engine(engineFunction.apply(request.getEngine()))
                .year(request.getYear())
                .build();
    }
}
