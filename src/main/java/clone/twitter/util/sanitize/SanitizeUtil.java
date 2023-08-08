package clone.twitter.util.sanitize;

import clone.twitter.dto.authenticate.AccountDTO;
import clone.twitter.dto.authenticate.LoginDTO;
import clone.twitter.dto.authenticate.UserAuthDTO;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.Optional;

import static clone.twitter.constant.RegularExpressionConstants.NUMBERS_ONLY;

public class SanitizeUtil {

    private SanitizeUtil() {
    }

    public static AccountDTO sanitizeAccountDto(final AccountDTO accountDTO) {

        final AccountDTO sanitizedAccountDto = new AccountDTO();

        sanitizedAccountDto.setFirstName(sanitizeString(accountDTO.getFirstName()));
        sanitizedAccountDto.setLastName(sanitizeString(accountDTO.getLastName()));
        sanitizedAccountDto.setPassword(sanitizeString(accountDTO.getPassword()));
        sanitizedAccountDto.setHandle(cleanHandle(accountDTO.getHandle()));
        sanitizedAccountDto.setEmail(sanitizeString(accountDTO.getEmail()));

        return sanitizedAccountDto;
    }

    public static UserAuthDTO sanitizeUserAuthDto(final UserAuthDTO userAuthDTO) {
        final UserAuthDTO sanitizedUserAuthDTO = new UserAuthDTO();

        Optional<Long> idOpt = Optional.ofNullable(userAuthDTO.getAccountId());
        Optional<String> handleOpt = Optional.ofNullable(userAuthDTO.getHandle());
        Optional<String> emailOpt = Optional.ofNullable(userAuthDTO.getEmail());
        Optional<String> passwordOpt = Optional.ofNullable(userAuthDTO.getPassword());

        idOpt.ifPresent(sanitizedUserAuthDTO::setAccountId);
        handleOpt.ifPresent(handle -> sanitizedUserAuthDTO.setHandle(sanitizeString(handle)));
        emailOpt.ifPresent(email -> sanitizedUserAuthDTO.setEmail(sanitizeString(email)));
        passwordOpt.ifPresent(password -> sanitizedUserAuthDTO.setPassword(sanitizeString(password)));

        return sanitizedUserAuthDTO;
    }

    public static LoginDTO sanitizeLoginDto(final LoginDTO loginDTO) {
        final LoginDTO sanitizedLoginDto = new LoginDTO();

        sanitizedLoginDto.setEmailOrHandle(sanitizeString(loginDTO.getEmailOrHandle()));
        sanitizedLoginDto.setPassword(sanitizeString(loginDTO.getPassword()));

        return sanitizedLoginDto;
    }

    public static String acceptNumbersOnly(final String userAuthId) {
        return sanitizeString(userAuthId).replaceAll(NUMBERS_ONLY, "");
    }

    private static String removeSpecialChars(final String string) {
        return StringEscapeUtils.escapeJava(string);
    }

    private static String removeHtmlSpecialChars(final String emailOrHandle) {
        return HtmlUtils.htmlEscape(emailOrHandle);
    }

    public static String sanitizeString(final String unsanitizedString) {
        return removeHtmlSpecialChars(removeSpecialChars(unsanitizedString));
    }

    private static String cleanHandle(final String handle) {
        return sanitizeString("@" + handle.replace("@", ""));
    }

}
