package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.model.auth.UserAuth;
import clone.twitter.repository.auth.UserAuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {
    private AuthService authService;

    @Mock
    private UserAuthRepository mockUserAuthRepository;

    private UserAuth userAuth;

    private UserAuthDTO mockUserAuthDTO;

    private UserAuthDTO expectedUserAuthDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = Mockito.mock(AuthServiceImpl.class);

        mockUserAuthDTO = new UserAuthDTO();
        mockUserAuthDTO.setEmail("Email@gmail.com");
        mockUserAuthDTO.setHandle("@Email");
        mockUserAuthDTO.setAccountId(100L);
        mockUserAuthDTO.setPassword("Password2");

        expectedUserAuthDTO = new UserAuthDTO();
    }

    @Test
    void testSignupMethod() {
        userAuth = new UserAuth(mockUserAuthDTO);

        when(mockUserAuthRepository.save(any(UserAuth.class))).thenReturn(userAuth);

        authService.signup(mockUserAuthDTO);

        verify(authService, atLeastOnce()).signup(mockUserAuthDTO);
    }

    @Test
    void testSuccessGetById() {
        final Long userId = 100L;

        when(authService.getById(userId)).thenReturn(mockUserAuthDTO);

        UserAuthDTO expectedUserAuthDTO = new UserAuthDTO();
        expectedUserAuthDTO.setEmail("Email@gmail.com");
        expectedUserAuthDTO.setHandle("@Email");
        expectedUserAuthDTO.setAccountId(100L);
        expectedUserAuthDTO.setPassword("Password2");

        assertEquals(expectedUserAuthDTO.getAccountId(), authService.getById(userId).getAccountId());
        assertEquals(expectedUserAuthDTO.getHandle(), authService.getById(userId).getHandle());
        assertEquals(expectedUserAuthDTO.getEmail(), authService.getById(userId).getEmail());
        assertEquals(expectedUserAuthDTO.getPassword(), authService.getById(userId).getPassword());

        verify(authService, atLeastOnce()).getById(userId);
    }

    @Test
    void testSuccessGetByEmail() {
        final String email = "Email@gmail.com";
        final String handle = "@Email";

        UserAuthDTO expectedUserAuthDTO = new UserAuthDTO();
        expectedUserAuthDTO.setEmail("Email@gmail.com");
        expectedUserAuthDTO.setHandle("@Email");
        expectedUserAuthDTO.setAccountId(100L);
        expectedUserAuthDTO.setPassword("Password2");

        when(authService.getByEmailOrHandle(email)).thenReturn(mockUserAuthDTO);

        assertEquals(authService.getByEmailOrHandle(email).getEmail(), expectedUserAuthDTO.getEmail());
        assertEquals(authService.getByEmailOrHandle(email).getHandle(), expectedUserAuthDTO.getHandle());
        assertEquals(authService.getByEmailOrHandle(email).getAccountId(), expectedUserAuthDTO.getAccountId());
        assertEquals(authService.getByEmailOrHandle(email).getPassword(), expectedUserAuthDTO.getPassword());

        verify(authService, atLeastOnce()).getByEmailOrHandle(email);

        when(authService.getByEmailOrHandle(handle)).thenReturn(mockUserAuthDTO);

        assertEquals(authService.getByEmailOrHandle(handle).getEmail(), expectedUserAuthDTO.getEmail());
        assertEquals(authService.getByEmailOrHandle(handle).getHandle(), expectedUserAuthDTO.getHandle());
        assertEquals(authService.getByEmailOrHandle(handle).getAccountId(), expectedUserAuthDTO.getAccountId());
        assertEquals(authService.getByEmailOrHandle(handle).getPassword(), expectedUserAuthDTO.getPassword());

        verify(authService, atLeastOnce()).getByEmailOrHandle(handle);
    }

    @Test
    void testSuccessDeleteUser() {
        final Long userId = 100L;

        authService.deleteUser(userId);

        verify(authService, atLeastOnce()).deleteUser(userId);
    }
}