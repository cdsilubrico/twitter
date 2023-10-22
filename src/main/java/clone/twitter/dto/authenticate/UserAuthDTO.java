package clone.twitter.dto.authenticate;

import clone.twitter.model.auth.UserAuth;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = {"password", "token"})
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
    private String token;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    public UserAuthDTO(final UserAuth userAuth) {
        this.accountId = userAuth.getUserId();
        this.email = userAuth.getEmail();
        this.handle = userAuth.getHandle();
        this.dateCreated = userAuth.getDateCreated();
        this.lastUpdated = userAuth.getLastUpdated();
    }
}
