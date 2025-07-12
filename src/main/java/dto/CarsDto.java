package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor


public class CarsDto {
    CarDto [] cars;
}
