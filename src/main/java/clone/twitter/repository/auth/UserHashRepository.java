package clone.twitter.repository.auth;

import clone.twitter.model.auth.password.secret.Hash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHashRepository extends JpaRepository<Hash, Integer> {
}
