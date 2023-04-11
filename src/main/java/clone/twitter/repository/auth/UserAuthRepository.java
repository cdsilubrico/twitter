package clone.twitter.repository.auth;

import clone.twitter.model.auth.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByEmailOrHandle(String email, String handle);
}
