package clone.twitter.model.auth.password;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Password {
    private String encryptedPassword;
    private String saltValue;
    private String hashValue;
}
