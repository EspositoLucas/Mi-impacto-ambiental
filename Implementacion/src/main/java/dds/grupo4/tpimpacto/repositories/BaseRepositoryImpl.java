package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {

    protected final EntityManager entityManager;

    public BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(T obj) {
        entityManager.persist(obj);
    }

    @Override
    public T merge(T obj) {
        return entityManager.merge(obj);
    }

    @Override
    public List<T> getAll() {
        String query = "FROM " + getEntityName() + " e";
        return entityManager.createQuery(query, getEntityClass())
                .getResultList();
    }

    @Override
    public T getById(long id) {
        return entityManager.find(getEntityClass(), id);
    }

    public abstract Class<T> getEntityClass();

    protected String getEntityName() {
        return getEntityClass()
                .getAnnotation(Entity.class)
                .name();
    }
}
