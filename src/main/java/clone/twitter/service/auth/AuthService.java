package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.*;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AccountDTO getByEmail(String email);

    AccountDTO getByHandle(String handle);

    AccountDTO login(LoginDTO loginDTO);

    void signup(final UserAuthDTO userAuthDTO);

    UserAuthDTO getById(Long id);
}
