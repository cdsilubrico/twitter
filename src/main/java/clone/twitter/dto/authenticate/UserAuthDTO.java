package clone.twitter.dto.authenticate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"password"})
@NoArgsConstructor
public class UserAuthDTO {
    private Long accountId;
    private String email;
    private String handle;
    private String password;
}
