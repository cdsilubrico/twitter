package clone.twitter.controller.auth;

import clone.twitter.dto.authenticate.UserAuthDTO;
import clone.twitter.service.auth.AuthService;
import clone.twitter.util.sanitize.SanitizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    transient AuthService authService;

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAuthDTO> updateUser(@RequestBody @Valid final UserAuthDTO userAuthDTO) {
        return ResponseEntity.ok(authService.updateUser(userAuthDTO));
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UserAuthDTO> signup(@RequestBody @Valid final UserAuthDTO userAuthDTO) {
        authService.signup(SanitizeUtil.sanitizeUserAuthDto(userAuthDTO));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserAuthDTO> getUser(@PathVariable final Long id) {
        return ResponseEntity.ok(authService.getById(id));
    }

    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@PathVariable final Long id) {
        authService.deleteUser(id);
    }

    @GetMapping(value = "/users/{handleOrEmail}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAuthDTO> getByEmailOrHandle(@PathVariable final String handleOrEmail) {
        return ResponseEntity.ok(authService.getByEmailOrHandle(SanitizeUtil.sanitizeString(handleOrEmail)));
    }
}
