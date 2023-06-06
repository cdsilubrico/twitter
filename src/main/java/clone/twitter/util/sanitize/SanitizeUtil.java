package clone.twitter.util.sanitize;

import clone.twitter.dto.authenticate.AccountDTO;
import clone.twitter.dto.authenticate.LoginDTO;
import clone.twitter.dto.authenticate.UserAuthDTO;
import org.apache.commons.text.StringEscapeUtils;

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

        sanitizedUserAuthDTO.setHandle(sanitizeString(cleanHandle(userAuthDTO.getHandle())));
        sanitizedUserAuthDTO.setEmail(sanitizeString(userAuthDTO.getEmail()));
        sanitizedUserAuthDTO.setPassword(sanitizeString(userAuthDTO.getPassword()));

        return sanitizedUserAuthDTO;
    }

    public static LoginDTO sanitizeLoginDto(final LoginDTO loginDTO) {
        final LoginDTO sanitizedLoginDto = new LoginDTO();

        sanitizedLoginDto.setEmailOrHandle(sanitizeString(loginDTO.getEmailOrHandle()));
        sanitizedLoginDto.setPassword(sanitizeString(loginDTO.getPassword()));

        return sanitizedLoginDto;
    }

    public static String cleanId(final String userAuthId) {
        return userAuthId.replaceAll(NUMBERS_ONLY, "");
    }

    public static String sanitizeString(final String unsanitizedString) {
        return StringEscapeUtils.escapeJava(unsanitizedString);
    }

    private static String cleanHandle(final String handle) {
        return "@" + handle.replace("@", "");
    }

}
