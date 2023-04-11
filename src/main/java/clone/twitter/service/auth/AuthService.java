package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.*;
import clone.twitter.model.auth.UserAuth;

import java.util.Optional;

public interface AuthService {
    //void signup(final AccountDTO accountDTO);

    AccountDTO getByEmail(String email);

    AccountDTO getByHandle(String handle);

    AccountDTO login(LoginDTO loginDTO);

    void signup(final UserAuthDTO userAuthDTO);

    UserAuthDTO getById(Long id);
}
