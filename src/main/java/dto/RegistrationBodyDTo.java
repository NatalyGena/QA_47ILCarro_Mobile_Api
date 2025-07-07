package dto;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor

public class RegistrationBodyDTo {

  private  String username;
  private String password;
  private String firstName;
  private String lastName;
}
