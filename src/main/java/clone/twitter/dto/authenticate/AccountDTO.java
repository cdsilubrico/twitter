package clone.twitter.dto.authenticate;

import clone.twitter.model.auth.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"password", "dateCreated", "accountId"})
public class AccountDTO {
    @NotEmpty(message = "First Name field can't be empty.")
    private String firstName;
    @NotEmpty(message = "Last Name field can't be empty.")
    private String lastName;
    @NotEmpty(message = "Email field can't be empty.")
    @Email(message = "Invalid Email Format")
    private String email;
    @NotEmpty(message = "Handle field can't be empty.")
    private String handle;
    @NotEmpty(message = "Password field can't be empty.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private LocalDate dateCreated;
    private long accountId;

    public AccountDTO(final Account account) {
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.email = account.getEmail();
        this.dateCreated = account.getDateCreated();
        this.handle = account.getHandle();
        this.accountId = account.getAccountId();
    }
}
