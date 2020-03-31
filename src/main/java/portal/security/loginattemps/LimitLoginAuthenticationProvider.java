package portal.security.loginattemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import portal.core.exception.InvalidActionException;
import portal.user.model.UserAttemps;
import portal.user.service.UserAttempsSvc;

@Component
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

    @Value("${security.max_attemps_login:3}")
    private int attemps;

    private final UserAttempsSvc userAttempsSvc;

    @Autowired
    @Qualifier("userDetailsService")
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Autowired
    public LimitLoginAuthenticationProvider(UserAttempsSvc userAttempsSvc) {
        this.userAttempsSvc = userAttempsSvc;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        try {
            return super.authenticate(authentication);
        } catch (BadCredentialsException e) {

            UserAttemps userAttemps = userAttempsSvc.updateUserAttemps(authentication.getName());
            if (userAttemps.getAttemps() >= attemps) {
                userAttempsSvc.updateLocked(userAttemps.getUsername());
            }
            throw new InvalidActionException("username atau password salah");
        } catch (LockedException e) {
            throw new InvalidActionException("akun telah terkunci");
        }
    }
}
