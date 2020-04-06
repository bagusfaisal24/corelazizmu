package portal.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import portal.user.model.UserAttemps;
import portal.user.model.UserModel;
import portal.user.repository.UserAttempsRepository;
import portal.user.repository.UserRepository;
import portal.util.TransactionDate;


@Service
public class UserAttempsSvcImp implements UserAttempsSvc {

    private final UserRepository userRepository;
    private final UserAttempsRepository userAttempsRepository;
    private final TransactionDate transactionDate;

    @Autowired
    public UserAttempsSvcImp(UserRepository userRepository, UserAttempsRepository userAttempsRepository,
                             TransactionDate transactionDate) {
        this.userRepository = userRepository;
        this.userAttempsRepository = userAttempsRepository;
        this.transactionDate = transactionDate;
    }

    @Override
    public UserAttemps getUserAttemps(String username) {
        return userAttempsRepository.findOneByUsername(username);
    }

    @Override
    public void updateLocked(String username) {
        UserModel user = userRepository.findOneByUsername(username);
        user.setAccountNonLocked(false);
        userRepository.save(user);
    }

    public UserAttemps updateUserAttemps(String username) {
        UserAttemps userAttemps = getUserAttemps(username);
        if (userAttemps == null) {
            userAttemps = new UserAttemps();
            userAttemps.setLastModified(transactionDate.now());
        }
        userAttemps.setUsername(username);
        userAttemps.setAttemps(userAttemps.getAttemps() + 1);
        userAttemps.setLastModified(transactionDate.now());
        return save(userAttemps);
    }

    public UserAttemps save(UserAttemps userAttemps) {
        return userAttempsRepository.save(userAttemps);
    }
}
