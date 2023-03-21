package clone.twitter.util.sanitize;

import clone.twitter.dto.authenticate.AccountDTO;
import clone.twitter.dto.authenticate.LoginDTO;
import org.apache.commons.text.StringEscapeUtils;

public class SanitizeUtil {
    public static AccountDTO sanitizeAccountDto(final AccountDTO accountDTO) {

        final AccountDTO sanitizedAccountDto = new AccountDTO();

        sanitizedAccountDto.setFirstName(sanitizeString(accountDTO.getFirstName()));
        sanitizedAccountDto.setLastName(sanitizeString(accountDTO.getLastName()));
        sanitizedAccountDto.setPassword(sanitizeString(accountDTO.getPassword()));
        sanitizedAccountDto.setHandle(cleanHandle(accountDTO.getHandle()));
        sanitizedAccountDto.setEmail(sanitizeString(accountDTO.getEmail()));

        return sanitizedAccountDto;
    }

    public static LoginDTO sanitizeLoginDto(final LoginDTO loginDTO) {
        final LoginDTO sanitizedLoginDto = new LoginDTO();

        sanitizedLoginDto.setEmailOrHandle(sanitizeString(loginDTO.getEmailOrHandle()));
        sanitizedLoginDto.setPassword(sanitizeString(loginDTO.getPassword()));

        return sanitizedLoginDto;
    }

    public static String sanitizeString(final String unsanitizedString) {
        return StringEscapeUtils.escapeJava(unsanitizedString);
    }

    private static String cleanHandle(final String handle) {
        return handle.replaceAll("@", "");
    }

}
