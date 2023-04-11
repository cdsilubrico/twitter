package clone.twitter.model.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "user_auth")
@ToString(exclude = {"password"})
@NoArgsConstructor
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String email;
    private String handle;
    //    @Column(name = "hash", nullable = false)
//    private String hash;
//    @Column(name = "salt", nullable = false)
//    private String salt;
    private String password;

    public UserAuth(final UserAuthDTO userAuthDTO) {
        this.accountId = getAccountId();
        this.email = userAuthDTO.getEmail();
        this.handle = userAuthDTO.getHandle();
        this.password = userAuthDTO.getPassword();
    }
}
