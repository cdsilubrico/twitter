package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.*;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    void signup(final UserAuthDTO userAuthDTO);

    UserAuthDTO getById(Long userAuthId);

    UserAuthDTO updateUser(UserAuthDTO userAuthDTO);

    void deleteUser(Long userAuthId);

    UserAuthDTO getByEmailOrHandle(String emailOrHandle);
}
