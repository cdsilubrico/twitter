package clone.twitter.model.auth.password.secret;

import clone.twitter.model.auth.UserAuth;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_salt")
public class Salt {
    @Id
    @Column(name = "salt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saltId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserAuth userId;
    @Column(name = "salt")
    private String saltValue;
}
