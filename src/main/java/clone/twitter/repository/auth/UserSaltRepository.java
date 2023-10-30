package clone.twitter.repository.auth;

import clone.twitter.model.auth.password.secret.Salt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSaltRepository extends JpaRepository <Salt, Integer> {
}
