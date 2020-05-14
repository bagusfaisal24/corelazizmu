package portal.master.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portal.core.repository.SoftDeletesRepository;
import portal.master.model.ProductTypeModel;
import portal.master.model.ProgramKerjaModel;

@Repository
@Transactional
public interface ProductTypeRepositories extends SoftDeletesRepository<ProductTypeModel, Long> {
}
