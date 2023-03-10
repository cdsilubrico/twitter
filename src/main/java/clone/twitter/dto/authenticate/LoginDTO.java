package clone.twitter.dto.authenticate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    private String emailOrHandle;
    private String password;
}
