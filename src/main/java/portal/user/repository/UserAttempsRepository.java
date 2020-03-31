package portal.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import portal.core.repository.SoftDeletesRepository;
import portal.user.model.UserAttemps;

@Repository
public interface UserAttempsRepository extends SoftDeletesRepository<UserAttemps, Long> {

    @Query(value = "SELECT * FROM user_attemps WHERE deleted_at IS NULL AND username = ?1", nativeQuery = true)
    UserAttemps findOneByUsername(String username);
}
