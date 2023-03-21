package clone.twitter.dto.authenticate;

import clone.twitter.model.auth.Account;
import clone.twitter.model.auth.Login;
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
