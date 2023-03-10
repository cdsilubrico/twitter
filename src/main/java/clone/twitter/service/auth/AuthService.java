package clone.twitter.service.auth;

//import clone.twitter.dto.auth.LoginDTO;

import clone.twitter.dto.authenticate.*;

public interface AuthService {
    void signup(final AccountDTO accountDTO);

//    LoginDTO login(LoginDTO loginDTO) throws Exception;
//
//    boolean validateEmail(String email);
//
//    boolean validateHandle(String handle);
}
