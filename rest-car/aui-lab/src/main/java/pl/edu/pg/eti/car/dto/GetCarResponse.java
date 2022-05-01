package pl.edu.pg.eti.car.dto;

import lombok.*;
import pl.edu.pg.eti.car.entity.Car;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCarResponse {

    private Long id;
    private String name;
    private String engine;
    private int year;

    public static Function<Car, GetCarResponse> entityToDtoMapper() {
        return car -> GetCarResponse.builder()
                .id(car.getId())
                .name(car.getName())
                .engine(car.getEngine().getName())
                .year(car.getYear())
                .build();
    }
}
