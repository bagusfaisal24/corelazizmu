package portal.security.loginattemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import portal.core.exception.InvalidActionException;
import portal.user.model.UserAttemps;
import portal.user.model.UserModel;
import portal.user.service.UserAttempsSvc;
import portal.user.service.UserService;

import java.util.ArrayList;

@Component
public class LimitLoginAuthenticationProvider implements AuthenticationProvider {

    @Value("${security.max_attemps_login:3}")
    private int attemps;


    private final UserAttempsSvc userAttempsSvc;
    private final UserService userService;

    @Autowired
    public LimitLoginAuthenticationProvider(UserAttempsSvc userAttempsSvc,
                                            @Qualifier("userDetailsService") UserService userService) {
        this.userAttempsSvc = userAttempsSvc;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserModel user = (UserModel) userService.loadUserByUsername(name);
        if (isValidUpdatePassword(user, password)) {
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } else if (!user.isAccountNonLocked()) {
            throw new InvalidActionException("anda melakukan kesalahan sebanyak " + attemps + "x akun telah terkunci");
        } else {
            UserAttemps userAttemps = userAttempsSvc.updateUserAttemps(name);
            if (userAttemps.getAttemps() == attemps) {
                userAttempsSvc.updateLocked(name);
            }
            throw new InvalidActionException("username atau kata sandi tidak sesuai");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean isValidUpdatePassword(UserModel user, String password) {
        return BCrypt.checkpw(password, user.getPassword());
    }
}
