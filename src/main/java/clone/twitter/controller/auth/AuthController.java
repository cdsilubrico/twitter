package clone.twitter.controller.auth;

import clone.twitter.dto.authenticate.AccountDTO;
import clone.twitter.service.auth.AuthService;
import clone.twitter.util.sanitize.SanitizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void signup(@RequestBody @Valid final AccountDTO accountDTO) {
        authService.signup(accountDTO);
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<AccountDTO> getByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(authService.getByEmail(email), HttpStatus.OK);
    }

}
