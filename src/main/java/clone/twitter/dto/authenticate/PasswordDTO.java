package clone.twitter.dto.authenticate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PasswordDTO {
    private String encryptedPassword;
    private String salt;
    private String hash;
}
