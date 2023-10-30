package clone.twitter.model.auth.password.secret;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_password_secret")
public class PasswordSecret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_password_secret_id")
    private Integer userPasswordSecretId;
    @Column(name = "salt_id")
    private Integer saltId;
    @Column(name = "hash_id")
    private Integer hashId;
}
