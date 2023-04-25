package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.*;

public interface AuthService {
    void signup(final UserAuthDTO userAuthDTO);

    UserAuthDTO getById(Long id);

    UserAuthDTO updateUser(UserAuthDTO userAuthDTO);

    void deleteUser(Long id);

    UserAuthDTO getByEmailOrHandle(String emailOrHandle);
}
