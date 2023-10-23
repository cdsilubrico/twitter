package clone.twitter.model.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "user_auth")
@ToString(exclude = {"password"})
@NoArgsConstructor
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String email;
    private String handle;
    private String password;
    private String token;
    @Column(name = "date_created", updatable = false)
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    public UserAuth(final UserAuthDTO userAuthDTO) {
        this.userId = getUserId();
        this.email = userAuthDTO.getEmail();
        this.handle = userAuthDTO.getHandle();
        this.password = userAuthDTO.getPassword();
    }
}
