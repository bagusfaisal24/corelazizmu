package portal.zakat.collection.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portal.core.repository.SoftDeletesRepository;
import portal.zakat.collection.model.ZakatModel;

@Repository
@Transactional
public interface ZakatRepositories extends SoftDeletesRepository<ZakatModel, Long> {
}
