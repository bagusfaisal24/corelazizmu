package portal.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
@NoRepositoryBean
public interface SoftDeletesRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    @Override
    Iterable<T> findAll();

    @Override
    Iterable<T> findAll(Sort sort);

    @Override
    Page<T> findAll(Pageable page);

    @Override
    Iterable<T> findAll(Iterable<ID> ids);

    T findOne(ID id);

    @Modifying
    void delete(ID id);

    @SuppressWarnings("unused")
    void deleteAll(Iterable<ID> ids);

    void hardDelete(ID id);

    void hardDeleteAll();

}
