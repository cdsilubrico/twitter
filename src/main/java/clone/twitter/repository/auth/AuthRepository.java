package clone.twitter.repository.auth;

import clone.twitter.model.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}
