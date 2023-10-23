package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.exception.specific.DuplicateEntry;
import clone.twitter.exception.specific.NoRecordFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {
    private AuthService authService;

    private UserAuthDTO mockUserAuthDTO;

    @BeforeEach
    public void setUp() {

        authService = Mockito.mock(AuthService.class);

        mockUserAuthDTO = new UserAuthDTO();
        mockUserAuthDTO.setEmail("Email@gmail.com");
        mockUserAuthDTO.setHandle("@Email");
        mockUserAuthDTO.setAccountId(100);
        mockUserAuthDTO.setPassword("Password2");
    }

    @Test
    void SuccessSignupMethod() {
        when(authService.signup(mockUserAuthDTO)).thenReturn(mockUserAuthDTO);

        authService.signup(mockUserAuthDTO);

        verify(authService, atLeastOnce()).signup(mockUserAuthDTO);
    }

    @Test
    void GivenUserAuthIdExistWhenSigningUpThenThrowDuplicateEntryException() {
        when(authService.signup(mockUserAuthDTO)).thenThrow(DuplicateEntry.class);

        assertThrows(DuplicateEntry.class, () -> authService.signup(mockUserAuthDTO));
    }

    @Test
    void SuccessGetById() {
        when(authService.getById(anyInt())).thenReturn(mockUserAuthDTO);

        assertNotNull(authService.getById(anyInt()));

        verify(authService, atLeastOnce()).getById(anyInt());
    }

    @Test
    void GivenThatUserAuthIdDoesNotExistThenThrowNoRecordException() {
        when(authService.getById(anyInt())).thenThrow(NoRecordFound.class);

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
    void GivenThatEmailOrHandleDoesNotExistThenThrowNoRecordFoundException() {
        when(authService.getByEmailOrHandle(anyString())).thenThrow(NoRecordFound.class);

        assertThrows(NoRecordFound.class, this::authServiceGetByEmailOrHandle);
    }

    @Test
    void SuccessDeleteUser() {
        final Integer userId = 100;

        authService.deleteUser(userId);

        verify(authService).deleteUser(userId);
    }

    @Test
    void GivenThatUserIdDoesNotExistWhenDeletingUserAuthThenThrowNoRecordFoundException() {
        doNothing().when(authService).deleteUser(anyInt());

        doThrow(NoRecordFound.class).when(authService).deleteUser(anyInt());

        assertThrows(NoRecordFound.class, this::authServiceDeleteById);
    }

    private void authServiceGetById() {
        authService.getById(anyInt());
    }

    private void authServiceGetByEmailOrHandle() {
        authService.getByEmailOrHandle(anyString());
    }

    private void authServiceDeleteById() {
        authService.deleteUser(anyInt());
    }
}