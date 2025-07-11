package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor


public class TokenDto {
    private String accessToken;
}
