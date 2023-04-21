package clone.twitter.model.auth;

import clone.twitter.dto.authenticate.AccountDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static clone.twitter.util.DateUtil.getLocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "practice_app_one_account")
public class Account {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "handle", nullable = false, unique = true)
    private String handle;
    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    public Account(final AccountDTO accountDTO) {
        this.firstName = accountDTO.getFirstName();
        this.lastName = accountDTO.getLastName();
        this.password = accountDTO.getPassword();
        this.email = accountDTO.getEmail();
        this.handle = "@" + accountDTO.getHandle();
        this.dateCreated = getLocalDate();
    }
}
