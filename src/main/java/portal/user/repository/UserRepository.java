package portal.user.repository;

import portal.core.repository.SoftDeletesRepository;
import portal.user.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends SoftDeletesRepository<UserModel, Long> {

    UserModel findOneByUsername(String username);

    @Query(value = "SELECT * FROM app_user WHERE deleted_at IS NULL AND user_role = ?1", nativeQuery = true)
    List<UserModel> findAllByUserRole(String roleInvestment);

}
