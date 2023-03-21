package clone.twitter.service.auth;

//import clone.twitter.dto.auth.LoginDTO;

import clone.twitter.dto.authenticate.*;
import clone.twitter.model.auth.Account;

import java.util.Optional;

public interface AuthService {
    void signup(final AccountDTO accountDTO);

    AccountDTO getByEmail(String email);

    AccountDTO getByHandle(String handle);

    AccountDTO login(LoginDTO loginDTO);

//    LoginDTO login(LoginDTO loginDTO) throws Exception;
//
//    boolean validateEmail(String email);
//
//    boolean validateHandle(String handle);
}
