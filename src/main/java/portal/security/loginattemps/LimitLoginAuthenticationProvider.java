package portal.security.loginattemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import portal.core.exception.InvalidActionException;
import portal.user.model.UserAttemps;
import portal.user.model.UserModel;
import portal.user.repository.UserRepository;
import portal.user.service.UserAttempsSvc;
import portal.user.service.UserService;
import portal.util.TransactionDate;

import java.util.ArrayList;
import java.util.Date;

@Component
public class LimitLoginAuthenticationProvider implements AuthenticationProvider {

    @Value("${security.max_attemps_login:3}")
    private int attemps;


    private final UserAttempsSvc userAttempsSvc;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TransactionDate transactionDate;

    @Autowired
    public LimitLoginAuthenticationProvider(UserAttempsSvc userAttempsSvc,
                                            @Qualifier("userDetailsService") UserService userService,
                                            UserRepository userRepository,
                                            TransactionDate transactionDate) {
        this.userAttempsSvc = userAttempsSvc;
        this.userService = userService;
        this.userRepository = userRepository;
        this.transactionDate = transactionDate;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserModel user = (UserModel) userService.loadUserByUsername(name);
        if (isValidPassword(user, password)) {
            UserAttemps userAttemps = userAttempsSvc.getUserAttemps(user.getUsername());
            if (userAttemps != null) {
                Date latestModified = new Date(userAttemps.getLastModified().getTime() + (5 * 60 * 1000));
                Date currentDate = new java.util.Date();
                if (currentDate.after(latestModified)) {
                    userAttemps.setDeletedAt(transactionDate.now());
                    user.setAccountNonLocked(true);
                    userAttempsSvc.save(userAttemps);
                    userRepository.save(user);
                } else if (!user.isAccountNonLocked()) {
                    throw new LockedException("Akun anda terkunci silahkan hubungi customer service");
                }
                return new UsernamePasswordAuthenticationToken(
                        name, password, new ArrayList<>());
            }
            if (!user.isEnabled()) {
                throw new LockedException("Akun anda terminated");
            }
            if (!user.isAccountNonLocked()) {
                throw new LockedException("Akun anda terkunci silahkan hubungi customer service");
            }
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } else if (!user.isAccountNonLocked()) {
            throw new LockedException("anda melakukan kesalahan sebanyak " + attemps + "x akun telah terkunci");
        } else {
            UserAttemps userAttemps = userAttempsSvc.updateUserAttemps(name);
            if (userAttemps.getAttemps() == attemps) {
                userAttempsSvc.updateLocked(name);
            }
            throw new BadCredentialsException("username atau kata sandi tidak sesuai");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean isValidPassword(UserModel user, String password) {
        return BCrypt.checkpw(password, user.getPassword());
    }
}
