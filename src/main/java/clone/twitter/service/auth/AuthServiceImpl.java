package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.exception.specificException.DuplicateEntry;
import clone.twitter.exception.specificException.NoRecordFound;
import clone.twitter.model.auth.UserAuth;
import clone.twitter.repository.auth.UserAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.DUPLICATE_USERNAME_OR_EMAIL;
import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.NO_SUCH_RECORD_FOUND;
import static clone.twitter.constant.LoggerConstants.*;
import static clone.twitter.util.LoggerUtil.logInfoUtil;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    transient UserAuthRepository userAuthRepository;

    @Override
    public void signup(final UserAuthDTO userAuthDTO) {

        logInfoUtil(log, START_SIGN_UP);

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

        logInfoUtil(log, START_GET_BY_ID);

        return new UserAuthDTO(userAuthRepository.findById(id).orElseThrow(
                () -> new NoRecordFound(NO_SUCH_RECORD_FOUND)));
    }

    @Override
    public UserAuthDTO updateUser(UserAuthDTO userAuthDTO) {

        logInfoUtil(log, START_UPDATE_USER_AUTH);

        UserAuth currentUserAuth = userAuthRepository.findById(userAuthDTO.getAccountId())
                .orElseThrow(() -> new NoRecordFound(NO_SUCH_RECORD_FOUND));
        currentUserAuth.setEmail(userAuthDTO.getEmail());
        currentUserAuth.setHandle(userAuthDTO.getHandle());

        return new UserAuthDTO(userAuthRepository.save(currentUserAuth));
    }

    @Override
    public void deleteUser(final Long id) {

        logInfoUtil(log, START_DELETE_USER_AUTH_BY_ID);

        userAuthRepository.findById(id).ifPresentOrElse(
                user -> userAuthRepository.deleteById(id),
                () -> {
                    throw new NoRecordFound(NO_SUCH_RECORD_FOUND);
                }
        );
    }

    @Override
    public UserAuthDTO getByEmailOrHandle(final String emailOrHandle) {

        logInfoUtil(log, START_GET_USER_AUTH_BY_EMAIL_OR_HANDLE);

        final Optional<UserAuth> userAuthOptional;

        if (emailOrHandle.startsWith("@")) {
            userAuthOptional = userAuthRepository.findByHandle(emailOrHandle);
        } else {
            userAuthOptional = userAuthRepository.findByEmail(emailOrHandle);
        }

        return userAuthOptional
                .map(UserAuthDTO::new)
                .orElseThrow(() -> new NoRecordFound(NO_SUCH_RECORD_FOUND));

    }
}
