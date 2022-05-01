package pl.edu.pg.eti.engine.entity;

import lombok.*;
import pl.edu.pg.eti.car.entity.Car;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "engines")
public class Engine implements Serializable {

    @Id
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "engine")
    @ToString.Exclude
    private List<Car> cars;
}
