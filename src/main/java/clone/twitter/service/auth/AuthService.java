package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.*;
import clone.twitter.model.auth.UserAuth;
import clone.twitter.model.auth.UserDetails;

public interface AuthService {
    //void signup(final AccountDTO accountDTO);

    AccountDTO getByEmail(String email);

    AccountDTO getByHandle(String handle);

    AccountDTO login(LoginDTO loginDTO);

    void signup(final UserAuthDTO userAuthDTO);
}
