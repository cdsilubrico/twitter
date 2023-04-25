package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.exception.specificException.DuplicateEntry;
import clone.twitter.exception.specificException.NoRecordFound;
import clone.twitter.model.auth.UserAuth;
import clone.twitter.repository.auth.AuthRepository;
import clone.twitter.repository.auth.UserAuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.DUPLICATE_USERNAME_OR_EMAIL;
import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.NO_SUCH_RECORD_FOUND;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    AuthRepository authRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public void signup(final UserAuthDTO userAuthDTO) {

        final UserAuth userAuth = new UserAuth(userAuthDTO);

        userAuthRepository.findFirstByEmailOrHandle(userAuth.getEmail(), userAuth.getHandle())
                .ifPresentOrElse(
                        user -> {
                            throw new DuplicateEntry(DUPLICATE_USERNAME_OR_EMAIL);
                        },
                        () -> userAuthRepository.save(userAuth)
                );

    }

    @Override
    public UserAuthDTO getById(final Long id) {
        return new UserAuthDTO(userAuthRepository.findById(id).orElseThrow(() -> new NoRecordFound(NO_SUCH_RECORD_FOUND)));
    }

    @Override
    public UserAuthDTO updateUser(UserAuthDTO userAuthDTO) {

        UserAuth currentUserAuth = userAuthRepository.findById(userAuthDTO.getAccountId())
                .orElseThrow(() -> new NoRecordFound(NO_SUCH_RECORD_FOUND));
        currentUserAuth.setEmail(userAuthDTO.getEmail());
        currentUserAuth.setHandle(userAuthDTO.getHandle());

        return new UserAuthDTO(userAuthRepository.save(currentUserAuth));
    }

    @Override
    public void deleteUser(final Long id) {
        if(userAuthRepository.findById(id).isPresent()) {
            userAuthRepository.deleteById(id);
        }
    }

    @Override
    public UserAuthDTO getByEmailOrHandle(final String emailOrHandle) {

        final Optional<UserAuth> userAuthOptional;

        if(emailOrHandle.startsWith("@")) {
            userAuthOptional = userAuthRepository.findByHandle(emailOrHandle);
        } else {
            userAuthOptional = userAuthRepository.findByEmail(emailOrHandle);
        }

        return userAuthOptional
                .map(UserAuthDTO::new)
                .orElseThrow(() -> new NoRecordFound(NO_SUCH_RECORD_FOUND));

    }
}
