package portal.user.service;

import portal.user.model.UserAttemps;

public interface UserAttempsSvc {

    void updateLocked(String username);

    UserAttemps updateUserAttemps(String username);

    UserAttemps getUserAttemps(String username);

    UserAttemps save(UserAttemps userAttemps);

}
