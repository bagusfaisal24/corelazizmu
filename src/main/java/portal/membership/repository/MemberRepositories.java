package portal.membership.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portal.core.repository.SoftDeletesRepository;
import portal.membership.model.MemberModel;

@Repository
@Transactional
public interface MemberRepositories extends SoftDeletesRepository<MemberModel, Long> {

    @Query(value = "SELECT * from member where deleted_at is null order by created_at asc", nativeQuery = true)
    Iterable<MemberModel> getAll();
}
