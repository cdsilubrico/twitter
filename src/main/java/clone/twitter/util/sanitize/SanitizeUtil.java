package clone.twitter.util.sanitize;

import clone.twitter.dto.authenticate.AccountDTO;
import org.apache.commons.text.StringEscapeUtils;

public class SanitizeUtil {
    public static AccountDTO sanitizeAccountDto (final AccountDTO accountDTO) {

        return AccountDTO.builder()
                .firstName(sanitizedString(accountDTO.getFirstName()))
                .lastName(sanitizedString(accountDTO.getLastName()))
                .password(accountDTO.getPassword())
                .handle(cleanHandle(sanitizedString(accountDTO.getHandle())))
                .email(sanitizedString(accountDTO.getEmail()))
                .build();
    }

     private static String sanitizedString(final String unsanitizedString) {
        return StringEscapeUtils.escapeJava(unsanitizedString);
    }

    private static String cleanHandle(final String handle){
        return handle.replaceAll("@", "");
    }

}
