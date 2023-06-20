package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.exception.specific.DuplicateEntry;
import clone.twitter.exception.specific.NoRecordFound;
import clone.twitter.model.auth.UserAuth;
import clone.twitter.repository.auth.UserAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.DUPLICATE_USERNAME_OR_EMAIL;
import static clone.twitter.constant.ExceptionConstants.DatabaseExceptionConstants.NO_SUCH_RECORD_FOUND;
import static clone.twitter.constant.LoggerConstants.*;
import static clone.twitter.util.LoggerUtil.logInfoUtil;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    final UserAuthRepository userAuthRepository;

    public AuthServiceImpl(final UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

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
    public UserAuthDTO getById(final String userAuthId) {

        logInfoUtil(log, START_GET_BY_ID);

        final Optional<Long> idInLongOpt = Optional.of(userAuthId)
                .map(Long::parseLong);

        return new UserAuthDTO(userAuthRepository.findById(idInLongOpt.get()).orElseThrow(
                () -> new NoRecordFound(NO_SUCH_RECORD_FOUND)));
    }

    @Override
    public UserAuthDTO updateUser(final UserAuthDTO newUserAuthDTO) {

        logInfoUtil(log, START_UPDATE_USER_AUTH);

        UserAuth currentUserAuth = userAuthRepository.findById(newUserAuthDTO.getAccountId())
                .orElseThrow(() -> new NoRecordFound(NO_SUCH_RECORD_FOUND));

        Optional<String> emailOpt = Optional.ofNullable(newUserAuthDTO.getEmail());
        emailOpt.ifPresent(email -> userAuthRepository.findByEmail(email)
                .ifPresentOrElse(value -> {
                    throw new DuplicateEntry(DUPLICATE_USERNAME_OR_EMAIL);
                }, () -> currentUserAuth.setEmail(email)));

        Optional<String> handleOpt = Optional.ofNullable(newUserAuthDTO.getHandle());
        handleOpt.ifPresent(handle -> userAuthRepository.findByHandle(handle)
                .ifPresentOrElse(value -> {
                    throw  new DuplicateEntry(DUPLICATE_USERNAME_OR_EMAIL);
                }, () -> currentUserAuth.setHandle(handle)));

        return new UserAuthDTO(userAuthRepository.save(currentUserAuth));
    }

    @Override
    public void deleteUser(final String id) {

        logInfoUtil(log, START_DELETE_USER_AUTH_BY_ID);

        final Optional<Long> idInLongOpt = Optional.of(id)
                .map(Long::parseLong);

        idInLongOpt.ifPresent(idInLong -> userAuthRepository.findById(idInLong).ifPresentOrElse(
                user -> userAuthRepository.deleteById(idInLong),
                () -> {
                    throw new NoRecordFound(NO_SUCH_RECORD_FOUND);
                }
        ));

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
