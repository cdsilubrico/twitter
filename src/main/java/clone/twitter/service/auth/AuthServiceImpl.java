package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.AccountDTO;
import clone.twitter.dto.authenticate.LoginDTO;
import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.exception.defaultHandler.DatabaseHandler;
import clone.twitter.model.auth.UserAuth;
import clone.twitter.repository.auth.AuthRepository;
import clone.twitter.repository.auth.UserAuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.USERNAME_OR_HANDLE_NOT_FOUND;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    AuthRepository authRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public AccountDTO getByEmail(final String email) {

        LOGGER.info(getClass().getName() + " - " + email);

        return Optional.ofNullable(authRepository.findByEmail(email))
                .map(AccountDTO::new).orElseThrow();
    }

    @Override
    public AccountDTO getByHandle(final String handle) {

        LOGGER.info(getClass().getName() + " - " + handle);

        return Optional.ofNullable(authRepository.findByHandle(handle))
                .map(AccountDTO::new).orElseThrow();
    }

    @Override
    public AccountDTO login(final LoginDTO loginDTO) {

        final AccountDTO accountDTO;

        LOGGER.info(getClass().getName() + " - " + loginDTO.toString());

        if (loginDTO.getEmailOrHandle().startsWith("@")) {
            accountDTO = Optional.ofNullable(authRepository.findByHandleAndPassword(loginDTO.getEmailOrHandle(), loginDTO.getPassword()))
                    .map(AccountDTO::new).orElseThrow();
        } else {
            accountDTO = Optional.ofNullable(authRepository.findByEmailAndPassword(loginDTO.getEmailOrHandle(), loginDTO.getPassword()))
                    .map(AccountDTO::new).orElseThrow();
        }

        return accountDTO;
    }

    @Override
    public void signup(final UserAuthDTO userAuthDTO) {

        final UserAuth userAuth = new UserAuth(userAuthDTO);

        userAuthRepository.findByEmailOrHandle(userAuth.getEmail(), userAuth.getHandle())
                .ifPresentOrElse(
                        user -> {
                            throw new DatabaseHandler(USERNAME_OR_HANDLE_NOT_FOUND);
                        },
                        () -> userAuthRepository.save(userAuth)
                );

    }

    @Override
    public UserAuthDTO getById(final Long id) {
        return new UserAuthDTO(userAuthRepository.findById(id).orElseThrow());
    }

    @Override
    public UserAuthDTO updateUser(UserAuthDTO userAuthDTO) {

        Optional<UserAuth> userAuthOptional = userAuthRepository.findById(userAuthDTO.getAccountId());

        if(userAuthOptional.isPresent()) {
            UserAuth currentUserAuth = userAuthOptional.get();
            currentUserAuth.setEmail(userAuthDTO.getEmail());
            currentUserAuth.setHandle(userAuthDTO.getHandle());
            currentUserAuth = userAuthRepository.save(currentUserAuth);
            return new UserAuthDTO(currentUserAuth);
        } else {
            throw new DatabaseHandler(USERNAME_OR_HANDLE_NOT_FOUND);
        }
    }

}
