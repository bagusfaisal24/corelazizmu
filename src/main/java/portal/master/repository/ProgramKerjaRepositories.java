package portal.master.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portal.core.repository.SoftDeletesRepository;
import portal.master.model.ProgramKerjaModel;
import portal.membership.model.MemberModel;

@Repository
@Transactional
public interface ProgramKerjaRepositories extends SoftDeletesRepository<ProgramKerjaModel, Long> {
}
