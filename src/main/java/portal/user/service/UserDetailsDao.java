package portal.user.service;

import portal.user.model.UserAttemps;

public interface UserDetailsDao {

    void updateFailAttempts(String username);
    void resetFailAttempts(String username);
    UserAttemps getUserAttempts(String username);
}
