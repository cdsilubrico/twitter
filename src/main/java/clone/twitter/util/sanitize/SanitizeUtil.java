package clone.twitter.util.sanitize;

import clone.twitter.dto.authenticate.AccountDTO;
import org.apache.commons.text.StringEscapeUtils;

public class SanitizeUtil {
    public static AccountDTO sanitizeAccountDto (final AccountDTO accountDTO) {

        final AccountDTO sanitizedAccountDto = new AccountDTO();

        sanitizedAccountDto.setFirstName(sanitizeString(accountDTO.getFirstName()));
        sanitizedAccountDto.setLastName(sanitizeString(accountDTO.getLastName()));
        sanitizedAccountDto.setPassword(sanitizeString(accountDTO.getPassword()));
        sanitizedAccountDto.setHandle(cleanHandle(accountDTO.getPassword()));
        sanitizedAccountDto.setEmail(sanitizeString(accountDTO.getEmail()));

        return sanitizedAccountDto;
    }

     public static String sanitizeString(final String unsanitizedString) {
        return StringEscapeUtils.escapeJava(unsanitizedString);
    }

    private static String cleanHandle(final String handle){
        return handle.replaceAll("@", "");
    }

}
