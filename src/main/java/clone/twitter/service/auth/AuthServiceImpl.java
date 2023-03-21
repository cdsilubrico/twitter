package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.AccountDTO;
import clone.twitter.dto.authenticate.LoginDTO;
import clone.twitter.model.auth.Account;
import clone.twitter.repository.auth.AuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private AuthRepository authRepository;

    @Override
    public void signup(final AccountDTO accountDTO) {

        logger.info(getClass().getName() + " - " + accountDTO.toString());

        authRepository.save(new Account(accountDTO));

    }

    @Override
    public AccountDTO getByEmail(String email) {

        logger.info(getClass().getName() + " - " + email);

        return Optional.ofNullable(authRepository.findByEmail(email))
                .map(AccountDTO::new).orElseThrow();
    }

    @Override
    public AccountDTO getByHandle(String handle) {

        logger.info(getClass().getName() + " - " + handle);

        return Optional.ofNullable(authRepository.findByHandle(handle))
                .map(AccountDTO::new).orElseThrow();
    }

    @Override
    public AccountDTO login(final LoginDTO loginDTO) {

        final AccountDTO accountDTO;

        logger.info(getClass().getName() + " - " + loginDTO.toString());

        if (loginDTO.getEmailOrHandle().startsWith("@")) {
            accountDTO = Optional.ofNullable(authRepository.findByHandleAndPassword(loginDTO.getEmailOrHandle(), loginDTO.getPassword()))
                    .map(AccountDTO::new).orElseThrow();
        } else {
            accountDTO = Optional.ofNullable(authRepository.findByEmailAndPassword(loginDTO.getEmailOrHandle(), loginDTO.getPassword()))
                    .map(AccountDTO::new).orElseThrow();
        }

        return accountDTO;
    }

}
