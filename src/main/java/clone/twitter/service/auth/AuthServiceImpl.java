package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.exception.specific.DuplicateEntry;
import clone.twitter.exception.specific.NoRecordFound;
import clone.twitter.model.auth.UserAuth;
import clone.twitter.dto.authenticate.PasswordDTO;
import clone.twitter.model.auth.password.secret.Hash;
import clone.twitter.model.auth.password.secret.Salt;
import clone.twitter.repository.auth.UserAuthRepository;
import clone.twitter.repository.auth.UserHashRepository;
import clone.twitter.repository.auth.UserSaltRepository;
import clone.twitter.service.auth.password.PasswordService;
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
    final UserSaltRepository userSaltRepository;
    final UserHashRepository userHashRepository;
    final PasswordService passwordService;

    public AuthServiceImpl(
            final UserAuthRepository userAuthRepository,
            final UserSaltRepository userSaltRepository,
            final UserHashRepository userHashRepository,
            final PasswordService passwordService) {
        this.userAuthRepository = userAuthRepository;
        this.userSaltRepository = userSaltRepository;
        this.userHashRepository = userHashRepository;
        this.passwordService = passwordService;
    }

    @Override
    public UserAuthDTO signup(final UserAuthDTO userAuthDTO) {

        logInfoUtil(log, START_SIGN_UP);

        userAuthRepository.findFirstByEmailOrHandle(userAuthDTO.getEmail(), userAuthDTO.getHandle())
                .ifPresent(userAuth -> {
                    throw new DuplicateEntry(DUPLICATE_USERNAME_OR_EMAIL);
                });

        final PasswordDTO passwordDTOSecret = passwordService.encrypt(userAuthDTO.getPassword());

        userAuthDTO.setPassword(passwordDTOSecret.getEncryptedPassword());

        UserAuth userAuth = userAuthRepository.save(new UserAuth(userAuthDTO));

        createPasswordSecret(passwordDTOSecret.getSalt(), passwordDTOSecret.getHash(), userAuth);

        return new UserAuthDTO(userAuth);
    }

    @Override
    public UserAuthDTO getById(final Integer userAuthId) {

        logInfoUtil(log, START_GET_BY_ID);

        return new UserAuthDTO(userAuthRepository.findById(userAuthId)
                .orElseThrow(() -> new NoRecordFound(NO_SUCH_RECORD_FOUND)));
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
                    throw new DuplicateEntry(DUPLICATE_USERNAME_OR_EMAIL);
                }, () -> currentUserAuth.setHandle(handle)));

        return new UserAuthDTO(userAuthRepository.save(currentUserAuth));
    }

    @Override
    public void deleteUser(final Integer id) {

        logInfoUtil(log, START_DELETE_USER_AUTH_BY_ID);

        userAuthRepository.findById(id)
                .ifPresentOrElse(userAuthRepository::delete,
                        () -> {
                            throw new NoRecordFound(NO_SUCH_RECORD_FOUND);
                        });
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

    private void createPasswordSecret(final String saltValue, final String hashValue, final UserAuth userAuth) {
        userSaltRepository.save(createSalt(saltValue, userAuth));
        userHashRepository.save(createHash(hashValue, userAuth));
    }

    private Salt createSalt(final String saltValue, final UserAuth userAuth) {
        Salt salt = new Salt();
        salt.setUserId(userAuth);
        salt.setSaltValue(saltValue);
        return salt;
    }

    private Hash createHash(final String hashValue, final UserAuth userAuth) {
        Hash hash = new Hash();
        hash.setUserId(userAuth);
        hash.setHashValue(hashValue);
        return hash;
    }
}