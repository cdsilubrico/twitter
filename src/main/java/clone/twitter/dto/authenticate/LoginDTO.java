package clone.twitter.dto.authenticate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "password")
public class LoginDTO {
    private String emailOrHandle;
    private String password;
}
