package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class BaseRepository<T extends BaseEntity> {

    protected final EntityManager entityManager;

    public BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(T obj) {
        entityManager.persist(obj);
    }

    public T merge(T obj) {
        return entityManager.merge(obj);
    }

    public List<T> getAll() {
        String query = "FROM " + getEntityName() + " e";
        return entityManager.createQuery(query, getEntityClass())
                .getResultList();
    }

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
