package clone.twitter.repository.auth;

import clone.twitter.model.auth.UserAuth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserAuthRepositoryTest {

    private UserAuthRepository userAuthRepository;
    private UserAuth mockUserAuth;

    @BeforeEach
    void setup() {
        userAuthRepository = mock(UserAuthRepository.class);
        mockUserAuth = mock(UserAuth.class);
    }

    @Test
    void findFirstByEmailOrHandle() {
        when(userAuthRepository.findFirstByEmailOrHandle(
                anyString(), anyString())).thenReturn(Optional.ofNullable(mockUserAuth));

        userAuthRepository.findFirstByEmailOrHandle(anyString(), anyString());

        verify(userAuthRepository, atLeastOnce()).findFirstByEmailOrHandle(anyString(), anyString());
    }

    @Test
    void findByEmail() {
        when(userAuthRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(mockUserAuth));

        userAuthRepository.findByEmail(anyString());

        verify(userAuthRepository, atLeastOnce()).findByEmail(anyString());
    }

    @Test
    void findByHandle() {
        when(userAuthRepository.findByHandle(anyString())).thenReturn(Optional.ofNullable(mockUserAuth));

        userAuthRepository.findByHandle(anyString());

        verify(userAuthRepository, atLeastOnce()).findByHandle(anyString());
    }

    @Test
    void saveUserAUth() {
        when(userAuthRepository.save(mockUserAuth)).thenReturn(mockUserAuth);

        userAuthRepository.save(mockUserAuth);

        verify(userAuthRepository, atLeastOnce()).save(mockUserAuth);
    }

    @Test
    void deleteUserAuth() {
        userAuthRepository.delete(mockUserAuth);

        verify(userAuthRepository, atLeastOnce()).delete(mockUserAuth);
    }
}