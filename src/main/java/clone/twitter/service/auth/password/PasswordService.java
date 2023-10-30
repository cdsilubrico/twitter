package clone.twitter.service.auth.password;

import clone.twitter.dto.authenticate.PasswordDTO;

public interface PasswordService {
    PasswordDTO encrypt(String password);
}
