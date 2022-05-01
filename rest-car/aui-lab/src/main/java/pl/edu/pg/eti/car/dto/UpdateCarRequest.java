package pl.edu.pg.eti.car.dto;

import lombok.*;
import pl.edu.pg.eti.car.entity.Car;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateCarRequest {

    private String name;
    private int year;

    public static BiFunction<Car, UpdateCarRequest, Car> dtoToEntityUpdater() {
        return (car, request) -> {
            car.setName(request.getName());
            car.setYear(request.getYear());
            return car;
        };
    }
}
