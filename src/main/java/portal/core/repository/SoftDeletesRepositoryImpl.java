package portal.core.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class SoftDeletesRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements SoftDeletesRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    private final Class<T> domainClass;
    private static final String DELETED_FIELD = "deletedAt";

    public SoftDeletesRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.domainClass = domainClass;
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, em);
    }

    @Override
    public List<T> findAll() {
        return super.findAll(notDeleted());
    }

    @Override
    public List<T> findAll(Sort sort) {
        return super.findAll(notDeleted(), sort);
    }

    @Override
    public Page<T> findAll(Pageable page) {
        return super.findAll(notDeleted(), page);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        softDelete(id, LocalDateTime.now());
    }

    @SuppressWarnings("unused")
    @Override
    @Transactional
    public void deleteAll(Iterable<ID> ids) {
        for (ID id : ids) {
            Assert.notNull(id, "The given id must not be null!");
            softDelete(id, LocalDateTime.now());
        }
    }

    @Override
    public void hardDelete(ID id) {
        super.delete(id);
    }

    @Override
    public void hardDeleteAll() {
        super.deleteAll();
    }

    private void softDelete(ID id, LocalDateTime localDateTime) {
        Assert.notNull(id, "The given id must not be null!");

        T entity = findOne(id);

        if (entity == null)
            throw new EmptyResultDataAccessException(
                    String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id), 1);

        softDelete(entity, localDateTime);
    }

    private void softDelete(T entity, LocalDateTime localDateTime) {
        Assert.notNull(entity, "The entity must not be null!");

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaUpdate<T> update = cb.createCriteriaUpdate(domainClass);

        Root<T> root = update.from(domainClass);

        update.set(DELETED_FIELD, Timestamp.valueOf(localDateTime));

        update.where(
                cb.equal(root.<ID>get(entityInformation.getIdAttribute().getName()), entityInformation.getId(entity)));

        em.createQuery(update).executeUpdate();
    }

    @Override
    public T findOne(ID id) {
        return super.findOne(
                Specifications.where(new ByIdSpecification<>(entityInformation, id)).and(notDeleted()));
    }

    private static final class ByIdSpecification<T, ID> implements Specification<T> {

        private final JpaEntityInformation<T, ?> entityInformation;
        private final ID id;

        ByIdSpecification(JpaEntityInformation<T, ?> entityInformation, ID id) {
            this.entityInformation = entityInformation;
            this.id = id;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.equal(root.<ID>get(entityInformation.getIdAttribute().getName()), id);
        }
    }

    private static final class DeletedIsNull<T> implements Specification<T> {

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.isNull(root.<LocalDateTime>get(DELETED_FIELD));
        }

    }

    private static final class DeletedTimeGreatherThanNow<T> implements Specification<T> {

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.greaterThan(root.get(DELETED_FIELD), Timestamp.valueOf(LocalDateTime.now()));
        }

    }

    private static <T> Specification<T> notDeleted() {
        return Specifications.where(new DeletedIsNull<T>()).or(new DeletedTimeGreatherThanNow<>());
    }
}
