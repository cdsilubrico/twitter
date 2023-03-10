package clone.twitter.repository.auth;

import clone.twitter.model.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Account, Long> {
}
