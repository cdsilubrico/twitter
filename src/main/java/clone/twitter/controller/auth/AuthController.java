package clone.twitter.controller.auth;

import clone.twitter.dto.authenticate.AccountDTO;
import clone.twitter.dto.authenticate.LoginDTO;
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
    AuthService authService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> login(@RequestBody final LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(SanitizeUtil.sanitizeLoginDto(loginDTO)));
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> getByEmail(@PathVariable("email") final String email) {
        return ResponseEntity.ok(authService.getByEmail(SanitizeUtil.sanitizeString(email)));
    }

    @GetMapping(value = "/handle/{handle}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> getByHandle(@PathVariable("handle") final String handle) {
        return ResponseEntity.ok(authService.getByHandle(SanitizeUtil.sanitizeString(handle)));
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UserAuthDTO> signup(@RequestBody @Valid final UserAuthDTO userAuthDTO) {
        authService.signup(SanitizeUtil.sanitizeUserAuthDto(userAuthDTO));
        return ResponseEntity.ok().build();
    }

}
