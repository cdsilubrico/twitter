package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.*;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserAuthDTO signup(UserAuthDTO userAuthDTO);

    UserAuthDTO getById(Integer userAuthId);

    UserAuthDTO updateUser(UserAuthDTO userAuthDTO);

    void deleteUser(Integer userAuthId);

    UserAuthDTO getByEmailOrHandle(String emailOrHandle);
}
