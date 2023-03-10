package clone.twitter.service.auth;

import clone.twitter.dto.authenticate.AccountDTO;
import clone.twitter.model.auth.Account;
import clone.twitter.repository.auth.AuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private AuthRepository authRepository;

    @Override
    public void signup(final AccountDTO accountDTO){

        logger.info(getClass().getName() + " - " +accountDTO.toString());

        authRepository.save(new Account(accountDTO));

    }

}
