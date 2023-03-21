package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.*;

public interface AuthService {
    void signup(final AccountDTO accountDTO);

    AccountDTO getByEmail(String email);

    AccountDTO getByHandle(String handle);

    AccountDTO login(LoginDTO loginDTO);
}
