package portal.ranting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import portal.core.repository.SoftDeletesRepository;
import portal.membership.model.MemberModel;
import portal.ranting.model.RantingModel;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RantingRepository extends SoftDeletesRepository<RantingModel, Long> {
	@Query(value = "SELECT * from ranting order by created_at asc ", nativeQuery = true)
	Iterable<RantingModel> getAll();
}
