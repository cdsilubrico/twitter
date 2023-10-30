package clone.twitter.service.auth.password;

import clone.twitter.model.auth.password.Password;

public interface PasswordService {
    Password encrypt(String password);
}
