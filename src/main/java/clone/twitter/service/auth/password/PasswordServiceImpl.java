package clone.twitter.service.auth.password;

import clone.twitter.model.auth.password.Password;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Service
public class PasswordServiceImpl implements PasswordService {

    @Override
    public Password encrypt(final String password) {
        final Password passwordSecret = new Password();

        final String salt = Arrays.toString(generateSalt());

        final char[] passwordChars = password.toCharArray();
        final byte[] saltBytes = salt.getBytes();

        final byte[] hashedBytes = hashPassword(passwordChars, saltBytes, passwordSecret);

        passwordSecret.setEncryptedPassword(toHex(hashedBytes));
        passwordSecret.setSaltValue(toHex(saltBytes));

        return passwordSecret;
    }

    private byte[] hashPassword(
            final char[] password,
            final byte[] salt,
            final Password passwordSecret) {

        final int iterations = 10000; //hide
        final int keyLength = 512; //hide

        final SecretKeyFactory secretKeyFactory;
        SecretKey secretKey = null;

        try {
            secretKeyFactory = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );

            final PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength );

            secretKey = secretKeyFactory.generateSecret(spec);

            passwordSecret.setHashValue(toHex(Arrays.toString(secretKey.getEncoded()).getBytes()));

        } catch(Exception e) {
            log.error("An error occurred when hashing the password." + e.getMessage());
        }

        return Objects.requireNonNull(secretKey).getEncoded();
    }

    private String toHex(final byte[] array)
    {
        final BigInteger bi = new BigInteger(1, array);
        final String hex = bi.toString(16);

        try {

            int paddingLength = (array.length * 2) - hex.length();

            if(paddingLength > 0)
            {
                return String.format("%%0%dd".formatted(paddingLength), 0) + hex;
            }

        } catch (Exception e) {
            log.error("An error occurred when converting to String." + e.getMessage());
        }

        return hex;
    }

    private byte[] generateSalt() {
        final byte[] salt = new byte[16];

        try {
            final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.nextBytes(salt);
        } catch (Exception e) {
            log.error("An error occurred when generating salt." + e.getMessage());
        }

        return salt;
    }
}