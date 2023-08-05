package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.exception.specific.NoRecordFound;
import clone.twitter.model.auth.UserAuth;
import clone.twitter.repository.auth.UserAuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
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
    void SuccessSignupMethod() {
        userAuth = new UserAuth(mockUserAuthDTO);

        when(authService.signup(mockUserAuthDTO)).thenReturn(mockUserAuthDTO);

        authService.signup(mockUserAuthDTO);

        verify(authService, atLeastOnce()).signup(mockUserAuthDTO);
    }
    @Test
    void SuccessGetById() {
        when(authService.getById(anyLong())).thenReturn(mockUserAuthDTO);

        assertNotNull(authService.getById(anyLong()));

        verify(authService, atLeastOnce()).getById(anyLong());
    }

    @Test
    void GivenThatUserIdDoesNotExistThenThrowNoRecordException() {
        when(authService.getById(anyLong())).thenThrow(NoRecordFound.class);

        assertThrows(NoRecordFound.class, this::authServiceGetById);
    }

    @Test
    void SuccessGetByEmailOrHandle() {
        final String email = "Email@gmail.com";
        final String handle = "@Email";

        UserAuthDTO expectedUserAuthDTO = new UserAuthDTO();
        expectedUserAuthDTO.setEmail("Email@gmail.com");
        expectedUserAuthDTO.setHandle("@Email");

        when(authService.getByEmailOrHandle(email)).thenReturn(mockUserAuthDTO);

        assertEquals(authService.getByEmailOrHandle(email).getEmail(), expectedUserAuthDTO.getEmail());

        verify(authService, atLeastOnce()).getByEmailOrHandle(email);


        when(authService.getByEmailOrHandle(handle)).thenReturn(mockUserAuthDTO);

        assertEquals(authService.getByEmailOrHandle(handle).getHandle(), expectedUserAuthDTO.getHandle());

        verify(authService, atLeastOnce()).getByEmailOrHandle(handle);
    }

    @Test
    void SuccessDeleteUser() {
        final Long userId = 100L;

        authService.deleteUser(userId);

        verify(authService).deleteUser(userId);
    }

    private void authServiceGetById() {
        authService.getById(anyLong());
    }
}