package clone.twitter.repository.auth;

import clone.twitter.model.auth.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    Optional<UserAuth> findFirstByEmailOrHandle(String email, String handle);
    Optional<UserAuth> findByEmail(String email);
    Optional<UserAuth> findByHandle(String handle);
}