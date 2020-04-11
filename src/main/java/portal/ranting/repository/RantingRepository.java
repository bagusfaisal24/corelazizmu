package portal.ranting.repository;

import org.springframework.stereotype.Repository;
import portal.core.repository.SoftDeletesRepository;
import portal.ranting.model.RantingModel;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RantingRepository extends SoftDeletesRepository<RantingModel, Long> {
}
