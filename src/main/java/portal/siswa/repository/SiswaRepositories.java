package portal.siswa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portal.core.repository.SoftDeletesRepository;
import portal.siswa.model.SiswaModel;

@Repository
@Transactional
public interface SiswaRepositories extends SoftDeletesRepository<SiswaModel, Long> {

}
