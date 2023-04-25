package clone.twitter.dto.authenticate;

import clone.twitter.model.auth.UserAuth;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString(exclude = {"password"})
@NoArgsConstructor
public class UserAuthDTO {
    private Long accountId;
    @NotEmpty(message = "Email field can't be empty.")
    private String email;
    @NotEmpty(message = "Handle field can't be empty.")
    private String handle;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "Password field can't be empty.")
    private String password;

    public UserAuthDTO(final UserAuth userAuth) {
        this.accountId = userAuth.getAccountId();
        this.email = userAuth.getEmail();
        this.handle = userAuth.getHandle();
    }
}
