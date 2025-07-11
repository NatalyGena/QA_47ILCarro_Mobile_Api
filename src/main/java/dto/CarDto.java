package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor

public class CarDto {
private String      serialNumber;
private String       manufacture;
private String       model;
private String       year;
private String       fuel;
private int       seats;
private String       carClass;
private double       pricePerDay;
private String      city;
}
