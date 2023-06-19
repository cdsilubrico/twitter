package clone.twitter.controller.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.service.auth.AuthService;
import clone.twitter.util.sanitize.SanitizeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAuthDTO> updateUser(@RequestBody @Valid final UserAuthDTO userAuthDTO) {
        return ResponseEntity.ok(authService.updateUser(userAuthDTO));
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UserAuthDTO> signup(@RequestBody @Valid final UserAuthDTO userAuthDTO) {
        authService.signup(SanitizeUtil.sanitizeUserAuthDto(userAuthDTO));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserAuthDTO> getUserById(@PathVariable final String id) {
        return ResponseEntity.ok(authService.getById(SanitizeUtil.acceptNumbersOnly(id)));
    }

    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@PathVariable final String id) {
        authService.deleteUser(SanitizeUtil.acceptNumbersOnly(id));
    }

    @GetMapping(value = "/users/handleOrEmail/{handleOrEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAuthDTO> getByEmailOrHandle(@PathVariable final String handleOrEmail) {
        return ResponseEntity.ok(authService.getByEmailOrHandle(handleOrEmail));
    }
}
